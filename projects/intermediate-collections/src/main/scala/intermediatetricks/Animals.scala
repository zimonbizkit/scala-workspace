package intermediatetricks

/*class Wolf extends AnimalTrait {
  override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack farm" :+ "eat animal"
}
class Fox extends AnimalTrait {
  override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack farm" :+ "eat animal" :+ "attack henhouse"
}*/

// Trait Linearization allows us the order of the 'liveOneDay' actions order
class Wolf
  extends Animal // this acts really as an interface as it doesn't implement an starting point
  with EmptyAnimal // this class does as this provides the starting point of an empty seq. I don't like the 'empty animal' name, though
  with WalkingAnimal
    with AttackingAnimal
    with CarnivoreAnimal

/*Anyhow there are a few tradeoffs of trait stacking/linearization. If we don't know details of implementation
 of all the traits and we are not sure of what they're doing, we would be breaking our bottom-most level class by
 blurring separation of concerns. We must avoid side effects by ensuring that our traits  don't have circular
 dependencies between them and that they won't introduce side effects on our final class.*/
class Fox
  extends Animal
    with EmptyAnimal
    with WalkingAnimal
    with AttackingAnimal
    with CarnivoreAnimal
    with HenAttackerAnimal

class Crow
  extends Animal
    with EmptyAnimal
    with FlyingAnimal


class Raven
  extends Animal
  with EmptyAnimal
  with FlyingAnimal
  with CarnivoreAnimal