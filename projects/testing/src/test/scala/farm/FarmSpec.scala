package farm

import org.specs2._
import org.specs2.mutable.Specification
import org.specs2.matcher._

class FarmSpec extends Specification {
  // simple testing below, no specific matchers, nothing like that
  "Farm" should {

    def performAnAction(description: String): Matcher[FarmTask] = { task: FarmTask =>
      task.description == description
    }

    val farm = new Farm("a Farm")
    "->extract the proper tasks for a chicken" in {
      val chick = Animal("Bob",12,Chicken)

      farm.tasksForTheDay(Seq(chick)).head must performAnAction("checking for eggs")

      farm.tasksForTheDay(Seq(chick)) must_==Seq(FarmTask(chick,"checking for eggs",12))
    }
    "->extract the proper tasks for a horse" in {
      val horse = Animal("Manolo",12,Horse)
      farm.tasksForTheDay(Seq(horse)) must_==Seq(FarmTask(horse,"plowing",12))
    }
    "->extract the proper tasks for a cow" in {
      val cow = Animal("Manolo",12,Cow)
      farm.tasksForTheDay(Seq(cow)) must_==Seq(FarmTask(cow,"milking",12))
    }

    "->extract the proper tasks for a dog or cat" in {
      val dog = Animal("Manolo",12,Dog)
      val cat = Animal("Manolo",12,Cat)
      farm.tasksForTheDay(Seq(dog)) must_== Seq(FarmTask(dog,"herding",12))
      farm.tasksForTheDay(Seq(cat)) must beEmpty
    }
    "extracl all tasks for multiple animals" in {
      val chick = Animal("Bob",12,Chicken)
      val horse = Animal("Manolo",12,Horse)
      val cow = Animal("Manolo",12,Cow)
      farm.tasksForTheDay(Seq(chick,horse,cow)) must have size 3
      farm.tasksForTheDay(Seq(chick,horse,cow)) must_==
      Seq(
        FarmTask(chick,"checking for eggs",12),
        FarmTask(horse,"plowing",12),
        FarmTask(cow,"milking",12)
      )

    }
    // use belike with moderation as it tinkers on with the error description of failing tests
    "extract the correct task descriptions for multiple animals" in {
      val chick = Animal("Bob",12,Chicken)
      val horse = Animal("Manolo",12,Horse)
      val cow = Animal("Manolo",12,Cow)
      farm.tasksForTheDay(Seq(chick,horse,cow)) must have size 3
      farm.tasksForTheDay(Seq(chick,horse,cow)) must beLike {
        case Seq(
        FarmTask(_, chickenDescription,_),
        FarmTask(_, horseDescription,_),
        FarmTask(_, cowDescription,_),
        ) =>
          chickenDescription must_== "checking for eggs"
          cowDescription must_== "milking"
          horseDescription must_== "plowing"
      }
    }
    "assigns animals as the animal of each task" in {
      val chick = Animal("Bob",12,Chicken)
      farm.tasksForTheDay(Seq(chick)).map(_.animal) must contain(beAnInstanceOf[Animal])
    }


    "assigns ages as the duration of each task" in {
      val chick = Animal("Bob",12,Chicken)
      val horse = Animal("Manolo",12,Horse)
      val cow = Animal("Manolo",12,Cow)

      farm.tasksForTheDay(Seq(chick)).map(_.duration) must contain(12)
      farm.tasksForTheDay(Seq(chick)).map(_.duration) must contain(be_>(11))
    }
    "Creating a wolf throws an exception" in {
      val wolf = Animal("Jack",12,Wolf)
      farm.tasksForTheDay(Seq(wolf)) must throwAn[Exception]
    }
  }
}
