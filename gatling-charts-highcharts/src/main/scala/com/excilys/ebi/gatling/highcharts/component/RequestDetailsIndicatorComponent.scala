/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.{ StackedColumnSeries, PieSeries }
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsIndicatorTemplate

object RequestDetailsIndicatorComponent {
	def apply(columnSeries: Series[String, Int], pieSeries: Series[String, Int]) = {
		val template = {
			val data = columnSeries.data
			val colors = columnSeries.colors
			val emptyStacks = data.map { case (name, _) => (name, 0) }
			new RequestDetailsIndicatorTemplate(
				new StackedColumnSeries("OK", List(data(0), emptyStacks(1), emptyStacks(2), emptyStacks(3)), colors(0)),
				new StackedColumnSeries("Acceptable", List(emptyStacks(0), data(1), emptyStacks(2), emptyStacks(3)), colors(1)),
				new StackedColumnSeries("Off Boundaries", List(emptyStacks(0), emptyStacks(1), data(2), emptyStacks(3)), colors(2)),
				new StackedColumnSeries("Failed", List(emptyStacks(0), emptyStacks(1), emptyStacks(2), data(3)), colors(3)),
				new PieSeries("Percentages", pieSeries.data, pieSeries.colors))
		}

		new HighchartsComponent(template)
	}
}