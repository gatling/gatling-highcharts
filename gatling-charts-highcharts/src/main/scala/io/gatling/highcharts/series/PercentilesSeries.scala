/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ Percentiles, PercentilesVsTimePlot, Series }

class PercentilesSeries(name: String, runStart: Long, data: Iterable[PercentilesVsTimePlot], colors: List[String]) extends Series[PercentilesVsTimePlot](name, data, colors) {

  def elements(percentile: Percentiles => Int): Iterable[String] =
    data.map { plot => s"[${runStart + plot.time},${plot.percentiles.map(percentile).getOrElse("null")}]" }

  def percentiles0: Iterable[String] = elements(_.percentile0)
  def percentiles25: Iterable[String] = elements(_.percentile25)
  def percentiles50: Iterable[String] = elements(_.percentile50)
  def percentiles75: Iterable[String] = elements(_.percentile75)
  def percentiles80: Iterable[String] = elements(_.percentile80)
  def percentiles85: Iterable[String] = elements(_.percentile85)
  def percentiles90: Iterable[String] = elements(_.percentile90)
  def percentiles95: Iterable[String] = elements(_.percentile95)
  def percentiles99: Iterable[String] = elements(_.percentile99)
  def percentiles100: Iterable[String] = elements(_.percentile100)
}
