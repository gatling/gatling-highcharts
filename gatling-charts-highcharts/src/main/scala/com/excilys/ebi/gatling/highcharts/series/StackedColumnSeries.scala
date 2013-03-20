/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.result.PieSlice

class StackedColumnSeries(name: String, data: Seq[PieSlice], color: String) extends ColumnSeries(name, data, Vector(color)) {

	def elements: Seq[String] = data.map(_.value.toString)
}