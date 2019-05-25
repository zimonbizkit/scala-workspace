package intermediatetricks

// This lesson just shows trait stacking and the caveats of long class hierarchies.
// Note the 'Animal' and 'EmptyAnimal' implementation. As long as we are having 'abstract override'
// in the bottom-most implementations, we need a common initial implementation for the 'liveOneDay'
// method. This is not done by the trait 'Animal', as it acts just as an interface for all the underlying 
// traits that have a specific implementation.

trait Animal {def liveOneDay : Seq[String]}

trait EmptyAnimal extends Animal {
  override def liveOneDay :Seq[String] = Seq.empty
}

trait WalkingAnimal extends Animal {
  abstract override def liveOneDay: Seq[String] = super.liveOneDay :+ "walk"
}

trait FlyingAnimal extends Animal {
  abstract override def liveOneDay: Seq[String] = super.liveOneDay :+ "fly"
}

trait AttackingAnimal extends Animal {
  abstract override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack farm"
}
trait CarnivoreAnimal extends Animal {
  abstract override def liveOneDay: Seq[String] = super.liveOneDay :+ "eat animal"
}

trait HenAttackerAnimal extends Animal {
  abstract override def liveOneDay: Seq[String] = super.liveOneDay :+ "attack the hen"
}

