/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ IntVsTimePlot, Series }
import io.gatling.core.util.StringHelper.RichString

class NumberPerSecondSeries(name: String, runStart: Long, data: Seq[IntVsTimePlot], color: String) extends Series[IntVsTimePlot](name.escapeJsQuoteString, data, List(color)) {

	def elements: Seq[String] = data.map { plot => "[" + (runStart + plot.time) + "," + plot.value + "]" }
}