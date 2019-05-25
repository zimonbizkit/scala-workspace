package functiondeclarationtricks

object functiondeclarationtricks {
  def main(args: Array[String]): Unit = {
    // this is normal function call
    println(makeAnimal("BLAKSJDLAK",12))
    // if in the function definition, the other parameters are defaulted, we can omit them as
    //nullable parameters in functions in other languages
    println(makeAnimal("BLAKSJDLAK"))
    // also , we can name some parameters for the sake of placeholding values
    // and also to 'skip'  parameters by telling with the placeholder where the argument goes
    println(makeAnimal("BLAKSJDLAK", species = Some("Dog")))
    // I think this is not so recommended as it can be confusing from the function caller perspective
    println(makeAnimal(name= "BLAKSJDLAK", age= 12,  species = Some("Dog")))

    // this is just to remember myself a simple pattern match
    println(youngOrOldAnimal(3,"Doggy","Dog"))
    println(youngOrOldAnimal(10,"Doggy","Dog"))

    // in this case, theres a call to two functions, to eventually decide which name to print
    println(youngOrOldAnimal(12,youngName("Doggy"),oldName("Doggo")))

    // imagining that the function young|oldAnimal are expensive processes , we would like to generate the process for
    // the name we would like to use, and not both

    // we can achieve this by declaring youngOrOld, like this:
    // def youngOrOldAnimal(age:Int, youngName: => String, oldName: => String): String = {

    // as far as I understand, this is a form of 'lazy' evaluation where the object is only constructed when
    // it's really going to be used.
    // pros for that -> only creating as much objects as we want, good!
    // cons for that -> lazy evaluation is at runtime/compiler side, and could have an effect in performance maybe?
    println(optimisedYoungOrOldAnimal(12,youngName("Doggy"),oldName("Doggo")))

    // more examples on this topic below:

    // this goes bad
    println(s"${printingArithmeticErrors(5/0)}")

    // this goes good
    println(s"${printingArithmeticErrors(5+2)}")

    // this goes bad, and haves the same behaviour as the first call
    println(
      printingArithmeticErrors {
        (0 to 5).map( 5 / _)
      }
    )

    // what we achieved on this as I understand is that we are more permissive on the type of function call, by
    // 1.- Specifying that a function is working invariantly with a specific type [T]
    // 2.- "Lazy evaluating" the first argument to the point that we are able now to pass more complex expressions
    //     by letting the parameter be passed and evaluated ,_only_ when it's going to be used, which is inside the
    //     function

    // this is also a very common pattern to use try/catrch statements on complex expressions , and if they don't go
    // well, have a specific behaviour happen , like wrapping exceptions /errors in a specific way (logging, message)..
  }

  def printingArithmeticErrors[T](fn: => T): Option[T] = {
    try {
      Some(fn)
    }catch{
      case arithmeticException: ArithmeticException =>
        println(s"Exception happened : $arithmeticException")
        None
    }
  }

  def makeAnimal(name: String, age: Int = 0, species: Option[String] = None): String = {
    s"Animal $name is $age years old ${species.map{s => "and is a "+s} getOrElse{}}"
  }

  def youngOrOldAnimal(age:Int, youngName: String, oldName: String): String = {
    age match {
      case a if age < 10 => youngName
      case _ => oldName
    }
  }

  def optimisedYoungOrOldAnimal(age:Int, youngName: => String, oldName: => String): String = {
    age match {
      case a if age < 10 => youngName
      case _ => oldName
    }
  }


  private def youngName(name:String): String = {
    println("processing...")
    s"Creating the young name -> $name"
  }
  private def oldName(name:String): String = {
    println("processing...")
    s"Creating the old name -> $name"
  }
}
