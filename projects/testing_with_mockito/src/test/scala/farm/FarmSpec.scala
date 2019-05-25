package farm

import org.specs2._
import org.specs2.mutable.Specification
import org.specs2.matcher._
import org.specs2.mock._
import org.specs2.scalacheck._
import org.scalacheck._
import org.specs2.specification._
import org.specs2.execute._
import java.time._

// THIS IS NOT WORKING
// seemingly mockito is not well imported here as it doesnt mock tasker class
// properly, I don't know why

// It works now as the sbt from the docker container is well configured with the proper version
// of both scala and sbt, so these tests should be run inside the sbt shell in the container, instead
// of inside the IntellijIDEA scala version & sbt shell


// sugars to put some beforescenarios and afterscenarios for some use case test cleaning purposes
// in form of a trait that extends aroundeach functionality
trait PrintTimes extends AroundEach {
  def around[R: AsResult](r: =>R): Result = {
    var startTime : LocalDateTime = LocalDateTime.now
    println("Starting at"+ startTime)
    val result = AsResult(r)
    println("Ending at"+ LocalDateTime.now + "From start at" + startTime)

    result
  }
}

class FarmSpec
  extends Specification
    with Mockito
    with ScalaCheck
    with PrintTimes {
    // the function below allows to run the tests of the specification sequentially
    // rather than concurrently , which is the default behaviour of the spec

  sequential

  var numberOfExamples = 0




  "Farm" should {
    val chicken = Animal("Bob",12,Chicken)
    val cow = Animal("Bessy",12,Cow)
    val horse = Animal("Douglas",12,Horse)
    val dog = Animal("Cleo",12,Dog)
    val cat = Animal("Santiago",12,Cat)


    "extract all tasks for multiple animals for one day" in {
      // here we define the mock
      val tasker = mock[FarmTasker]

      val farm = new Farm("A farm",tasker)

      // here we define the method expectation that we want our mock to return
      tasker.tasksForTheDay(anyInt) returns Seq(
        FarmTask(chicken,"checking for egss",12),
        FarmTask(cow,"milking",12),
        FarmTask(horse,"plowing",12)
      )

      // this is the ACT of our unit test. We equal the method call to the sequence at
      // right with the _must comparator
      farm.runForDays(13, 1) must_== Seq(
        FarmTask(chicken,"checking for egss",12),
        FarmTask(cow,"milking",12),
        FarmTask(horse,"plowing",12)
      )

      // this is a nice method spy that lets us assert that an internal method from the mock
      // was called, anyInt is a wildcard that does a placeholder for the paramter type
      // such as it happens with the PHP prophecy's Argument::type('int')
      there was one(tasker).tasksForTheDay(anyInt)
    }

    "extract for two days" in {
      // the same as above, but with the method spy asserting on two colls rather tham just one
      val tasker = mock[FarmTasker]

      val farm = new Farm("A farm",tasker)

      tasker.tasksForTheDay(anyInt) returns Seq(
        FarmTask(chicken,"checking for egss",12),
        FarmTask(cow,"milking",12),
        FarmTask(horse,"plowing",12)
      )

      farm.runForDays(13,2) must_== Seq(
        FarmTask(chicken,"checking for egss",12),
        FarmTask(cow,"milking",12),
        FarmTask(horse,"plowing",12),
        FarmTask(chicken,"checking for egss",12),
        FarmTask(cow,"milking",12),
        FarmTask(horse,"plowing",12)
      )

      // sugary syntax here
      there was 2.times(tasker).tasksForTheDay(anyInt)
    }

    /*"runs correctly in any number of days " in prop { days:Int  => (days >0 && days <= 365) ==> {
      val tasker = mock[FarmTasker]

      val farm = new Farm("A farm", tasker)

      val basicTask = FarmTask(chicken, "checking for egss", 12)
      tasker.tasksForTheDay(anyInt) returns Seq(basicTask)

      val farmRun = farm.runForDays(13,days)

      farmRun must have size days
      farmRun.filter(_ == basicTask) must have size days
      //there was days.times(tasker).tasksForTheDay(13)
    }
    }*/
    "runs correctly in a limited number of days " in Prop.forAll(Gen.choose(0,365)) { days:Int  => {
      val tasker = mock[FarmTasker]

      val farm = new Farm("A farm", tasker)

      val basicTask = FarmTask(chicken, "checking for egss", 12)
      tasker.tasksForTheDay(anyInt) returns Seq(basicTask)

      val farmRun = farm.runForDays(13,days)

      farmRun must have size days
      farmRun.filter(_ == basicTask) must have size days
      //there was days.times(tasker).tasksForTheDay(13)
    }
    }

    "only passes in correwct max age every other day"  in {
      val tasker = mock[FarmTasker]

      val farm = new Farm("A farm",tasker)

      tasker.tasksForTheDay(anyInt) returns Seq()

      farm.runForDays(12,2)


      there was 2.times(tasker).tasksForTheDay(12)
      there was 1.times(tasker).tasksForTheDay(500)
    }.pendingUntilFixed("Not quite ready to build this yet")
  }
}
