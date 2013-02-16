/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.result.{ PieSlice, Series }

class ColumnSeries(name: String, data: Seq[PieSlice], colors: List[String]) extends Series[PieSlice](name, data, colors) {
	val getXValues = data.map(_.name)
	val getYValues = data.map(_.value)
}