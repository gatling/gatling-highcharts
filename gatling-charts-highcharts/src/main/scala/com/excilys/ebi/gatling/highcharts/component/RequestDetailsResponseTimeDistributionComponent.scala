/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.core.result.{ IntVsTimePlot, PieSlice, Series }
import com.excilys.ebi.gatling.highcharts.series.StackedColumnSeries
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsResponseTimeDistributionTemplate

object RequestDetailsResponseTimeDistributionComponent {

	def apply(successDistribution: Series[IntVsTimePlot], failuresDistribution: Series[IntVsTimePlot]) = {
		val template = new RequestDetailsResponseTimeDistributionTemplate(
			new StackedColumnSeries(successDistribution.name, successDistribution.data.map { plot => PieSlice(plot.time.toString, plot.value) }, successDistribution.colors.head),
			new StackedColumnSeries(failuresDistribution.name, failuresDistribution.data.map { plot => PieSlice(plot.time.toString, plot.value) }, failuresDistribution.colors.head))

		new HighchartsComponent(template)
	}
}