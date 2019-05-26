package animals

import net.liftweb.util.Helpers._
import net.liftweb.json._

object Run extends App {
  val myCat = new Cat("Jazz","Tabby")
  val myDog = new Dog("Randy","Husky")

  implicit val formats = DefaultFormats
  println ("I'm running really well! " + capify(myCat.speak) + " " + capify(myDog.speak) )
  println ("This is my cat and dog in JSON format:")
  println(prettyRender(Extraction.decompose(myCat)))
  println(prettyRender(Extraction.decompose(myDog)))
}