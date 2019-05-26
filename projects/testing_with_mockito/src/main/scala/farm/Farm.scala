package farm

class Farm(name: String, tasker: FarmTasker) {
  def tasksForTheDay(animals: Seq[Animal]):Seq[FarmTask] = {
    animals.flatMap{
      case animal @ Animal(_,age, Cow) =>
        Seq(FarmTask(animal,"milking",age))
      case animal @ Animal(_,age, Chicken) =>
        Seq(FarmTask(animal,"checking for eggs",age))
      case animal @ Animal(_,age, Horse) =>
        Seq(FarmTask(animal,"plowing",age))
      case animal @ Animal(_,age, Dog) =>
        Seq(FarmTask(animal,"herding",age))
      case animal @ Animal(_,_,Wolf) =>
        throw new Exception("Wolves not allowed")
      case _ =>
        Seq()

    }
  }
  def runForDays(maxAge: Int, days: Int): Seq[FarmTask] = {
    for {
      day <- 1 to days
      task <- tasker.tasksForTheDay(maxAge)
    } yield {
      task
    }
  }
}

class FarmTasker(animals: Seq[Animal]) {
  // tasksForTheDay is repeated just to comply with the mocking lesson
  // for the learning path
  def tasksForTheDay(maxAge: Int) : Seq[FarmTask] = {
    animals.flatMap{
      case animal @ Animal(_,age, Cow) if animal.age < maxAge =>
        Seq(FarmTask(animal,"milking",age))
      case animal @ Animal(_,age, Chicken) if animal.age < maxAge =>
        Seq(FarmTask(animal,"checking for eggs",age))
      case animal @ Animal(_,age, Horse) if animal.age < maxAge =>
        Seq(FarmTask(animal,"plowing",age))
      case animal @ Animal(_,age, Dog) if animal.age < maxAge =>
        Seq(FarmTask(animal,"herding",age))
      case animal @ Animal(_,_,Wolf) if animal.age < maxAge =>
        throw new Exception("Wolves not allowed")
      case _ =>
        Seq()

    }
  }
}
