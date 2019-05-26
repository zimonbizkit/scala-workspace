package test

trait Placeable {
  def place: String
  var placeCount : Int
  def incrementPlaceCount : Unit = placeCount += 2
}
