/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.core.result.{ IntRangeVsTimePlot, Series }
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsLatencyTemplate

object RequestDetailsLatencyComponent {

	def apply(runStart: Long, latencySuccess: Series[IntRangeVsTimePlot], latencyFailures: Series[IntRangeVsTimePlot]) = {
		val template = new RequestDetailsLatencyTemplate(
			new ResponseTimeSeries(latencySuccess.name, runStart, latencySuccess.data, latencySuccess.colors.head),
			new ResponseTimeSeries(latencyFailures.name, runStart, latencyFailures.data, latencyFailures.colors.head))

		new HighchartsComponent(template)
	}
}