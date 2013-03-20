/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

import com.excilys.ebi.gatling.core.result.{ PieSlice, Series }

class PieSeries(name: String, data: Seq[PieSlice], seriesColors: Vector[String]) extends Series[PieSlice](name, data, seriesColors) {

	def elements: Seq[String] = data
		.zip(seriesColors)
		.map { case (slice, color) => (slice.name, slice.value, color) }
		.map { case (name, count, color) => "{name: '" + name + "', y: " + count + ", color: '" + color + "'}" }
}
