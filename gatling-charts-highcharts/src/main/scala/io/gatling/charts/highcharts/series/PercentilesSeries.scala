/*
 * Copyright 2011-2024 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.series

import io.gatling.charts.stats._
import io.gatling.charts.util.Color

private[highcharts] final case class PercentilesSeries(name: String, runStart: Long, data: Iterable[PercentilesVsTimePlot], colors: List[Color]) {
  def render: String =
    s"[${data.map(renderPercentilesVsTimePlot).mkString(",")}]"

  def renderPercentilesVsTimePlot(percentilesVsTimePlot: PercentilesVsTimePlot): String = {
    def renderPercentiles(percentiles: Percentiles) = {
      import percentiles._
      s"[$percentile0,$percentile25,$percentile50,$percentile75,$percentile80,$percentile85,$percentile90,$percentile95,$percentile99,$percentile100]"
    }

    s"[${(runStart + percentilesVsTimePlot.time) / 1000},${percentilesVsTimePlot.percentiles.map(renderPercentiles).getOrElse("null")}]"
  }
}
