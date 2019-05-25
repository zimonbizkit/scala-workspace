package recursion_and_tailrec

object main extends App with TreeEntry {

  override def main(args: Array[String]): Unit = {
    // creating a tree

    val tree = Branch(
      List(
        Leaf("Hello"),
        Branch(
          List(Leaf("hi"))
        ),
        Leaf("Bye")
      )
    )
    println(uniqueContent(tree))

    val secondtree = Branch(
      List(
        Leaf("Hello"),
        Branch(
          List(Leaf("hi"))
        ),
        Leaf("Hello")
      )
    )


    // shouldnt this be not good as it omits content with the same name?
    println(uniqueContent(secondtree))

    var deepNest = Branch(Seq.empty)

    // we are creating a tree with 4000 levels of nesting below.
    for (i <- 1 to 4000) {
      deepNest = Branch(Seq(deepNest))
    }
    // we would expect the below function to have an empty set as a result as it has no leaves, but it crashes
    // with a stack overflow error, as there's a limit of nested invocations you can make in JVM

    //       println(uniqueContent(deepNest))

    // the function tailrecOptimisedUniqueContent is the same function, but it's in fact  optimized for "tail recursion"
    // You can refer to the function body to see differences from the original uniqueContent function

    println(tailrecOptimisedUniqueContent(Seq(tree)))
    println(tailrecOptimisedUniqueContent(Seq(secondtree)))

    // the scala compiler is smart enough to know if a recursive function is optimised for tail recursion, but if we want
    // to be sure that a function should be marked as 'tail recursive'. However, if the function is not really optimised to
    // be tail recursive, even though we can mark recursive functions like that, we will have a compile error telling
    // us that the function is not optimised for that purpose. You can put tailrec annotation in 'uniqueContent' like shown
    // below to see the compile error:

    // @annotation.tailrec
    // def uniqueContent(root: TreeEntry): Set[String] = {
    println(tailrecOptimisedUniqueContent(Seq(deepNest)))

    // then WHY is the annotation useful for? for the compiler?

  }
  def uniqueContent(root: TreeEntry): Set[String] = {
    root match {
      case Branch(children) =>
        children.foldLeft(Set[String]())(_ ++ uniqueContent(_))
      case Leaf(content) =>
        Set(content)
    }
  }
  @annotation.tailrec
  def tailrecOptimisedUniqueContent(currentLevel: Seq[TreeEntry], seenSoFar:Set[String]= Set.empty): Set[String] = {
    currentLevel match {
      case Seq(Branch(children), rest @_*) =>
        tailrecOptimisedUniqueContent(children ++ rest ,seenSoFar )
      case Seq(Leaf(content), rest @_*) =>
        tailrecOptimisedUniqueContent(rest, seenSoFar+ content)
      case _ =>
        seenSoFar
    }
  }

}
