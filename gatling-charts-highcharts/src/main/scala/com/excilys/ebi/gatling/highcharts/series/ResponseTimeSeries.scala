/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.core.result.reader.DataReader.NO_PLOT_MAGIC_VALUE

class ResponseTimeSeries(name: String, data: Seq[(Long, (Long, Long))], color: String) extends Series[Long, (Long, Long)](name, data, List(color)) {

	def elements: Seq[String] = data.map { case (time, (responseTimeMin, responseTimeMax)) => "[" + time + "," + valueOrNull(responseTimeMin) + "," + valueOrNull(responseTimeMax) + "]" }

	private def valueOrNull[A](value: A) = value match {
		case NO_PLOT_MAGIC_VALUE => "null"
		case _ => value.toString
	}
}