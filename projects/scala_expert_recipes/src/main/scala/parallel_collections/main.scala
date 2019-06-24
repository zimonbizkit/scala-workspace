package parallel_collections

import scala.collection.parallel.ParSeq


// parallel collections can be used for efficiency when:
// 1.- we have large collections that can and need to be processed by more than one thread
// 2.- we don't have (and we won't introduce) state on collection processing

// However,if we use parallel collections on small collections, we would be losing performance by spinning up
// a few threads to process the collection rather than just processing it.
object main {
  def main(args: Array[String]): Unit = {
    //test
    //  println(test)
    println(parcoltest)
    println(parcoltestThreads)

    parcolwithstatetest

  }
  def test : List[Int] = List(1,2,3,4).map { i => println(i); i+5 }

  // parallel collections access the values of the collection parallelly, but when operating with elements of the collection
  // the order is maintained. To see this, check the prints of the 'i' variable inside the map, and then the result of
  // the 'parcoltest' function call.
  def parcoltest : ParSeq[Int] = List(1,2,3,4).par.map { i => println(i); i+5 }
  // in this case, we can see that a few element of the list is accessed by a different thread. It's not that scala will
  // spawn a thread for each element in the sequence, it uses a threadPool with a rather specific set of threads that are
  // reused always.

  def parcoltestThreads : ParSeq[Int] = (1 to 100).toList.par.map { i => println(Thread.currentThread.getName); i+5 }

  // this means that we need to be careful on referencing immutable state inside at any of the iterations on a parallel collection
  // considering the scenario below:

  def parcolwithstatetest : Unit = {
    // we have a counter that starts with 0
    var counter  = 0

    // and a collection of 100 elements,accessed parallely, that will have each its elements added by 5, and for each iteration, it will
    // add one
    println((1 to 100).toList.par.map{ i => counter +=1; i+5})


    // this counter, however, won't have 100 values. Why is that?
    println(counter)
    //This is because different threads will look at the 'counter' value in a specific moment
    // where the counter has a specific value, and it will update to that value , plus one, like below:
    // COUNTER = 1 <-----------------
    //                              |
    // THREAD 1 LOOKS AT COUNTER-----
    // THREAD 2 LOOKS AT COUNTER-----
    // THREAD 1 SEES value 1 and updates to 2
    // THREAD 2 sees value 1 and updates to 2
    //
    // FINAL 'counter' VALUE = 2

    // this can be solved by common concurrency state solutions , such as locks and mutexes, but in scala we should choose the right abstraction
    // or tool to solve a specific problem.

    // we can transform back a parallel collection to a non parallel one by calling "seq" on it
    test.seq
  }

}
