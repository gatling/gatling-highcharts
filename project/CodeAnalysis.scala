import sbt.Keys._
import sbt._
import wartremover.WartRemover.autoImport._
import wartremover.Wart

object CodeAnalysis {
  lazy val settings = Seq(
    Compile / compile / wartremoverErrors := Warts.allBut(disabledWarts: _*),
    Test / compile / wartremoverErrors := (Compile / compile / wartremoverErrors).value
  )

  private def disabledWarts: List[Wart] =
    List(
      Wart.Overloading,
      Wart.IterableOps,
      Wart.Serializable, // breaks JMS's Serializable usage, looks like a bug
      Wart.StringPlusAny // see https://github.com/wartremover/wartremover/issues/447
    )
}
