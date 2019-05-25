package farm

class Farm(name: String) {
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
}
