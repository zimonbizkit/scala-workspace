package caseclasses

object caseclasses {
  def main(args: Array[String]): Unit = {
    // bad way of creating other instances from objects with different vals
    // or to update vals from an instance of a class
    // Without the case
    val an1 = new Animal("Edu",12,"Dog")
    print(an1.name)
    val an2 = new Animal(an1.name, 13, an1.species)
    print(an2.name)

    // case class would be good for DDD value objects?
    val an3 =
      println(describeAnial(CaseAnimal("BLABLA",12,"Cat")))
    println(describeAnial(CaseAnimal("BLABLA",12,"Dog")))
    println(describeAnial(CaseAnimal("BLABLA",12,"Suricata")))
    val caseAn1 = CaseAnimal("SuricataCopiedCaseWithTwelveYearsOld",12,"Suricata")
    // you can update values of old case objects by calling the copy method
    val copiedCaseAn = caseAn1.copy(age = 8)
    println(describeAnial(copiedCaseAn))


    val classifiedCat = ClassifiedAnimal(
      "Mengano",
      12,
      Classification("felix","felicis")
    )

    println(describeClassifiedAnimal(classifiedCat))

    // righthand variable declaration with pattern matching with a yet created object!
    // As far as I understand : this takes values from classifiedCat, and deconstructs the
    // classified cat object in the pattern we specify on the left hand of the operation
    // telling that this is a "classified animal, from which we want the patternmatched name,
    // and the patternmatched species
    val ClassifiedAnimal(patternMatchedName,_,Classification(_,patternMatchedSpecies)) =  classifiedCat

    println(s" pattern matched name is $patternMatchedName")
    println(s" pattern matched species is $patternMatchedSpecies")

  }
  private def describeAnial(animal: CaseAnimal): String = {
    // matches should case the most restrictive scenario first!
    // going to the least restrictive case eventually, case by case
    animal match {
      case CaseAnimal(name,age,"Dog") if age > 10 =>
        s"The cat $name is $age y o woof woof and is old"
      case CaseAnimal(name,age,"Cat") =>
        s"The cat $name is $age y o"
      case CaseAnimal(name,age,species) =>
        s"Name is $name, will be ${age+2} yo, species is $species"
    }
  }

  private def describeClassifiedAnimal(animal: ClassifiedAnimal) : String = {
    animal match {
      // nested pattern matching inside the expression!!!
      case ClassifiedAnimal(name,age,Classification(vc1@"felix",vc2@"felicis")) =>
        // variable name assigning with @ looks cool as, in the case of patternmatching on a function
        // you can pass the variable name instead of a fixed value
        s"Animal is like a cat, called$name, is $age y o and is a $vc1 the genus $vc2"

      case ClassifiedAnimal(name,age,Classification(genus,species)) =>
        s"wtf is this shit!!!!!: $name, $age, is a $species of genus $genus"

    }
  }
}

class Animal(val name: String , val age: Int, val species: String)
case class CaseAnimal(name: String ,age: Int, species: String)
case class Classification(genus: String, species: String)
case class ClassifiedAnimal(name: String, age:Int, species: Classification)
