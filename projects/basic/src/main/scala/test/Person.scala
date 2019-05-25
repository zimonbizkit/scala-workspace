package test

class Person(firstName : String, var lastName: String, var place: String)
  extends Nameable with Moveable {
  def name : String  = firstName+ ""+lastName
  def moveTo(newPlace: String): Unit = {
    this.place = newPlace
    placeCount +=1
  }
}
