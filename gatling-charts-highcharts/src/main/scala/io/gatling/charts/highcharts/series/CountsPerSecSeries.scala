/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.series

import io.gatling.charts.highcharts.template.Template
import io.gatling.charts.stats._
import io.gatling.charts.util.Color

private[highcharts] final case class CountsPerSecSeries(runStart: Long, data: Iterable[CountsVsTimePlot], colors: List[Color]) {
  val names: Seq[String] = List(Series.All, Series.OK, Series.KO)

  def render: String =
    s"[${data.map(renderCountsVsTimePlot).mkString(",")}]"

  def renderCountsVsTimePlot(countsVsTimePlot: CountsVsTimePlot): String = {
    import countsVsTimePlot._
    s"[${Template.millisToSeconds(runStart + time)},[$total,$oks,$kos]]"
  }
}
