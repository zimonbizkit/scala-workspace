package animals

case class Dog (name: String, breed: String) extends Animal {

  def speak = "woof"

  def nameIs : String = this.name
  def breedIs : String = this.breed
}