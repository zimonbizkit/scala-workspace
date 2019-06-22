package mutability_vs_immutability_in_practice

object MutabilityVsImmutabilityInPractice {
  def main(args: Array[String]): Unit = {
    // profiling immutable collections

    // profile driven decisions to switch to mutability

    val iterations = 1 to 5000

    val range = 1 to 10000

    val start = System.currentTimeMillis()


    iterations.foreach(
      _ => range.foldLeft(
        scala.collection.Map[String, Int]()
      ) {
        (map, i) => map + (i.toString -> i)
      }
    )

    val end = System.currentTimeMillis()

    // this takes 10s average to run as the first println shows, this is slow
    println(end - start)
  }
}
