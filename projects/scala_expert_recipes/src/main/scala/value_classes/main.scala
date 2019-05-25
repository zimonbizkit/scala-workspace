package value_classes

// universal trait , as it is extended from Any, can be used on seemingly any
// class or case class that extends AnyVal
trait UniversalTrait extends Any {
  def int : Int
  def withExtra5 : Int = int + 5
}

// this value class is the use case for the universal trait, that has the implementation
// details of the value in it
case class MyValue(int : Int) extends AnyVal with UniversalTrait {

}

object main {
  def main(args: Array[String]): Unit = {

    // for the most part, this can be used as a container of value objects
    // they can just contain one parameter
    var v = MyValue(5)

    println(v)

    // def inside case classes are static methocs
    println(v.withExtra5)

    // this seems the same as value classes without the case class
    // class MyValue (val int : Int) extends AnyVal

    println(
      v match {
      case MyValue(internal) => internal
    }
    )
  }
  Option
}
