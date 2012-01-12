/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series
import org.joda.time.DateTime
import com.excilys.ebi.gatling.core.util.StringHelper.END_OF_LINE
import scala.collection.mutable.ArrayBuffer

class ResponseTimeSeries(name: String, data: List[(DateTime, Int)], color: String) extends HighchartsSeries[DateTime, Int](name, data, List(color)) {

	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "name: '" + name + "',"
		buffer += "color: '" + color + "',"
		buffer += "pointInterval: 1000,"
		buffer += "data: ["
		if (!data.isEmpty)
			buffer ++= data.map {
				entry => new StringBuilder().append("[").append(entry._1.getMillis).append(",").append((if (entry._2 == -1) "null" else entry._2)).append("]").toString
			}.foldLeft(List[String]())((l, v) => "," :: v :: l).tail.reverse
		buffer += "], tooltip: { yDecimals: 0, ySuffix: 'ms' }"
	}
}