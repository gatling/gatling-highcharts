/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.{ PieSeries, NumberPerSecondSeries }
import com.excilys.ebi.gatling.highcharts.template.RequestsTemplate

object RequestsComponent {

	def apply(runStart: Long, allRequests: Series[Int, Int], failedRequests: Series[Int, Int], succeededRequests: Series[Int, Int], pieSeries: Series[String, Int]) = {
		val template = new RequestsTemplate(
			Seq(new NumberPerSecondSeries(allRequests.name, runStart, allRequests.data, allRequests.colors.head),
				new NumberPerSecondSeries(failedRequests.name, runStart, failedRequests.data, failedRequests.colors.head),
				new NumberPerSecondSeries(succeededRequests.name, runStart, succeededRequests.data, succeededRequests.colors.head)),
			new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors))

		new HighchartsComponent(template)
	}
}