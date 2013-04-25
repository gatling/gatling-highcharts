/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series

class PieSeries(name: String, data: Seq[(String, Int)], seriesColors: List[String]) extends Series[String, Int](name, data, seriesColors) {

	def elements: Seq[String] = data
		.zip(seriesColors)
		.map { case (slice, color) => (slice._1, slice._2, color) }
		.map { case (name, count, color) => "{name: '" + name + "', y: " + count + ", color: '" + color + "'}" }
}
