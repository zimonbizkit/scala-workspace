package intermediatetricks

object IntermediateTricks {
  def main(args: Array[String]): Unit = {
    //look at the "animal" trait and then at the "wolf" class
    // instantiating a wolf and making it liveOneDay will have the sequence
    // of three elements:
    println((new Wolf).liveOneDay)
    println((new Fox).liveOneDay)
    println((new Crow).liveOneDay)
  }
}
