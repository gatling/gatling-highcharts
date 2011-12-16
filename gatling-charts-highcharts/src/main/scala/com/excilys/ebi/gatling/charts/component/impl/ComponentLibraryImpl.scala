/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.charts.component.impl

import com.excilys.ebi.gatling.charts.component.ComponentLibrary
import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.charts.series.Series
import org.joda.time.DateTime
import com.excilys.ebi.gatling.highcharts.component.RequestDetailsResponseTimeComponent
import com.excilys.ebi.gatling.highcharts.component.ActiveSessionsComponent
import com.excilys.ebi.gatling.highcharts.component.RequestDetailsScatterComponent
import com.excilys.ebi.gatling.highcharts.component.RequestsComponent
import com.excilys.ebi.gatling.highcharts.component.RequestDetailsIndicatorComponent

class ComponentLibraryImpl extends ComponentLibrary {
	def getActiveSessionsChartComponent(series: Series[DateTime, Int]*): Component = new ActiveSessionsComponent(series: _*)
	def getRequestsChartComponent(allRequests: Series[DateTime, Int], failedRequests: Series[DateTime, Int], succeededRequests: Series[DateTime, Int], pieSeries: Series[String, Int], allActiveSessions: Series[DateTime, Int]): Component = new RequestsComponent(allRequests, failedRequests, succeededRequests, pieSeries, allActiveSessions)
	def getRequestDetailsResponseTimeChartComponent(responseTimesSuccess: Series[DateTime, Int], responseTimesFailures: Series[DateTime, Int], allActiveSessions: Series[DateTime, Int]): Component = new RequestDetailsResponseTimeComponent(responseTimesSuccess, responseTimesFailures, allActiveSessions)
	def getRequestDetailsScatterChartComponent(successes: Series[Int, Int], failures: Series[Int, Int]): Component = new RequestDetailsScatterComponent(successes, failures)
	def getRequestDetailsIndicatorChartComponent(columnSeries: Series[String, Int], pieSeries: Series[String, Int]): Component = new RequestDetailsIndicatorComponent(columnSeries, pieSeries)
}