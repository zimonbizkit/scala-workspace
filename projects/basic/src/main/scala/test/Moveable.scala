package test

trait Moveable extends Placeable {
  def moveTo(place: String): Unit
  var placeCount = 0
  override def incrementPlaceCount : Unit =  placeCount += 1
  def latestPlace : String = s"$place @ $placeCount"
}
