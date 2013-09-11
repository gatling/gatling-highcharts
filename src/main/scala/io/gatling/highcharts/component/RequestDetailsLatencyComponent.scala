/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntRangeVsTimePlot, Series }
import io.gatling.highcharts.series.ResponseTimeSeries
import io.gatling.highcharts.template.RequestDetailsLatencyTemplate

object RequestDetailsLatencyComponent {

	def apply(runStart: Long, latencySuccess: Series[IntRangeVsTimePlot], latencyFailures: Series[IntRangeVsTimePlot]) = {
		val template = new RequestDetailsLatencyTemplate(
			new ResponseTimeSeries(latencySuccess.name, runStart, latencySuccess.data, latencySuccess.colors.head),
			new ResponseTimeSeries(latencyFailures.name, runStart, latencyFailures.data, latencyFailures.colors.head))

		new HighchartsComponent(template)
	}
}