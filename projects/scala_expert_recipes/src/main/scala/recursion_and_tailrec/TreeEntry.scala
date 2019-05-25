package recursion_and_tailrec

trait TreeEntry {
  case class Branch(children: Seq[TreeEntry]) extends TreeEntry
  case class Leaf(content:String = "I am a leaf :D") extends TreeEntry
}
