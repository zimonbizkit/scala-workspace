package parametrized_types_and_generic_programming

import parametrizedtypes._

object SecondMain {
  def main(args: Array[String]): Unit = {
    // why od we get here an interface scala.Product, when we call minspect?
    println(parametrizedtypes.minspect(List(Dog,Cat,Cow,Horse)))

    // when typing a variable inside a context
    val animals : List[Animal] = List(Dog,Cat,Cow,Horse)
    // when inspecting
    println(parametrizedtypes.minspect(animals))

    // we see that the list contains objects that implement the Animal trait.

    // the difference here is that in the minspect call we are able to see the compilers internal view of our
    // type system. When callin the minspect directly with the list as a parameter, we hacve the scala.Product.
    // But if we create a typed variable (List[Animal]) and inspect this, we'll see that this is a specifc list
    // of animals.
  }
}
