package custommatchpatterns

object customMatchPatterns {

  // Sometimes we wouls need to have a more specific conditioning when using pattern matching
  // These are granted via singletons (objects), with the unapply method in it, it allows us to
  // encapsulate the conditioning to filter and/or map collections of objects
  def main(args: Array[String]): Unit = {
    println(
      namesOfFarmAnimals(
        Seq(
          Animal("A",12,"Cow"),
          Animal("A1",24,"Cat"),
          Animal("A2",36,"Dog"),
          Animal("A3",8,"Cow"),
        )
      )
    )
    println("-----------------------------------")
    println(
      tasksForFarmAnimals(
        Seq(
          Animal("A",12,"Cow"),
          Animal("A1",24,"Cat"),
          Animal("A2",36,"Dog"),
          Animal("A3",8,"Cow"),
        )
      )
    )
    println("-----------------------------------")
    println(
      undeterminedTasksForFarmAnimals(
        Seq(
          Animal("A",12,"Cow"),
          Animal("A1",24,"Cat"),
          Animal("A2",36,"Dog"),
          Animal("A3",8,"Cow"),
        )
      )
    )
  }

  def namesOfFarmAnimals(animals: Seq[Animal]):Seq[String] = {

    // filter will return a sequence of strings always
    animals.filter{
      // way to check if the animal onto filter is a farm animal.
      // to check the logic of filtering, just go to FarmAnimal singleton
      case FarmAnimal(_) => true
      case _ => false
    }.map {
      // In this case we would just have farm animals, placeholded as 'FarmAnimal'
      // from the filter statement above. For all the cases that we have animals whose age is older than age,
      // return its name
      case FarmAnimal(name,age) if age > 8 =>
        name
      // too old is printed in case the animal age is equal or less than 8, but in any case we ewturn a string
      case _ =>
        "(too Old)"
    }
  }

  def tasksForFarmAnimals(animals: Seq[Animal]): Seq[String] = {
    animals.filter {
      case FarmTask(_,_) =>
        true
      case _ =>
        false
    }. map{
      case FarmTask(Animal(name,_,_),task) =>
        s"Task for $name is $task"
      case _ =>
        ""
    }
  }

  def undeterminedTasksForFarmAnimals(animals: Seq[Animal]): Seq[String] = {
    animals.filter {
      case undeterminedFarmTasks(_,_,_) =>
        true
      case _ =>
        false
    }. map{
      case undeterminedFarmTasks(task1,task2,task3) =>
        s"Tasks are $task1, $task2,$task3"
      case _ =>
        ""
    }
  }

  def namesOfFarmAnimalsWithNestedPatternMatching(animals: Seq[Animal]):Seq[String] = {
    animals.filter{
      case FarmAnimal(_) => true
      case _ => false
    }.map {
      case FarmAnimalWithNestedPatternMatching(Animal(name,age,_)) if age < 8 =>
        name
      case _ =>
        "(too Old)"
    }
  }

}

case class Animal(name:String, age: Int, species: String)

object FarmAnimal {
  def unapply (animal: Animal): Option[(String,Int)] = {
    // if the animal species is a cow, return a Some with the animal name and age
    if(animal.species == "Cow") {
      Some(animal.name,animal.age)
    }else{
      None
    }
  }
}
object FarmAnimalWithNestedPatternMatching {
  def unapply(animal: Animal): Option[Animal] = {
    if(animal.species == "Cow") {
      Some(animal)
    }else{
      None
    }
  }
}

object FarmTask {
  def unapply(animal:Animal): Option[(Animal,String)] = {
    if (animal.species == "Cow") {
      Some((animal,"milking"))
    }else{
      None
    }
  }
}

object undeterminedFarmTasks {
  def unapplySeq(animal:Animal): Option[Seq[String]] = {
    if(animal.species == "Cow"){
      Some(Seq("milking","breeding","eating"))
    }else{
      None
    }
  }
}




