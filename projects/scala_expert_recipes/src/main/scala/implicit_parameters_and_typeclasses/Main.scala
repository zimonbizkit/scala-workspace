package implicit_parameters_and_typeclasses

// A way to define behaviour in the top-most level of abstraction, and let some implicit values
// work on those contract, you can use typeclasses. If we look at the trait below:
trait Printable[T]{
  def print(item: T): Unit
}

// we can see it can be used to make a 'printable' object work, with the type 'T' (which means any type)
// and that this Printable trait, has a method 'print' that works on this topmost level type. The same goes for the
// trait below:

trait Adder[T] {
  def add(a: T,b: T):T
}

// It's just a trait that 'by contract' it's going to add value A and B, both of type T. This, of course, without
// going into details about the implementation

object Main {

  // A good usage for these 'generic type' traits is to use them as 'implicits'. Looking to the functions below, we'll
  // see they rely on the funciton implicit , so as to apply the contract of the 'most generic' type , but at the same time
  // let the users of this method, know about the defails of this implementation

  // here we are telling the function 'AddtwoThings' to use the 'Adder' trait, who knows the entrypoint of the implementation
  // of actually adding these two things, but without knowing the type of what we are adding
  def addTwoThings[T](a:T,b:T)(implicit adder: Adder[T], printable: Printable[T]): T = {
    printable.print(a)
    printable.print(b)

    adder.add(a,b)
  }

  // in this case, which is mpre specific, we are telling that the 'addInts' has an implicit value that tells us that a 'Printable'
  // type object should be on scope that should know the details of how to print the ints.
  def addInts(a: Int, b: Int)(implicit printable: Printable[Int]): Int = {
    printable.print(a)
    printable.print(b)

    a + b
  }
  def main(args: Array[String]): Unit = {
    // in the main method, which is the final execution scope, is where we define all the 'implicits' that the generic typeclasses or traits
    // are going to use:
    // 1.- a Printable of Ints, where we tell to the scope how to print it (println(item)
    // 2.- an Adder of Ints, where we override the add contract of the Adder trait, and give an actual implementation to it
    //     which is 'a + b'
    // 3.- and and Adder fo Strings, where we do the same but with strings -> s"$a:$b"

    implicit val intPrintable : Printable[Int] = new Printable[Int] {
      def print(item: Int) :Unit = { println(item)}
    }
    implicit val stringPrintable : Printable[String] = new Printable[String] {
      def print(item: String) :Unit = { println(item)}
    }

    implicit val intAdder : Adder[Int] = new Adder[Int] {
      def add(a: Int, b: Int): Int = a + b
    }
    implicit val stringAdder : Adder[String] = new Adder[String] {
      def add(a: String, b: String): String = s"$a:$b"
    }

    // note we can have an 'implicitly defined behaviour' called directly in our scope like so
    intPrintable.print(5)

    // in this case, we are both printing the values thanks to the 'Printable' typeclass and actually adding an int
    println(
      addInts(5,3)
    )

    // here, we are telling the generic 'addTwoThings' to add these. But since we don't know the details of the implementation
    // going to the method, the intrinsics of it is _implicitly_ loaded on _this_ scope, where we tell, via the 'intAdder' implicit val,
    // how we add those ints.
    println(
      addTwoThings(5,3)
    )
    // here we do the same, but with the implicit 'stringAdder' implicit val
    println(
      addTwoThings("hello","goodbye")
    )

    // but how do we know which implicit goes into which function 'addTwoThings' call? This is thanks to a strong type checking.
    // addTwoThings, accepts _anything_ (type T). Given  the implicit Adder that's included in this method, we can tell, via the type of
    // intAdder (Adder[Int]) and StringAdder (Adder[String]), which implicit val actually _fits_ for which call.

    // Not having these implicit vals defined in scope, and calling the 'addTwoThings' method with a specific 'unimplicited' type will result
    // in a compile error

  }

}