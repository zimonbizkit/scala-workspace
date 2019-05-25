package partialfunctions

object partialfunctions {
  def main(args: Array[String]): Unit = {
    val santiago = Animal("Santiago","Cat")
    val cleo = Animal("Cleo","Dog")
    println(describeWalking(santiago))
    println(describeWalking(cleo))
    println(meow(santiago))
    // a way to evaluate if a partial function will work on a specific match, is to use the 'isDefinedAt'
    // method, that will check the cases of the partial function and return either true or false if they
    // can handle the case , or not, respectively
    println(s" can we use partialfunctionmeow on santiago? : ${meow.isDefinedAt(cleo)}")
    println(s" can we use partialfunctionmeow on santiago? : ${meow.isDefinedAt(santiago)}")
    println(s" can we use partialfunctionmeow on Tiger? : ${meow.isDefinedAt(Animal("Manolo","Lion"))}")
    println(s" can we use partialfunctionmeow on Tiger? : ${meow.isDefinedAt(Animal("Manolo","Tiger"))}")
    println(s" can we use partialfunctionmeow on Tiger? : ${meow.isDefinedAt(Animal("Manolo","Suricata"))}")

    println(speak(cleo))
    println(speak(santiago))
    println(speak(Animal("PATATA","Lion")))

    println("------------------------------------------------")

    println(speak.applyOrElse(
      Animal("A","Cat"),
      (unknown: Animal) => "speak"
    )
    )

    println(speak.applyOrElse(
      Animal("A","Dog"),
      (unknown: Animal) => "speak"
    ))

    println(speak.applyOrElse(
      Animal("A","Lion"),
      (unknown: Animal) => "speak"
    )
    )
    // applyOrElse case will be used here. This just accepts the arguments:
    // 1.-argument of the partial function to pass it as usual,
    // 2.- argument that is just another function that handles the uncovered case
    //     this case is for the fish, which cannot either meow or woof, and is implemented
    //     as "speak"
    println(speak.applyOrElse(
      Animal("A","Fish"),
      (unknown: Animal) => "speak"
    ))
  }

  def describeWalking(animal: Animal) : String = {
    "walk"
  }

  // Partial functions are just a val that is a body of a pattern matching statement
  // calling 'meow' and passing an animal whose species is one of these below, will print
  // a 'yelling as a' string and return either a meow or a roar
  val meow: PartialFunction[Animal,String] = {
    case Animal(_,"Cat") => println("yelling as a cat");"meow"
    case Animal(_,"Tiger") =>println("yelling as a tiger"); "meow"
    case Animal(_,"Lion") => println("yelling as a lion");"roar"
  }

  // same as above but only in one case
  val woof : PartialFunction[Animal, String]  = {
    case Animal(_,"Dog") => "woof"
  }

  // speak will wrap meow or woof, in case meow doesnt work, woof will be tried
  // a way to wrap partial functions onto orElse is to putting them in another val, like this
  private val speak = meow orElse woof
}

case class Animal(name: String, species: String)