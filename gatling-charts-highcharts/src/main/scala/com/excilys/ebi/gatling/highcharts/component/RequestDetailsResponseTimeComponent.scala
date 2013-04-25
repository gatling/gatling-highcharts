/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsResponseTimeTemplate

object RequestDetailsResponseTimeComponent {

	def apply(runStart: Long, responseTimesSuccess: Series[Int, (Int, Int)], responseTimesFailures: Series[Int, (Int, Int)]) = {
		val template = new RequestDetailsResponseTimeTemplate(
			new ResponseTimeSeries(responseTimesSuccess.name, runStart, responseTimesSuccess.data, responseTimesSuccess.colors.head),
			new ResponseTimeSeries(responseTimesFailures.name, runStart, responseTimesFailures.data, responseTimesFailures.colors.head))

		new HighchartsComponent(template)
	}
}