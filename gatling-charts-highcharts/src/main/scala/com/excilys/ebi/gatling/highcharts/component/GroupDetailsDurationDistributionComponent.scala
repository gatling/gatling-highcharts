/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.core.result.{ IntVsTimePlot, PieSlice, Series }
import com.excilys.ebi.gatling.highcharts.series.StackedColumnSeries
import com.excilys.ebi.gatling.highcharts.template.GroupDetailsDurationDistributionTemplate

object GroupDetailsDurationDistributionComponent {

	def apply(durationDistributionSuccess: Series[IntVsTimePlot], durationDistributionFailure: Series[IntVsTimePlot]) = {
		val template = new GroupDetailsDurationDistributionTemplate(
			new StackedColumnSeries(durationDistributionSuccess.name, durationDistributionSuccess.data.map { plot => PieSlice(plot.time.toString, plot.value) }, durationDistributionSuccess.colors.head),
			new StackedColumnSeries(durationDistributionFailure.name, durationDistributionFailure.data.map { plot => PieSlice(plot.time.toString, plot.value) }, durationDistributionFailure.colors.head))

		new HighchartsComponent(template)
	}
}