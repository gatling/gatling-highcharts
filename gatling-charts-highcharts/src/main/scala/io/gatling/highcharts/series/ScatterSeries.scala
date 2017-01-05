/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.stats._

class ScatterSeries(name: String, data: Iterable[IntVsTimePlot], color: String) extends Series[IntVsTimePlot](name, data, List(color)) {

  def elements: Iterable[String] =
    if (data.isEmpty) List("[]")
    else data.map { plot => s"[${plot.time},${plot.value}]" }
}
