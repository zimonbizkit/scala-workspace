package advantages_and_dangers_of_mutability

import scala.concurrent.Future  // <----------------------------| This would allow to run some code concurrently so
import scala.concurrent.ExecutionContext.Implicits.global //    | we can evaluate how mutable maps behave


// DISCLAIMER for this example:
// the author tries to show the pros and cons of immutable & mutable access to collections in the video, but
// the proof of the shared mutable state of collections that is shown in the video could not be demonstrated in this
// example. I'll need to investigate why that could not be reproduced.

// Reasons:
  // ->Putting this functionality inside of an 'object' of scala, and executing it?
  // .-No, as executing this on ammonite gives the same exact result

  // -> Some thread locking happening, when the exercise is compiled via either intellijIDEA or sbt?
  // .-Also no, as ammonite execution doesn't work either


object Main {
  def main(args: Array[String]): Unit = {


    // here printing how much time to do operations on a collection of 10 items, immutably treated
    println(elapsedTimeforImmutableCollections)
    // here printing how much time to do operations on a collection of 10 items, mutably treated
    println(elapsedTimeforMutableCollections)

    // running this , we should get the mutable treated collection as more time efficient steadily
    // mostly it's about three times faster

    // Why immutable collections are slower:
    //    1.- They cannot write to yet accessed/allocated memory in most cases, as opposite of mutable
    //    2.- They go through a lot of optimizations to enhance performance, but more operations are done

    // We could run into situations where large collections should be managed mutably to gain performance rather
    // than immutably. However, striving for immutability is a must as other should consume those collections and they
    // should be as safe as possible, therefore, immutable.




    // Another example of proper use of immutable collections, is to use them when
    // manipulating them concurrently. Given the example below:

    val ranges = List(1 to 10,1 to 10,1 to 10,1 to 10)
    /*val mMap =  scala.collection.mutable.Map[String,Int]()

    Future.sequence(
      ranges.map(
        range => Future(
          range.foreach {
            i => mMap.get(i.toString)
              mMap += (i.toString -> i)
          }
        )
      )
    ).onComplete(_ => println(s"Done traversing a mutable map, size of collection is ${mMap.size}"))*/

    // we should not see the 'Done!' printed.Even if the work is done in a background thread, what's happening here is
    // that mutable maps are not safe for reading and writing in parallel. So we can't have multiple threads accessing
    // a mutable map bcause we can get in infinite loops in implementations (instructor dixit). So there's a danger in
    // mutable collections , when they are not set for concurrent access at all.

    // However ,Java has a ConcurrentMap that deals with this problem , which we can sync access with difference pieces,
    // although  there's a performance hit

    // However, with immutable collections
    var iMap = scala.collection.Map[String,Int]()
    Future.sequence(
      ranges.map(
        range => Future(
            range.foreach {
              i => iMap.get(i.toString)
                println(s"working with value $i")
                iMap += (i.toString -> i)
          }

        )
      )
    ).onComplete(_ => println(s"Done traversing an immutable map"))
    println(s"Current size of collection is ${iMap.size}")


    // we should get , with this execution , the message instantly. However, as we are dealing with an immutable collection
    // the access is also immutable, and the view that the four threads have for the same collection alters the expected result.
    // This 'shared immutable state' would make that, for a position in the collection, one thread could alter the position of a
    // given collection more than once, and have undesired results.

    // At least that's what the author of the video was trying to demonstrate, and I wasn't able to reproduce.

  }

  // function to capture the runtime of iter1.foreach in milliseconds
  def elapsedTimeforImmutableCollections: Long = {
    val iter1 = 1 to 10

    val rng = 1 to 10
    val start = System.currentTimeMillis()

    iter1.foreach(                          // <------------------------ | doing a foreach in the immutable collection
      _ => rng.foldLeft(
        scala.collection.Map[String, Int]() // <------------------------ | mapping a 'string' to 'int' for each item in
      ) {                                   //                           | our range, and treating the map as immutable
        (map, i) => map + (i.toString -> i)
      }
    )

    val end = System.currentTimeMillis()

    end - start
  }

  // function to capture the runtime of iter1.foreach in milliseconds
  def elapsedTimeforMutableCollections: Long = {
    val iter1 = 1 to 10

    val rng = 1 to 10
    val start = System.currentTimeMillis()

    iter1.foreach(
      _ => rng.foldLeft(//  v----------------------------------------------------| the difference of doing a mutable map, here
        scala.collection.mutable.Map[String, Int]() // <------------------------ | mapping a 'string' to 'int' for each item in
      ) {                                           //                           | our range, and treating the map as mutable now
        (map, i) => map + (i.toString -> i)
      }
    )

    val end = System.currentTimeMillis()

    end - start
  }
}
