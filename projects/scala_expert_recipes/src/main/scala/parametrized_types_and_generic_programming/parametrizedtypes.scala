package parametrized_types_and_generic_programming

object parametrizedtypes {
  def main(args: Array[String]): Unit = {
    // parametrized types are kind of types that we could use when we sense that our types are losing fidelity

    logFn("hello",() => 5+5)

    //below won't work as Any is a generic type and we cannot add 5 to a generic type for which we don't know the
    // underlying type.

    //so how can we write a function whose signature is as type-agnostic as possible, and at the same time , let the
    // function understand and accept specific types?

    //logFn("hello",() => 5+5) +5

    // We can do this via parametrized types. We are telling, after the function name, that there will be
    // a generic type (T), that will have its second argument as a function that will return a generic type (T)
    // and return that generic type (T), this way we can ensure that the function usage is agnostic from its definition
    // and that it can be defined regardles its use cases
    //
    // as indicated below // -------------------------------.
    //                                                      |
    parametrizedlogFn("hello",() => 5 +5) + 5   //   |
    nextSection                                        //   |
  }                                                    //   |
  def logFn(name: String,fn: () =>  Any): Any = {  //       |
    println(s"Running $name function ...")         //       |
    val retval = fn()                              //       |
    println("Done running function")               //       |
    retval                                         //       |
  }                                                //       |
  //                    ------------------------------------·
  //                    |                          |   |
  //                    v                          v   v
  def parametrizedlogFn[T](name: String,fn: () =>  T): T= {
    println(s"Running $name function ...")
    val retval = fn()
    println("Done running function")
    retval
  }

  def nextSection = {
    // This will tells us that the argument passed is a collection
    println(inspect(List(1,2,3,4,5)))

    // but we cannot know of which elements is comprised (T). This is because parametrized types can only be known at
    // compile time.

    // a way to workaround this, is via 'Manifest'. This just is telling that we want an implicit parameter that will
    // tells us the type of T at runtime.
    println(minspect(List(1,2,3,4,5)))
  }

  def inspect[T](in: List[T]) = {
    println(in.getClass)
  }
  def minspect[T: Manifest](in: List[T]) = {
    println(manifest[T].erasure)
  }

  // this new inspect, tells at type level that the Type being referred here is not that generic. Instead we are telling
  // that the type T here is a _subtype_ of a FarmAnimal. So it can be used only for the object cow and horse.
  // the '<' character can be used to indicate _covariance_ at function signature level
  //      |
  //      .---------
  //               |
  //               v
  def cminspect[ T <: FarmAnimal: Manifest](in: List[T]) = {
    println(manifest[T].erasure)
  }

  // this is really good to filter at _compile time_ the types that are allowed when calling a specific function


}
