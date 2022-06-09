/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.series

import io.gatling.charts.stats._
import io.gatling.charts.util.Color

private[highcharts] final case class ScatterSeries(name: String, data: Iterable[IntVsTimePlot], color: Color) {

  def elements: Iterable[String] =
    if (data.isEmpty) List("[]")
    else
      data.map { plot =>
        s"[${plot.time},${plot.value}]"
      }
}
