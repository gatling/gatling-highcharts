/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ IntVsTimePlot, Series }

class ScatterSeries(name: String, data: Seq[IntVsTimePlot], color: String) extends Series[IntVsTimePlot](name, data, List(color)) {

	def elements: Seq[String] = data match {
		case Nil => List("[]")
		case _ => data.map { plot => "[" + plot.time + "," + plot.value + "]" }
	}
}