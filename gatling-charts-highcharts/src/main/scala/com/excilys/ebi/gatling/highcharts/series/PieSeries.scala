/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series

class PieSeries(name: String, data: Seq[(String, Int)], seriesColors: List[String]) extends Series[String, Int](name, data, seriesColors) {

	def elements: Seq[String] = data
		.zip(seriesColors)
		.map { case (slice, color) => (slice._1, slice._2, color) }
		.map {
			case (name, count, color) => new StringBuilder()
				.append("{name: '")
				.append(name)
				.append("', y: ")
				.append(count)
				.append(", color: '")
				.append(color)
				.append("'}")
				.toString
		}
}
