/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.result.{ IntVsTimePlot, Series }
import com.excilys.ebi.gatling.core.util.StringHelper.escapeJsQuoteString

class NumberPerSecondSeries(name: String, runStart: Long, data: Seq[IntVsTimePlot], color: String) extends Series[IntVsTimePlot](escapeJsQuoteString(name), data, List(color)) {

	def elements: Seq[String] = data.map { plot => "[" + (runStart + plot.time) + "," + plot.value + "]" }
}