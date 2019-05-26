package patternmatching

object patternmatchingcollections {
  def main(args: Array[String]): Unit = {
    matchingAListObject
    matchingADef
  }

  // _* <--- vararg
  private def matchingADef : Unit  = {
    val mySeq : Seq[Int] = List(1,2)

    print(mySeq match {
      case Seq (a,b, rest @ _*) =>
        print(rest.mkString(","));a + b
    })
  }
  private def matchingAListObject = {
    val myList: List[Int] = 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: Nil
    val res: Int = myList match {
      case a :: _ :: b :: _ =>
        a + b
    }
    println(s" BOBOBOBO result is $res")
  }
}
