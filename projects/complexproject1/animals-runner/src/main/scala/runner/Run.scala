package animals

object Run extends App {
  val myCat = new Cat("Jazz","Tabby")
  val myDog = new Dog("Randy","Husky")

  println ("I'm running really well! " + myCat.speak + " " + myDog.speak )
  println ("This is my cat and dog in JSON format:")
}