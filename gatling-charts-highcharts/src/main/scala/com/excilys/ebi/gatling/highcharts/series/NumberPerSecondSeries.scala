/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series

class NumberPerSecondSeries(name: String, data: Seq[(Long, Int)], color: String) extends Series[Long, Int](name.replace("'", "\\\'"), data, List(color)) {

	def elements: Seq[String] = sample.map {
		case (time, count) =>
			new StringBuilder()
				.append("[")
				.append(time)
				.append(",")
				.append(count)
				.append("]")
				.toString
	}
}