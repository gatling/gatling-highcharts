/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import scala.collection.mutable.ArrayBuffer

import com.excilys.ebi.gatling.charts.series.Series

class NumberPerSecondSeries(name: String, data: Seq[(Long, Int)], color: String) extends Series[Long, Int](name.replace("'", "\\\'"), data, List(color)) {

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		if (!sample.isEmpty)
			buffer ++= sample
				.map { case (time, count) => new StringBuilder().append("[").append(time).append(",").append(count).append("]").toString }
				.foldLeft(List[String]())((l, v) => "," :: v :: l)
				.tail
				.reverse
		buffer
	}
}