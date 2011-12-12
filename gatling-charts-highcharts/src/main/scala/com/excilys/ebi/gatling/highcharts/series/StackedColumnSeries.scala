/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.util.StringHelper.END_OF_LINE

class StackedColumnSeries(name: String, data: List[(String, Int)], color: String) extends ColumnSeries(name, data, List(color)) {
	override def toString = new StringBuilder()
		.append("type: 'column',").append(END_OF_LINE)
		.append("color: '").append(colors(0)).append("',").append(END_OF_LINE)
		.append("name: '").append(name).append("',").append(END_OF_LINE)
		.append("data: ").append(data.map { entry => entry._2 }.mkString("[", ",", "]")).append(",").append(END_OF_LINE)
		.append("tooltip: { yDecimals: 0, ySuffix: 'ms' }").toString
}