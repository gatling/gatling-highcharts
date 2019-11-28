/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.core.stats._

final case class ScatterSeries(name: String, data: Iterable[IntVsTimePlot], color: String) {

  def elements: Iterable[String] =
    if (data.isEmpty) List("[]")
    else
      data.map { plot =>
        s"[${plot.time},${plot.value}]"
      }
}
