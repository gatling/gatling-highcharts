/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component
import org.joda.time.DateTime
import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.template.RequestsTemplate
import com.excilys.ebi.gatling.highcharts.series.PieSeries
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.series.NumberPerSecondSeries

class RequestsComponent(allRequests: Series[DateTime, Int], failedRequests: Series[DateTime, Int], succeededRequests: Series[DateTime, Int], pieSeries: Series[String, Int], allActiveSessions: Series[DateTime, Int])
	extends HighchartsComponent(
		new RequestsTemplate("Number of requests per second",
			Seq(new NumberPerSecondSeries(allRequests.name, allRequests.data, allRequests.colors.head),
				new NumberPerSecondSeries(failedRequests.name, failedRequests.data, failedRequests.colors.head),
				new NumberPerSecondSeries(succeededRequests.name, succeededRequests.data, succeededRequests.colors.head)),
			new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
			new NumberPerSecondSeries(allActiveSessions.name, allActiveSessions.data, allActiveSessions.colors.head)))