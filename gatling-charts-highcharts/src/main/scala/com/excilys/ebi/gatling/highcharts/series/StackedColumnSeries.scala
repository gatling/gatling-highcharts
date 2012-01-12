/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.util.StringHelper.END_OF_LINE
import scala.collection.mutable.ArrayBuffer

class StackedColumnSeries(name: String, data: List[(String, Int)], color: String) extends ColumnSeries(name, data, List(color)) {

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "type: 'column',"
		buffer += "color: '" + colors(0) + "',"
		buffer += "name: '" + name + "',"
		buffer += "data: ["
		if (!data.isEmpty)
			buffer ++= data.map {
				entry => entry._2.toString
			}.foldLeft(List[String]())((l, v) => "," :: v :: l).tail.reverse
		buffer += "], tooltip: { yDecimals: 0, ySuffix: 'ms' }"
	}
}