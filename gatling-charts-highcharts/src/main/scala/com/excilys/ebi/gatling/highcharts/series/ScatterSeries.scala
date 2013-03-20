/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.result.{ IntVsTimePlot, Series }

class ScatterSeries(name: String, data: Seq[IntVsTimePlot], color: String) extends Series[IntVsTimePlot](name, data, Vector(color)) {

	def elements: Seq[String] = data match {
		case x if x.isEmpty => Vector("[]")
		case _ => data.map { plot => "[" + plot.time + "," + plot.value + "]" }
	}
}