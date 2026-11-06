/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

private[highcharts] object Template {

  private def escapeJsChar(c: Char): String =
    c match {
      case '\\' => "\\\\"
      case '\'' => "\\'"
      case '"'  => "\\\""
      case '\n' => "\\n"
      case '\r' => "\\r"
      case '\t' => "\\t"
      // prevent closing the enclosing <script> tag or opening an HTML comment
      case '<' => "\\x3c"
      case '>' => "\\x3e"
      // control characters, and U+2028/U+2029 which are line terminators in older JS engines
      case other if other < ' ' || (other >= 0x2028 && other <= 0x2029) => f"\\u${other.toInt}%04x"
      case other                                                        => other.toString
    }

  /**
   * Render a single-quoted JavaScript string literal that is safe to embed in an inline HTML script block.
   */
  def jsString(s: String): String =
    s.iterator.map(escapeJsChar).mkString("'", "", "'")

  /**
   * Escape text rendered as SVG text by Highcharts (labels, legend, tooltips without useHTML): Highcharts parses tags and then only decodes &lt; and &gt;.
   */
  def svgText(s: String): String =
    s.replace("<", "&lt;").replace(">", "&gt;")

  /**
   * Escape text rendered as real HTML (useHTML: true, or the page markup).
   */
  def escapeHtml(s: String): String =
    s.iterator.map {
      case '&'   => "&amp;"
      case '<'   => "&lt;"
      case '>'   => "&gt;"
      case '"'   => "&quot;"
      case '\''  => "&#39;"
      case other => other.toString
    }.mkString

  /**
   * A Highcharts title using useHTML: true, as a JavaScript string literal.
   */
  def htmlTitle(title: String): String =
    jsString(s"""<span class="chart_title">${escapeHtml(title)}</span>""")
}

private[highcharts] abstract class Template {
  def js: String

  def html: String

  protected def truncateTimestampToSecond(fullTimestamp: Long): Long =
    fullTimestamp / 1000 * 1000
}
