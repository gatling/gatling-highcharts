/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.charts.series.Series

class ScatterSeries(name: String, data: Seq[(Int, Int)], color: String) extends Series[Int, Int](name, data, List(color)) {

	def elements: Seq[String] = data match {
		case Nil => List("[]")
		case _ => data.map { case (requestsPerSecond, responseTime) => "[" + requestsPerSecond + "," + responseTime + "]" }
	}
}