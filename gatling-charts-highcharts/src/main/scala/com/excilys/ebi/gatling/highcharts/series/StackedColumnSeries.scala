/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import scala.collection.mutable.ArrayBuffer

class StackedColumnSeries(name: String, data: Seq[(String, Int)], color: String) extends ColumnSeries(name, data, List(color)) {

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "type: 'column',"
		buffer += "color: '" + colors(0) + "',"
		buffer += "name: '" + name + "',"
		buffer += "data: ["
		if (!data.isEmpty)
			buffer ++= data
				.map { case (_, count) => count.toString }
				.foldLeft(List[String]())((l, v) => "," :: v :: l)
				.tail
				.reverse
		buffer += "], tooltip: { yDecimals: 0, ySuffix: 'ms' }"
	}
}