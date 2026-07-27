import sbt.Keys.*
import sbt.*
import wartremover.WartRemover.autoImport.*
import wartremover.Wart

object CodeAnalysis {
  lazy val settings: Seq[Def.Setting[?]] = Seq(
    Compile / compile / wartremoverErrors := Warts.allBut(disabledWarts *),
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
