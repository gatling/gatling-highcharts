/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import scala.collection.mutable.ArrayBuffer

import com.excilys.ebi.gatling.charts.series.Series

class ScatterSeries(name: String, data: Seq[(Int, Long)], color: String) extends Series[Int, Long](name, data, List(color)) {

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "type: 'scatter',"
		buffer += "color: '" + color + "',"
		buffer += "name: '" + name + "',"
		buffer += "data: ["
		if (sample.isEmpty)
			buffer += "[]"
		else
			buffer ++= sample
				.map { case (requestsPerSecond, responseTime) => new StringBuilder().append("[").append(requestsPerSecond).append(",").append(responseTime).append("]").toString }
				.foldLeft(List[String]())((l, v) => "," :: v :: l)
				.tail
				.reverse
		buffer += "]"
	}
}