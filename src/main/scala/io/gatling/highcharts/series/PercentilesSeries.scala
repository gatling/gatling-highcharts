/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ PercentilesVsTimePlot, Series }
import io.gatling.core.result.reader.DataReader.NoPlotMagicValue

class PercentilesSeries(name: String, runStart: Long, data: Seq[PercentilesVsTimePlot], colors: List[String]) extends Series[PercentilesVsTimePlot](name, data, colors) {

  def elements(percentile: PercentilesVsTimePlot => Int): Seq[String] = {

      // FIXME doesn't seem to be properly dealt with upstream
      def valueOrNull(value: Int) = value match {
        case NoPlotMagicValue => "null"
        case _                => value.toString
      }

    data.map { plot => s"[${runStart + plot.time},${valueOrNull(percentile(plot))}]" }
  }

  def percentiles0: Seq[String] = elements(_.percentile0)
  def percentiles25: Seq[String] = elements(_.percentile25)
  def percentiles50: Seq[String] = elements(_.percentile50)
  def percentiles75: Seq[String] = elements(_.percentile75)
  def percentiles80: Seq[String] = elements(_.percentile80)
  def percentiles85: Seq[String] = elements(_.percentile85)
  def percentiles90: Seq[String] = elements(_.percentile90)
  def percentiles95: Seq[String] = elements(_.percentile95)
  def percentiles99: Seq[String] = elements(_.percentile99)
  def percentiles100: Seq[String] = elements(_.percentile100)
}
