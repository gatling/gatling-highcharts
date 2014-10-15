/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ IntVsTimePlot, Series }

class ScatterSeries(name: String, data: Iterable[IntVsTimePlot], color: String) extends Series[IntVsTimePlot](name, data, List(color)) {

  def elements: Iterable[String] =
    if (data.isEmpty) List("[]")
    else data.map { plot => s"[${plot.time},${plot.value}]" }
}
