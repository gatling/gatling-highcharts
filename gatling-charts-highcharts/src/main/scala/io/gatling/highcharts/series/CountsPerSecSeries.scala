/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.charts.stats._
import io.gatling.highcharts.template.Template

final case class CountsPerSecSeries(runStart: Long, data: Iterable[CountsVsTimePlot], colors: List[String]) {

  val names: Seq[String] = List(Series.All, Series.KO, Series.OK)

  def render: String =
    s"[${data.map(renderCountsVsTimePlot).mkString(",")}]"

  def renderCountsVsTimePlot(countsVsTimePlot: CountsVsTimePlot): String = {
    import countsVsTimePlot._
    s"[${Template.millisToSeconds(runStart + time)},[$total,$kos, $oks]]"
  }
}
