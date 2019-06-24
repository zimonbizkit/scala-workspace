package advanced_string_interpolation

object Main {
  def main (args: Array[String] ): Unit = {
    // we are going to create a custom string interpolator that will
    // take some functions as arguments and will produce a result

    val add = (a:Int,b:Int) => a+b
    val multiply = (a:Int,b:Int) => a*b

    // printing the string with the implicit interpolator created below
    println(fold"1 $add 2 $add 3 $multiply 5 $add 12 $multiply 153")

    // also, advanced string interpolation would fit, for example , when parsing XMLs
    println(_xml" abc ")

    // the example wont work as I'm not importing properly the scala-xml library somehow


  }

  implicit class XmlInterpolator (val sc: StringContext) extends AnyVal {
    def _xml(parameters: Any*) = {
       val wholeString = sc.parts.zipAll(parameters,""," ").collect {
         case (part,param) =>
           part + param.toString
       }.mkString("")

    }
  }

  implicit class FoldInterpolator(val sc: StringContext)extends AnyVal {
    def fold (parameters: Any*) = {

      // this takes all the non-variable values of the string context, and trims them to be an
      // arrayBuffer of Ints
      val ints = sc.parts.map(_.trim.toInt)
      // this just takes all the parameters (parts with the $ that are passed as variables, that
      // in fact are functions), and tell that the value from the left and right will operate with ints.
      // This is needed, as the parameters in the implicit class typed in 'fold' are type in the signature
      // as "Any*"
      val operators = parameters.collect {
        case binary: Function2[_,_,_] =>
          binary.asInstanceOf[Function2[Int,Int,Int]]
      }

      // this just makes the first operation on the first and second int , with the first operator
       val start = operators(0).apply(ints(0),ints(1))

      // then, it drops the last operator, and calls a left-reducing function, from the 'start' part
      // that will apply(1), the operation, to the latest(2) value reduced (the carry) with the following int(3)
      //           ¨¨¨¨¨¨¨¨                        ¨¨¨¨¨¨¨¨¨                                              ¨¨¨¨¨¨
      //             |                             |                                                        |
      operators.drop(1).zip(ints.drop(2)).foldLeft(start) { //                                              |
      //             |                             |                                                        |
        case (latest,(op,int)) => //               |                                                        |
      //   __________|_____________________________|________________________________________________________|
      //   1     2    3
      //   v     v    v
          op(latest,int)
      }
    }
  }
}

