package patternmatching

object patternmatching {
  def main(args: Array[String]): Unit = {
    println("Describing none...")
    describeAge(None)
    println("Describing some...")
    println(describeAge(Some(9)))
    println(describeAge(Some(16)))
    println(describeAge(Some(30)))

    println(describePerson("Antonio",None))
    println(describePerson("Antonio",Some(25)))
    println(describePerson("Antonio",Some(8)))
  }

  def describeAge(maybeAge: Option[Int]): String = {
    maybeAge match {
      case None =>
        s"IDK your age"
      case Some(age) if age < 25 =>
        s"You are still learning at $age years old"
      case Some(age) if age < 10 =>
        s"You are $age years young!"
      case Some(age) =>
          s"You are damn old when you are $age. Get over it"
    }
  }

  def describePerson(person: (String, Option[Int])) : String = {
    person match {
      case (name,None) =>
        s"$name : IDK your age"
      case (name,Some(age @ (5 | 8))) =>
        s" $name is particularly $age y o"
      case (name,Some(age)) if age < 10 =>
        s" $name, you are $age years young"
      case (name,Some(age)) if age < 25 =>
        s" $name, you are still learning with $age years"
      case (name,Some(age)) =>
        s" Old fat mf'ing $name you are $age years old"
    }
  }
}
