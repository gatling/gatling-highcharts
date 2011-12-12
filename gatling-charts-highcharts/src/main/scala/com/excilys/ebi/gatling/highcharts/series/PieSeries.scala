/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.util.StringHelper.END_OF_LINE

class PieSeries(name: String, data: List[(String, Int)], seriesColors: List[String]) extends HighchartsSeries[String, Int](name, data, seriesColors) {

	private val dataWithColors =
		data.zip(seriesColors).map { entry =>
			(entry._1._1, entry._1._2, entry._2)
		}

	override def toString = new StringBuilder()
		.append("type: 'pie',").append(END_OF_LINE)
		.append("name: '").append(name).append("',").append(END_OF_LINE)
		.append("data: [").append(dataWithColors.map { entry => "name: '" + entry._1 + "', y: " + entry._2 + ", color: '" + entry._3 + "'" }.mkString("{", "}, {", "}")).append("]").toString
}
