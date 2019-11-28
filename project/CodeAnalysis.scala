import sbt.Keys._
import sbt._
import wartremover.WartRemover.autoImport._
import wartremover.Wart

object CodeAnalysis {

  lazy val settings = Seq(
    wartremoverErrors in (Compile, compile) := Warts.allBut(disabledWarts: _*),
    wartremoverErrors in (Test, compile) := (wartremoverErrors in (Compile, compile)).value
  )

  private def disabledWarts: List[Wart] =
    List(
      Wart.Overloading,
      Wart.TraversableOps
    )
}
