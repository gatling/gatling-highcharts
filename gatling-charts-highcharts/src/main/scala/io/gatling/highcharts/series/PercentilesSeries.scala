/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.core.stats._

class PercentilesSeries(name: String, runStart: Long, data: Iterable[PercentilesVsTimePlot], colors: List[String])
  extends Series[PercentilesVsTimePlot](name, data, colors) {

  def render: String = {
    s"[${data.map(renderPercentilesVsTimePlot).mkString(",")}]"
  }

  def renderPercentilesVsTimePlot(percentilesVsTimePlot: PercentilesVsTimePlot) = {
    def renderPercentiles(percentiles: Percentiles) = s"[${percentiles.productIterator.mkString(",")}]"

    s"[${(runStart + percentilesVsTimePlot.time) / 1000},${percentilesVsTimePlot.percentiles.map(renderPercentiles).getOrElse("null")}]"
  }
}
