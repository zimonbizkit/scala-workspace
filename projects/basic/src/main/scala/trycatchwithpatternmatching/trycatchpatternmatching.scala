package trycatchwithpatternmatching

import scala.util.control.NonFatal

object trycatchpatternmatching {
  def main(args: Array[String]): Unit = {

    try {
      // throw new MyException("expected")
      println(
        makeAnimalDance(
          Animal("Manolo",18)
        )
      )
      println("nothing happens, ok")
      makeAnimalDance(
        Animal("Sinforoso",78)
      )

      /*erroredMakeAnimalDance(
        Animal("Error",18)
      )*/
    } catch {
      //case AnimalTooOldException(name,age) => println(s" $name is too old with $age years old, but we let him dance anyway")
      case exception: MyException => println(s"exception is ${exception.toString},controlled by me")
      case exception : Exception if scala.util.control.NonFatal(exception) => println("wtf is this pete")
    }
  }

  def makeAnimalDance(animal: Animal) : String = {
    if (animal.age > 65) {
      throw AnimalTooOldException(animal.name,animal.age)
    }
    s"I am ${animal.name}, I am ${animal.age} years old and I am dancing"
  }

  // dangerous but to show that there are some exceptions that cannot be catched
  // normally, such an stack overflow exception, which is what this function provokes

  def erroredMakeAnimalDance (animal: Animal) :Unit = {
    // note that if we do a normal recursion here, scala will detect it as a 'tail' recursion
    // and will loop indefinitely (with maybe specific memory management?) until we SIGINT the application
    // with CTRL + C
    if (animal.age < 65) {
      s"trying again..."+ erroredMakeAnimalDance(animal)
    }else{
      s"DANCING as a MF"
    }
  }
}

case class Animal(name: String, age: Int)
class MyException(message: String) extends Exception
case class AnimalTooOldException(name:String,age:Int) extends Exception(s"$name is older than 18, it is $age")