package test

object Runner extends App {
  override def main(args: Array[String]): Unit = {
    val aPerson = new Person("Potato","BCN","Place")

    println(s"Persons name ${aPerson.name}")
    println(s"Persons place ${aPerson.place}")
    aPerson.moveTo("AUS")
    println(s"Persons place ${aPerson.place}")
    println(s"Is the person placeable: ${aPerson.isInstanceOf[Placeable]}")
    println(s"Person latest place ${aPerson.latestPlace}")

  }
}
