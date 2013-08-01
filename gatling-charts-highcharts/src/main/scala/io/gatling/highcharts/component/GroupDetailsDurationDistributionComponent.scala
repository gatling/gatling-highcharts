/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntVsTimePlot, PieSlice, Series }
import io.gatling.highcharts.series.StackedColumnSeries
import io.gatling.highcharts.template.GroupDetailsDurationDistributionTemplate

object GroupDetailsDurationDistributionComponent {

	def apply(title: String, containerId: String, durationDistributionSuccess: Series[IntVsTimePlot], durationDistributionFailure: Series[IntVsTimePlot]) = {
		val template = new GroupDetailsDurationDistributionTemplate(
		    title,
		    containerId,
			new StackedColumnSeries(durationDistributionSuccess.name, durationDistributionSuccess.data.map { plot => PieSlice(plot.time.toString, plot.value) }, durationDistributionSuccess.colors.head),
			new StackedColumnSeries(durationDistributionFailure.name, durationDistributionFailure.data.map { plot => PieSlice(plot.time.toString, plot.value) }, durationDistributionFailure.colors.head))

		new HighchartsComponent(template)
	}
}