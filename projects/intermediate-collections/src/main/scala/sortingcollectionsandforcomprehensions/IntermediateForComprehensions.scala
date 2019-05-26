package sortingcollectionsandforcomprehensions

object IntermediateForComprehensions {
  private val list = sortingcollectionsandforcomprehensions.AnimalsList.animalList
  def main(args: Array[String]): Unit = {
    // basic for loop to print the animals list
    for (animal <- list) println(animal)
    println("--------------------------------------------")
    // filtering sequence
    for {
      animal <- list
      if animal.age >=10
    } println(animal)
    println("--------------------------------------------")
    // same as above but yielding animal name in a seq
    println(
      for {
        animal <- list
        if animal.age >=10
      }yield{
        animal.name
      }
    )
    println("--------------------------------------------")
    // you can do prints on a for comprehension for debugging
      for {
        animal <- list
        // ignoring the left hand expression is the way to put a print statement
        // this also can be interpreted as side-effecting the for comprehension
        // to print the animal object
        _ = println(animal+ "Iterating!")
        if animal.age >=10
      }yield{
        animal.name
      }
    println("--------------------------------------------")
    // you can also patternmatch inside the for comprehension
    println(for {
      // look at the 'cow' in the pattern match. We are filtering now for all the cows in the collection
      Animal(name,age,Cow) <- list
      if age >=10
      number <- 1 to age
    } yield {
      name +" Number: "+ number
    })
  }
}
