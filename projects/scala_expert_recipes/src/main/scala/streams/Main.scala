package streams

object Main {
  def main(args: Array[String]): Unit = {
    val l = List(1,2,3,4,5)

    println(l.foreach(_ => println("hello")))

    // mapping 5 values but needing only three
    println(l.map(_ => println("hello")).take(3))

    // streams are answer to what happens if I want to have a colection tahat lets me only do the operations
    // that I end up needing on a specific portion of a collection and not all

    // the operation below will have only the println done three times
    //  |_________
    //            v
    println(l.toStream.map(_ => println("hello")).take(3))

    // streams are a form of lazy collection that only evaluates the element in the collection when you explicitly
    // tell to do so

    println(l.toStream.map(_ => println("hello")).take(3).take(2).take(1))

    // The same goes for any kind of lists, even the function ones
    val fl = List(
      () => println(1),
      () => println(2),
      () => println(3)
    )

    // this will only print 1
    println(fl.toStream.map(_()).take(1))
    // 53
    println(Stream.continually(5).take(3))
    // 20
    println(Stream.continually(2).take(20))
    // 8
    println(Stream.concat(List(1,2,3),List(1,2,3,4,5),List(5,6,7,8)).take(8))
    //
    print(Stream.range(1,100,3))

    // This is going to be slow as iteratively creating a stream of many values can be slow performant
    // However, this works as we don't care about _all_ the elements in the stream, we just care about its length
    val bl = Stream.range(1,1000000000,1).length

    // unlike this , as it will crash as it will iteratively create all the elements to the list, probably provoking
    // a heap space overflow error
    //                         |____
    //                              v
    val badList = (1 to 10000000).toList.length

    // so the reason to use Streams, is to work with a very big set of elements, but just actually needing a portion
    // or a part of them as a result
  }
}
