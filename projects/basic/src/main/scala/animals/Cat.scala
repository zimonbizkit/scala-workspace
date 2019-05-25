package animals

case class Cat (name: String, breed: String) extends Animal
{
  def speak = "meow"
  def nameIs : String = this.name
  def breedIs : String = this.breed
}