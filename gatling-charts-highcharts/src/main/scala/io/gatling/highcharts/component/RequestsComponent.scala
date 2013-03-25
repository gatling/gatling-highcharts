/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntVsTimePlot, PieSlice, Series }
import io.gatling.highcharts.series.{ NumberPerSecondSeries, PieSeries }
import io.gatling.highcharts.template.RequestsTemplate

object RequestsComponent {

	def apply(runStart: Long, allRequests: Series[IntVsTimePlot], failedRequests: Series[IntVsTimePlot], succeededRequests: Series[IntVsTimePlot], pieSeries: Series[PieSlice]) = {
		val template = new RequestsTemplate(
			Seq(new NumberPerSecondSeries(allRequests.name, runStart, allRequests.data, allRequests.colors.head),
				new NumberPerSecondSeries(failedRequests.name, runStart, failedRequests.data, failedRequests.colors.head),
				new NumberPerSecondSeries(succeededRequests.name, runStart, succeededRequests.data, succeededRequests.colors.head)),
			new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors))

		new HighchartsComponent(template)
	}
}