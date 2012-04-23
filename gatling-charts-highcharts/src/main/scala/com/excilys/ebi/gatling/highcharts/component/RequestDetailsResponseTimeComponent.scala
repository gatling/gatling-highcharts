/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.{ResponseTimeSeries, NumberPerSecondSeries}
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsResponseTimeTemplate

class RequestDetailsResponseTimeComponent(responseTimesSuccess: Series[Long, Long], responseTimesFailures: Series[Long, Long], allActiveSessions: Series[Long, Int])
	extends HighchartsComponent(
		new RequestDetailsResponseTimeTemplate(
			new ResponseTimeSeries(responseTimesSuccess.name, responseTimesSuccess.data, responseTimesSuccess.colors.head),
			new ResponseTimeSeries(responseTimesFailures.name, responseTimesFailures.data, responseTimesFailures.colors.head),
			new NumberPerSecondSeries(allActiveSessions.name, allActiveSessions.data, allActiveSessions.colors.head)))