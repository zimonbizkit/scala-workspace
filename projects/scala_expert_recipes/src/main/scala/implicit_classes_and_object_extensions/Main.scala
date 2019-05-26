package implicit_classes_and_object_extensions

// object extensions is just a mechanism that allows us to extend behaviour and functionality of yet
// existing classes


object Main {                                             // .------------------------------------------------
                                                          // | this part allows to not instantiate the
                                                          // | MagicInterpolator when it's called in the
                                                          // | main method below. Despite the behaviour
                                                          // | is the same, extending from AnyVal seemingly
                                                          // | makes the call to the interpolator static, so it
                                                          // | is never instantiated. [value classes]
                                                          // |
                                                          // v
  implicit class MagicInterpolator(val sc: StringContext) extends AnyVal {
    // what we're doing here is to create an implicit class inside the context of the Main object, that will
    // act as a 'decorator' for the string that we'd like to print. the 's"This is a string"' mechanism should
    // work the same way
    //
    // Why the asterisk here?
    //                   v
    def m(parameters: Any*) = {
      println(sc.parts)
      // doing silly things to the parameters to see what we are given once we call the interpolator:
      sc.parts.zipAll(parameters,"","").map{
        case ("",param) => param
        case (part,"") => part
        case (part,param) => s"$part:magic:param"
      }.mkString("\n")
    }
  }

  implicit class PoundifyStrings(val string: String) extends AnyVal {
    def poundify :String = {
      string.replace(" ","pound")
    }
  }
  def main(args: Array[String]): Unit = {
    val s : String = "hello"

    val name : String ="Antonio"

    // here we are testing our string interpolator with a value class
    println(m"Hello $name:$name")

    println("test test test".replace(" ","#"))

    // this is just testing our string extension.
    // Still unclear how the poundify method is usable from the String. Maybe because type checking of implicit
    // classes?
    println("test test test".poundify)

  }
}
