/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

class ColumnSeries(name: String, data: List[(String, Int)], colors: List[String]) extends HighchartsSeries[String, Int](name, data, colors) {
	val getXValues = data.map(_._1)

	val getYValues = data.map(_._2)
}