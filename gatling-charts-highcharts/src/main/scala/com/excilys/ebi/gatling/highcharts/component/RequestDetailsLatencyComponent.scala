/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsLatencyTemplate

object RequestDetailsLatencyComponent {

	def apply(latencySuccess: Series[Long, Long], latencyFailures: Series[Long, Long]) = {
		val template = new RequestDetailsLatencyTemplate(
			new ResponseTimeSeries(latencySuccess.name, latencySuccess.data, latencySuccess.colors.head),
			new ResponseTimeSeries(latencyFailures.name, latencyFailures.data, latencyFailures.colors.head))

		new HighchartsComponent(template)
	}
}