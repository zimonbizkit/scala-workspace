package try_either_type_alternatives

import scala.util.Try
import scala.util.{Success,Failure}

object main {
  def main(args: Array[String]): Unit = {
    // try and either can be used to represent type alternatives
    // and it's also an alternative to try/catch

    // this is an example of customwrapping some unexpected behaviour into an exception, and that's handled
    // to return a zero value
    println(asInt("s"))

    // this is somehow the same, but using the 'Try' util. This just wraps the unexpected behaviour in a
    //wrapping type [Try], that can be either Success,or Failure

    // this should output Success(5)
    println(secureAsInt("5"))

    // this shouldn't, as this should wrap the NumberFormatException inside the Failure type
    println(secureAsInt("s"))

    // importing Success and Failure allows us to patternmatch on results, like below
    // this prints 'OK'
    println(
      secureAsInt("5") match {
      case s: Success[_] => "OK"
      case f:Failure[_] => s"this had an error =>$f"
     }
    )

    // this prints "this had an error =>Failure(java.lang.NumberFormatException: For input string: "s")"
    println(secureAsInt("s") match {
      case s: Success[_] => "this went OK"
      case f:Failure[_] => s"this had an error =>$f"
    }
    )

    // this goes well for managing exceptions, but exceptions should be the least expected behaviour and for which the
    // execution should be ended. So for representing "non execution stopping/non fatal" errors we should use a softer type
    // that lets us manage more controllable and expected errors. For this, we can use Either

    // Either has two types in it, Left, and Right. Implementing Right("5"), for example will return
    // Right[Nothing,String],
    // This means that the value is held in the part  at where the function name specifies respectively (at right of left). This
    // means that Either type can be used to specify that a result can be _one_ of _two_ expected types.

    // this will work
    val either : Either[String,Int] = Left("5")
    println(either)

    // however, this will fail, as the rightmost part of the Either expects a String, not an Int
    //val either2: Either[String,Int] = Right("hello")
   // println(either2)

    // this gives us the opportunity to represent an error message on the left part of an Either, and a correct value
    // on the Right

    println(asIntWithEither("5"))
    println(asIntWithEither("potato"))

    println(
      asIntWithEither("potato") match {
        case Left(error) => 0
        case Right(int) => int
      }
    )

    // eithers work even with for comprehensions
    for (myint <- asIntWithEither("5")) println(myint)
    for (myint <- asIntWithEither("s")) println(myint)
  }

  // convention : when using Either, the left should represent the "wrong" value
  //                                                                  |
  //                                       ___________________________|
  //                                       v
  def asIntWithEither(s: String): Either[String,Int] = {
    Try(s.toInt) match {
      case Success(int) => Right(int)
      case Failure(_) => Left("Invalid Integer")
    }
  }
  def secureAsInt(s: String): Try[Int] = {
    Try(s.toInt)
  }

  def asInt(s: String) :Int = {
    try {
      s.toInt
    }catch {
      case nfe: NumberFormatException =>
        0
    }

  }
}
