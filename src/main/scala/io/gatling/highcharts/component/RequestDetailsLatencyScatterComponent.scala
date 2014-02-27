/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntVsTimePlot, Series }
import io.gatling.highcharts.series.ScatterSeries
import io.gatling.highcharts.template.RequestDetailsScatterTemplate

object RequestDetailsLatencyScatterComponent {

	def apply(success: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]) = {
		val template = new RequestDetailsScatterTemplate(
			new ScatterSeries(success.name, success.data, success.colors.head),
			new ScatterSeries(failures.name, failures.data, failures.colors.head),
			"container_latency_dispersion",
			"Latency against the Global Number of Requests per Second",
			"Latency")
		new HighchartsComponent(template)
	}
}
