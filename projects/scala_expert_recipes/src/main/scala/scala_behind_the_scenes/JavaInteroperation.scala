package scala_behind_the_scenes

// importing java utils to scala
import java.util.{List => Jlist, LinkedList => JlinkedList}

// despite interoperation between J and S collections is not permitted out of the box
// importing the library below will allow you to "bridge" treatment of collections of one platform
// to another

import scala.collection.JavaConverters

object JavaInteroperation {
  def main(args: Array[String]): Unit = {
    // instantiating java utils from scala and using them
    // java lists are mutable
    val javaList : Jlist[String] = new JlinkedList[String]

    javaList.add("a")
    javaList.add("b")

    // now instantiating a native scala list
    // sacla lists are immutable
    val MySlist : List[String] = List[String]("a","b")

    // the list instantiated below fails because:
    // 1.- mutability between java and scala differ
    // 2.- interfaces of lists on java/scala are different from one another

    // val mySList2: List[String] = myJList

    // also you can't (obviously) call scala methods on java lists
    // Supposedly, vice versa too

    // javaList.filter(_ == "a")
    

    // I smell that interoperability between types in java and scala might be a tricky thing to maintain
    // in the future as mutable state between java collections disguised as scala (and vice versa)
    // will have side effects on its (scala or java) language counterpart.
  }
}
