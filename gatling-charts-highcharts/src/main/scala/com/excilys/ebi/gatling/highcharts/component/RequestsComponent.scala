/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.{ PieSeries, NumberPerSecondSeries }
import com.excilys.ebi.gatling.highcharts.template.RequestsTemplate

object RequestsComponent {

	def apply(allRequests: Series[Long, Int], failedRequests: Series[Long, Int], succeededRequests: Series[Long, Int], pieSeries: Series[String, Int]) = {
		val template = new RequestsTemplate(
			Seq(new NumberPerSecondSeries(allRequests.name, allRequests.data, allRequests.colors.head),
				new NumberPerSecondSeries(failedRequests.name, failedRequests.data, failedRequests.colors.head),
				new NumberPerSecondSeries(succeededRequests.name, succeededRequests.data, succeededRequests.colors.head)),
			new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors))

		new HighchartsComponent(template)
	}
}