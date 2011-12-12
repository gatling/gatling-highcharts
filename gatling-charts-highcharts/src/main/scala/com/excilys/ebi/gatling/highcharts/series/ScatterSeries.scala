/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.util.StringHelper.END_OF_LINE

class ScatterSeries(name: String, data: List[(Int, Int)], color: String) extends HighchartsSeries[Int, Int](name, data, List(color)) {
	override def toString = new StringBuilder()
		.append("type: 'scatter',").append(END_OF_LINE)
		.append("color: '").append(color).append("',").append(END_OF_LINE)
		.append("name: '").append(name).append("',").append(END_OF_LINE)
		.append("data: [").append(data.map { entry => entry._1 + "," + entry._2 }.mkString("[", "], [", "]")).append("]")
		.toString
}