/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.core.util.StringHelper.escapeJsQuoteString

class NumberPerSecondSeries(name: String, runStart: Long, data: Seq[(Int, Int)], color: String) extends Series[Int, Int](escapeJsQuoteString(name), data, List(color)) {

	def elements: Seq[String] = data.map { case (time, count) => "[" + (runStart + time) + "," + count + "]" }
}