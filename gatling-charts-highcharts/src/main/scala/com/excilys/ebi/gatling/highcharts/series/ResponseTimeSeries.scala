/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.charts.util.StatisticsHelper.NO_PLOT_MAGIC_VALUE

class ResponseTimeSeries(name: String, data: Seq[(Long, Long)], color: String) extends Series[Long, Long](name, data, List(color)) {

	override def isPlotMandatory(plot: (Long, Long)) = plot._2 != NO_PLOT_MAGIC_VALUE

	def elements: Seq[String] = sample.map {
		case (time, responseTime) =>
			new StringBuilder()
				.append("[")
				.append(time)
				.append(",")
				.append((if (responseTime == NO_PLOT_MAGIC_VALUE) "null" else responseTime))
				.append("]")
				.toString
	}
}