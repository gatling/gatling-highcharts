/*
 * Copyright 2011-2020 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.core.stats._

final case class PercentilesSeries(name: String, runStart: Long, data: Iterable[PercentilesVsTimePlot], colors: List[String]) {

  def render: String =
    s"[${data.map(renderPercentilesVsTimePlot).mkString(",")}]"

  def renderPercentilesVsTimePlot(percentilesVsTimePlot: PercentilesVsTimePlot): String = {
    def renderPercentiles(percentiles: Percentiles) = s"[${percentiles.productIterator.mkString(",")}]"

    s"[${(runStart + percentilesVsTimePlot.time) / 1000},${percentilesVsTimePlot.percentiles.map(renderPercentiles).getOrElse("null")}]"
  }
}
