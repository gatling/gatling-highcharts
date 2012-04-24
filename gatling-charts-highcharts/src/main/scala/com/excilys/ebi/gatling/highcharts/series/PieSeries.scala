/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import scala.collection.mutable.ArrayBuffer
import com.excilys.ebi.gatling.charts.series.Series

class PieSeries(name: String, data: Seq[(String, Int)], seriesColors: List[String]) extends Series[String, Int](name, data, seriesColors) {

	private val dataWithColors = data.zip(seriesColors).map { case (slice, color) => (slice._1, slice._2, color) }

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "type: 'pie',"
		buffer += "name: '" + name + "',"
		buffer += "data: ["
		if (!data.isEmpty)
			buffer ++= dataWithColors
				.map { case (name, count, color) => new StringBuilder().append("{name: '").append(name).append("', y: ").append(count).append(", color: '").append(color).append("'}").toString }
				.foldLeft(List[String]())((l, v) => "," :: v :: l)
				.tail
				.reverse
		buffer += "]"
	}
}
