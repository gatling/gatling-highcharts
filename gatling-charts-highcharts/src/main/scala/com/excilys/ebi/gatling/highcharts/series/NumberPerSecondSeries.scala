/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.core.util.StringHelper.escapeJsQuoteString

class NumberPerSecondSeries(name: String, data: Seq[(Long, Long)], color: String) extends Series[Long, Long](escapeJsQuoteString(name), data, List(color)) {

	def elements: Seq[String] = data.map { case (time, count) => "[" + time + "," + count + "]" }
}