package animals

class Cat (name: String, breed: String) extends Animal
{
  def speak = "meow"
  def nameIs = this.name
  def breedIs = this.breed
}