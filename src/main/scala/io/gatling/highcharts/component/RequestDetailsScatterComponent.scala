/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntVsTimePlot, Series }
import io.gatling.highcharts.series.ScatterSeries
import io.gatling.highcharts.template.RequestDetailsScatterTemplate

object RequestDetailsScatterComponent {

	def apply(success: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]) = {
		val template = new RequestDetailsScatterTemplate(
			new ScatterSeries(success.name, success.data, success.colors.head),
			new ScatterSeries(failures.name, failures.data, failures.colors.head))
		new HighchartsComponent(template)
	}
}