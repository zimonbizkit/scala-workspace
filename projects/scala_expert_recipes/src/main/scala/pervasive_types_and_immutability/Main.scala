package pervasive_types_and_immutability

object Main {
  def main(args: Array[String]): Unit = {
    // In scala, pervasive typing philosophy implies ->
    // _representing as much as possible about what your function is doing and you value is capturing_

    val int = 3 // Integer
    val myNumber = 3 //Integer

    // this is wnough for us to understand the range of values that variables can contain (from min Integer to max Integer)
    // in scala

    // this function definition tells us that will return a variable of type integer
    def buildNumber :Int = 3

    // the function below also, specifies that we can return either an Integer , or a None

    def buildANullableNumber: Option[Int] = Some(3)

    // However, the function above tells us by typing that can be a range of integers, but not a specific '3' number.
    // A good practice for doing so, is to extend the typing system by implementing our custom types. A way to do this is by
    // implementing case objects (see case object Three, below). This way we can limit very specifically the number of values
    // or the value that your function will return.

    // if we want to implement a function whose return value is exactly the three object, we could do it as below
    // however, this does not compile

    //def buildExactlyAThree : Three = Three

    // the function below sums two integers
    def doAddition  = {
      val n1 = buildANullableNumber
      val n2 = buildANullableNumber

      // if we do operation on values returned by functions that can return Option[Int], the current functions return
      // type becomes the return value of the functions called, so now IntellijIDEA suggests the return type to be
      // Option[Int] as 'buildNullableNumber' returns that specific value
      // for {
      //   n1 <- buildANullableNumber
      //   n2 <- buildANullableNumber
      // } yield {
      //   n1 + n2
      // }

      // However, we signed 'doAddition' to return an Int, and we should fix the 'None' part of the function, which we can
      // do with a pattern match. Now it'll always return a number

      (buildANullableNumber, buildANullableNumber) match {
        case (Some(a),Some(b)) => a + b
        case _ => 0
      }

    }

    // to measure how long it takes the function to run, we'll use a 'timer' function that will measure the execution in
    // milliseconds. However, it fails because of a type mismatch

    //  timer(doAddition)

    // if we do as below

    //  timer(buildNullableNumber)

    // we will have a type mismatch between Int & Option[Int]. In this part, the instructor is telling that including
    // a '_' character will make the parameter an actual function instead of picking the value that returns 'buildNullableNumber'
    // this thing is not explained, but anyhow we'll use it

    timer(doAddition _)

    // the function below should measure the time that the function has taken to execute

    // Important to note that this function takes, as the only argument, a function (maybe closure) that returns an int,
    // and eventually it will return an int. The function or closure is specified as parenthesis in the arguments section
    // like (this) and the return type of the function, like (this)
    //        |____        ____________________________________|
    //             |      |
    //             v      v
    //def timer(fn: () => Int): Int = {

    // however, we are not being flexible on the typing system here, so we need to specify that the function will accept
    // a function whose first parameter is of any type, and will return any type of parameter as the result, like below
    //   ¨¨¨¨¨|¨¨       ¨¨¨|¨   _________________________________|¨¨¨¨¨¨¨¨¨¨¨¨¨¨
    //        |            |   |
    //        v            v   v
    def timer[T](fn: () => T): T = {
      val t1  = System.currentTimeMillis
      val retval = fn()
      val t2 = System.currentTimeMillis

      println(t2 - t1)

      retval

    }

    // exemplification of pervasive types with type hierarchy shown below
    // (see case objects and traits below to see the typing structure)

    val l = List(Dog,Cat,Cow,Horse)
    // the type of this , in  the video, is shown as below ->
    // List[Product with Serialiåble with Animal]

    // the type of this is Ok
    val la : List[Animal] = List(Dog,Cat,Cow,Horse)

    // but the type of below is not (and thus is commented code) because Dog and Cat objects are not of type FarmAnimal

    // val invalidList : List[FarmAnimal] = List(Dog,Cat,Cow,Horse)

    // If you have a list of different types (a collection of various types in the end) and you want to act on only _some_
    // of the elements of that collection, you would use the .collect function. We act with the List[Animal] created above

    println( // <-----------------------------------------------------------------__________________________________
      la.collect {                                                             // | When writing immutable code one
      // this just does a pattern match for one specific type of the list.     // | of the main gains to aim is to avoid
      case fa: FarmAnimal => fa                                                // | side-effecting as much as possible.
      }                                                                        // | However, when using specific functions
    )                                                                          // | to act on other systems (stdin|out,writing
    // The result of this print should be                                      // | to a log, sending a message...) it can
    // List[FarmAnimal] = List(Cow,Horse)                                      // | introduce side-effecting into a side-effect
  }                                                                            // | free code. In this case, despite we are acting
}                                                                              // | on an rather immutable collection ,we are
                                                                               // | printing it on the standard output, thus
trait Animal                                                                   // | side-effecting our code. A way to prevent
                                                                               // | this would be to provide a common access point
trait FarmAnimal extends Animal                                                // | to printing values and leave the 'la.collect'
case object Dog extends Animal                                                 // | part isolated, and using the print function in
case object Cat extends Animal                                                 // | another code block.
case object Cow extends FarmAnimal                                             // .________________________________
case object Horse extends FarmAnimal

case object Three {
  def toInt = 3
}