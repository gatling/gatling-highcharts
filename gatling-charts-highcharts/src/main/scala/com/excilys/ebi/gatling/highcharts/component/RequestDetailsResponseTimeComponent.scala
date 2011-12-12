/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component
import org.joda.time.DateTime
import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsResponseTimeTemplate
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.series.NumberPerSecondSeries

class RequestDetailsResponseTimeComponent(responseTimesSuccess: Series[DateTime, Int], responseTimesFailures: Series[DateTime, Int], allActiveSessions: Series[DateTime, Int])
	extends HighchartsComponent(
		new RequestDetailsResponseTimeTemplate(
			"Response time during simulation",
			new ResponseTimeSeries(responseTimesSuccess.name, responseTimesSuccess.data, responseTimesSuccess.colors.head),
			new ResponseTimeSeries(responseTimesFailures.name, responseTimesFailures.data, responseTimesFailures.colors.head),
			new NumberPerSecondSeries(allActiveSessions.name, allActiveSessions.data, allActiveSessions.colors.head)))