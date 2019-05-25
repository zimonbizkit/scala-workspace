package switch_annotation

case class Test(in: String)

object main {
  def main(args: Array[String]): Unit = {


    println(
      5 match {
      case 5 => "hello"
      case 3 => "bye"
      case _ => "done"
      }
    )

    println(
      (5: @annotation.switch) match {
        case 5 => "hello"
        case 3 => "bye"
        case _ => "done"
      }
    )

    // this should issue a warning as the compiler cannot optimize it to either a switch or a jump table because
    // of its "complex" typing
    println(
      (Test("hai"): @annotation.switch)  match {
        case Test("bai") => "bai"
        case Test("hai") => "hai"
        case _ => "laksjdaklsjdals"
      }
    )
  }
}
