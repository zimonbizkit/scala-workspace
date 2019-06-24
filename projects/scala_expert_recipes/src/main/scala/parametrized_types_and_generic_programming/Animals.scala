package parametrized_types_and_generic_programming

 trait Animal

case object Dog extends Animal
case object Cat extends Animal

trait FarmAnimal extends Animal

case object Cow extends FarmAnimal
case object Horse extends FarmAnimal