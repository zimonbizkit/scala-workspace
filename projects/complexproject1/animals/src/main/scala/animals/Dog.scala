package animals

class Dog (name: String, breed: String) {

  def speak : String = {
   return "woof"
  }

  def nameIs = this.name
  def breedIs = this.breed
}