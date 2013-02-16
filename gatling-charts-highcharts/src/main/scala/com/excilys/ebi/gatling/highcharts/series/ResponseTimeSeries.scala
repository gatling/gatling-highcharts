/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.result.{ IntRangeVsTimePlot, Series }
import com.excilys.ebi.gatling.core.result.reader.DataReader.NO_PLOT_MAGIC_VALUE

class ResponseTimeSeries(name: String, runStart: Long, data: Seq[IntRangeVsTimePlot], color: String) extends Series[IntRangeVsTimePlot](name, data, List(color)) {

	def elements: Seq[String] = data.map { plot => "[" + (runStart + plot.time) + "," + valueOrNull(plot.lower) + "," + valueOrNull(plot.higher) + "]" }

	private def valueOrNull[A](value: A) = value match {
		case NO_PLOT_MAGIC_VALUE => "null"
		case _ => value.toString
	}
}