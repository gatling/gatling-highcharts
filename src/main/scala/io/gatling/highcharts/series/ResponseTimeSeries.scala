/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ IntRangeVsTimePlot, Series }
import io.gatling.core.result.reader.DataReader.NO_PLOT_MAGIC_VALUE

class ResponseTimeSeries(name: String, runStart: Long, data: Seq[IntRangeVsTimePlot], color: String) extends Series[IntRangeVsTimePlot](name, data, List(color)) {

	def elements: Seq[String] = {

		def valueOrNull[A](value: A) = value match {
			case NO_PLOT_MAGIC_VALUE => "null"
			case _ => value.toString
		}

		data.map { plot => "[" + (runStart + plot.time) + "," + valueOrNull(plot.lower) + "," + valueOrNull(plot.higher) + "]" }
	}
}