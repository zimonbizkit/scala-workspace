package sortingcollectionsandforcomprehensions

import java.time.LocalDateTime

object SortingCollections {

  private val animals = sortingcollectionsandforcomprehensions.AnimalsList.animalList

  def main(args: Array[String]): Unit = {
    val sq = Seq(4,6,1,2,9,12,3)
    println(sq.sorted)
    val sq2 = Vector(4,6,1,2,9,12,3)
    println(sq2.sorted)

    // for the animals Seq is different, you'll need to define ordering
    println(animals.sortWith(_.age < _.age))
    // you can do it like this
    println(animals.sortBy(_.age))

    //or like this. Don't look at the implicit val, not knowing what's doing for now
    implicit val animalOrdering = Ordering.by[Animal,Int](_.age)

    // this should now have the 'sorted' working as we implemented an ordering above. Seems to work
    println(animals.sorted)

    // another form of defining an implicit ordering for dates
    val firstDate = LocalDateTime.now
    val secondDate = LocalDateTime.now

    val datesSeq = Seq(secondDate,firstDate)

    implicit val dateTimeOrdering = Ordering.fromLessThan[LocalDateTime](_ isBefore _)
    println(datesSeq.sorted)

  }
}