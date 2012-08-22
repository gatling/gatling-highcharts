/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series

class ScatterSeries(name: String, data: Seq[(Long, Long)], color: String) extends Series[Long, Long](name, data, List(color)) {

	def elements: Seq[String] =
		if (sample.isEmpty)
			List("[]")
		else
			sample.map { case (requestsPerSecond, responseTime) => "[" + requestsPerSecond + "," + responseTime + "]" }
}