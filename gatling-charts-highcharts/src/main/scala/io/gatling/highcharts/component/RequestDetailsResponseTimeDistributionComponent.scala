/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntVsTimePlot, PieSlice, Series }
import io.gatling.highcharts.series.StackedColumnSeries
import io.gatling.highcharts.template.RequestDetailsResponseTimeDistributionTemplate

object RequestDetailsResponseTimeDistributionComponent {

	def apply(successDistribution: Series[IntVsTimePlot], failuresDistribution: Series[IntVsTimePlot]) = {
		val template = new RequestDetailsResponseTimeDistributionTemplate(
			new StackedColumnSeries(successDistribution.name, successDistribution.data.map { plot => PieSlice(plot.time.toString, plot.value) }, successDistribution.colors.head),
			new StackedColumnSeries(failuresDistribution.name, failuresDistribution.data.map { plot => PieSlice(plot.time.toString, plot.value) }, failuresDistribution.colors.head))

		new HighchartsComponent(template)
	}
}