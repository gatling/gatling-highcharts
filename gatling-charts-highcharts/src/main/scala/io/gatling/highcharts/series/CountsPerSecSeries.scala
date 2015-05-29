/**
 * Copyright 2011-2015 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.highcharts.template.Template

import com.dongxiguo.fastring.Fastring.Implicits._
import io.gatling.core.stats._

case class CountsPerSecSeries(runStart: Long, data: Iterable[CountsVsTimePlot], colors: List[String]) {

  val names = List(Series.All, Series.KO, Series.OK)

  def render: Fastring =
    fast"[${data.map(renderCountsVsTimePlot).mkString(",")}]"

  def renderCountsVsTimePlot(countsVsTimePlot: CountsVsTimePlot) = {
    import countsVsTimePlot._
    fast"[${Template.millisToSeconds(runStart + time)},[$total,$kos, $oks]]"
  }
}
