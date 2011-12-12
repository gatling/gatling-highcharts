/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series
import org.joda.time.DateTime
import com.excilys.ebi.gatling.core.util.StringHelper._

class NumberPerSecondSeries(name: String, data: List[(DateTime, Int)], color: String) extends HighchartsSeries[DateTime, Int](name, data, List(color)) {
	override def toString = new StringBuilder()
		.append("name: '").append(name).append("',").append(END_OF_LINE)
		.append("color: '").append(color).append("',").append(END_OF_LINE)
		.append("data: [").append(data.map { entry => "[" + entry._1.getMillis + "," + entry._2 + "]" }.mkString(",")).append("],").append(END_OF_LINE)
		.append("tooltip: { yDecimals: 0, ySuffix: '' }").toString
}