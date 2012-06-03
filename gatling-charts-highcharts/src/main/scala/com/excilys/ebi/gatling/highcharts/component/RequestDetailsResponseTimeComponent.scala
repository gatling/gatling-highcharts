/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsResponseTimeTemplate

object RequestDetailsResponseTimeComponent {

	def apply(responseTimesSuccess: Series[Long, Long], responseTimesFailures: Series[Long, Long]) = {
		val template = new RequestDetailsResponseTimeTemplate(
			new ResponseTimeSeries(responseTimesSuccess.name, responseTimesSuccess.data, responseTimesSuccess.colors.head),
			new ResponseTimeSeries(responseTimesFailures.name, responseTimesFailures.data, responseTimesFailures.colors.head))

		new HighchartsComponent(template)
	}
}