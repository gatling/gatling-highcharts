/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.core.result.{ IntVsTimePlot, Series }
import com.excilys.ebi.gatling.highcharts.series.ScatterSeries
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsScatterTemplate

object RequestDetailsScatterComponent {

	def apply(success: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]) = {
		val template = new RequestDetailsScatterTemplate(
			new ScatterSeries(success.name, success.data, success.colors.head),
			new ScatterSeries(failures.name, failures.data, failures.colors.head))
		new HighchartsComponent(template)
	}
}