/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import com.dongxiguo.fastring.Fastring.Implicits._
import io.gatling.core.stats._

class PercentilesSeries(name: String, runStart: Long, data: Iterable[PercentilesVsTimePlot], colors: List[String])
  extends Series[PercentilesVsTimePlot](name, data, colors) {

  def render: Fastring = {
    fast"[${data.map(renderPercentilesVsTimePlot).mkString(",")}]"
  }

  def renderPercentilesVsTimePlot(percentilesVsTimePlot: PercentilesVsTimePlot) = {
    def renderPercentiles(percentiles: Percentiles) = fast"[${percentiles.productIterator.mkString(",")}]"

    fast"[${(runStart + percentilesVsTimePlot.time) / 1000},${percentilesVsTimePlot.percentiles.map(renderPercentiles).getOrElse("null")}]"
  }
}
