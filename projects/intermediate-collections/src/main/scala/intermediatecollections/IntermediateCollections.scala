package intermediatecollections

object IntermediateCollections extends App {
  override def main(args: Array[String]): Unit = {
    val list = List(1,2,3)
    list :+ 4
    // should print 1,2,3 as lists are by default immutable
    print(list)

    val myLongList = (1 to 100).toList
    0 :: myLongList
    myLongList :+ 0

    // should have the same effect
    print(myLongList)

    /* DONT UNCOMMENT THIS

    The List type, is just a linked chain of integers as we define it. The first element is an integer but the next ones
    contains a reference to the prior elements. This means that dealing with long linked lists like below, would
    provoke some resources overhead, as list manipulation implies manipulating all the references of the elements of the
    list. This is done from the top down, so if we also want to manipulate an element of the list that's at the end of it
    we might either experience slowness, or a Java heap space problem.
    ----------------------------------------------------------------------------------------
    This scenario creates a list of ten million elements, like this
    [myLongList] = 0[ref nil] -> 1[ref 0] -> 2[ref 1] -> 3[ref2]...
    This takes some time:

    val mySuperLongList = (1 to 10000000).toList

    ----------------------------------------------------------------------------------------
    This just implies little overhead as we just need to recover the whole list and not traverse through all its elements,
    and just appending one of its elements.

    0 :: myLongList
    ----------------------------------------------------------------------------------------
    But this example that appends a 0 at the end of the list needs to walk through all the elements, and this implies
    an important resources overhead that can result in an application crash

    myLongList :+0
    ----------------------------------------------------------------------------------------
    */

    vecs

    maps

  }


  def maps = {
    val map: Map[String,Int] = Map(
      "Manolo" -> 10,
      "Sinforoso" -> 345,
      "Saturnino" -> 123123,
      "Rogelio" -> 18,
    )

    map.get("Manolo") // The output will be -> Some(10)
    map.get("LAKJDALSKD") // The output will be -> None

    map.apply("iyiyiu") // error
    map.apply("Manolo") // will output 10

    map + ("Paco" -> 10) // will add to the map



  }

  def vecs = {

    // Vectors are faster at that. Could be a good practice for heavy arrays?
    val mySuperLongVector = (1 to 1000000).toVector

    0 +: mySuperLongVector

    mySuperLongVector :+0

    mySuperLongVector.slice(5000,5005)

    mySuperLongVector.patch(5000,Seq(1,2,3,4,5),5)

    mySuperLongVector.patch(5000,Seq(1,2,3,4,5),1)

    mySuperLongVector.patch(5000,Seq(1,2,3,4,5),10)


  }
}
