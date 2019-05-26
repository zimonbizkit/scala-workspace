package implicits

import obj._


object Main {
  def main(args: Array[String]): Unit = {
    // implicit vals are arguments that are passed apart from the function call
    // if we call the function a without any parameter, like so
    // a("hello")
    // it will tell us that there are no implicits found for the parameters 'goodbye'

    // we can fill it in ourselves EXPLICITLY to make it work
    a("hello")("bye")

    //but also we can have an implicit val defined here , like so
    implicit val implicitString : String = "Farewell"

    // now this prints 'hello and Farewell'
    println(
      a("hello")
    )

    // The way it works is that at the function call, it looks which _implicit vals_ are in scope of the
    // same type, and includes them as values on the second implicit argument list

    // we can use the 'implicitly' keyword to know which implicit values are in the current scope that can be passed
    // as implicit values, like so
    println(
      implicitly[String]
    )

    // you can have implicits that are looked up by type that is pased to a parametrized class, example below
    val sl : List[Int] = List(3,2,1).sorted
    // if we look at the signature of 'sorted', we'll see that takes an implicit parameter, that specifies ordering:
    // sorted[B >: A](implicit ord: Ordering[B])
    // we can use the implicitly keyword, then, to know what implicit value we have in scope
    println(
      implicitly[Ordering[Int]]
    )

    println(
      implicitly[Ordering[String]]
    )

    // however , the implicits have a hyerarchy. In case we want to have a custom implementation of the Ordering[Int]
    // comparator, we'll need to redefine it on our current scope, like so

    // however, this fails to an error indicating that theres a forward reference extending over the definition of value
    // sl, which is the one using the new ordering. That's the exact opposite of what's the instructor saying in this video
    // It might be because he's using ammonite instead of intellijidea, i don't know

    /*implicit val intOrdering: Ordering[Int]  = new Ordering[Int] {
       def compare(x: Int, y: Int): Int = {
        y - x
      }
    }*/

    // first, the implicits of our own scope are evaluated, then the ones related to a parametrized function usage are
    // evaluated, then the ones most recently imported
    println(
     implicitly[Ordering[Double]]
    )
  }


  def a(hello:String)(implicit goodbye: String): String = {
    hello + "and" + goodbye
  }
}

object obj {
  implicit val doubleOrdering:Ordering[Double]  = new Ordering[Double] {
    def compare(x: Double, y: Double) :Int ={
      (y-x).toInt
    }
  }
}