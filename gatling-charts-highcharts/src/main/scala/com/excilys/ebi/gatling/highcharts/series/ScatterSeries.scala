/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import scala.collection.mutable.ArrayBuffer
import com.excilys.ebi.gatling.charts.series.Series

class ScatterSeries(name: String, data: List[(Int, Long)], color: String) extends Series[Int, Long](name, data, List(color)) {

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "type: 'scatter',"
		buffer += "color: '" + color + "',"
		buffer += "name: '" + name + "',"
		buffer += "data: ["
		if (data.isEmpty)
			buffer += "[]"
		else
			buffer ++= data.map {
				entry => new StringBuilder().append("[").append(entry._1).append(",").append(entry._2).append("]").toString
			}.foldLeft(List[String]())((l, v) => "," :: v :: l).tail.reverse
		buffer += "]"
	}
}