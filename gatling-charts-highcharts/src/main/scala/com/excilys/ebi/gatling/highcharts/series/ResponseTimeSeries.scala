/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.core.result.reader.DataReader.NO_PLOT_MAGIC_VALUE

class ResponseTimeSeries(name: String, runStart: Long, data: Seq[(Int, (Int, Int))], color: String) extends Series[Int, (Int, Int)](name, data, List(color)) {

	def elements: Seq[String] = data.map { case (time, (responseTimeMin, responseTimeMax)) => "[" + (runStart + time) + "," + valueOrNull(responseTimeMin) + "," + valueOrNull(responseTimeMax) + "]" }

	private def valueOrNull[A](value: A) = value match {
		case NO_PLOT_MAGIC_VALUE => "null"
		case _ => value.toString
	}
}