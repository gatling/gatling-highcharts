/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import scala.collection.mutable.ArrayBuffer

import org.joda.time.DateTime

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.core.config.GatlingConfig.CONFIG_CHARTING_MAX_PLOT_PER_SERIE
import com.excilys.ebi.gatling.charts.computer.Computer.AVERAGE_TIME_NO_PLOT_MAGIC_VALUE

class ResponseTimeSeries(name: String, data: List[(DateTime, Int)], color: String) extends Series[DateTime, Int](name, data, List(color)) {

	override def isPlotMandatory(plot: (DateTime, Int)) = plot._2 != AVERAGE_TIME_NO_PLOT_MAGIC_VALUE
	
	def getElements: ArrayBuffer[String] = {
		val buffer = new ArrayBuffer[String]
		buffer += "name: '" + name + "',"
		buffer += "color: '" + color + "',"
		buffer += "pointInterval: 1000,"
		buffer += "data: ["
		if (!sample.isEmpty)
			buffer ++= sample.map {
				entry => new StringBuilder().append("[").append(entry._1.getMillis).append(",").append((if (entry._2 == AVERAGE_TIME_NO_PLOT_MAGIC_VALUE) "null" else entry._2)).append("]").toString
			}.foldLeft(List[String]())((l, v) => "," :: v :: l).tail.reverse
		buffer += "], tooltip: { yDecimals: 0, ySuffix: 'ms' }"
	}
}