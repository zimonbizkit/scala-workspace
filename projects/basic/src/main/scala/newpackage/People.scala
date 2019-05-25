package newpackage

object People {
  var personList :Seq[Person] = Seq.empty
  def addPerson(person: Person) ={
    personList  = personList :+ person
  }
}
