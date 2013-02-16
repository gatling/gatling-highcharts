/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.core.result.{ IntRangeVsTimePlot, Series }
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsResponseTimeTemplate

object RequestDetailsResponseTimeComponent {

	def apply(runStart: Long, responseTimesSuccess: Series[IntRangeVsTimePlot], responseTimesFailures: Series[IntRangeVsTimePlot]) = {
		val template = new RequestDetailsResponseTimeTemplate(
			new ResponseTimeSeries(responseTimesSuccess.name, runStart, responseTimesSuccess.data, responseTimesSuccess.colors.head),
			new ResponseTimeSeries(responseTimesFailures.name, runStart, responseTimesFailures.data, responseTimesFailures.colors.head))

		new HighchartsComponent(template)
	}
}