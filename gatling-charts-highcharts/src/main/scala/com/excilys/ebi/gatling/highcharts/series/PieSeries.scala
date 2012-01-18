/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import scala.collection.mutable.ArrayBuffer
import com.excilys.ebi.gatling.charts.series.Series

class PieSeries(name: String, data: List[(String, Int)], seriesColors: List[String]) extends Series[String, Int](name, data, seriesColors) {

	private val dataWithColors =
		data.zip(seriesColors).map { entry =>
			(entry._1._1, entry._1._2, entry._2)
		}

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "type: 'pie',"
		buffer += "name: '" + name + "',"
		buffer += "data: ["
		if (!data.isEmpty)
			buffer ++= dataWithColors.map {
				entry => new StringBuilder().append("{name: '").append(entry._1).append("', y: ").append(entry._2).append(", color: '").append(entry._3).append("'}").toString
			}.foldLeft(List[String]())((l, v) => "," :: v :: l).tail.reverse

		buffer += "]"
	}
}
