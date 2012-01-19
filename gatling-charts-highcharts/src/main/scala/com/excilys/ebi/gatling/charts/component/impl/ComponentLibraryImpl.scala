/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.charts.component.impl

import org.joda.time.DateTime

import com.excilys.ebi.gatling.charts.component.{ ComponentLibrary, Component }
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.component.{ TransactionsComponent, RequestsComponent, RequestDetailsScatterComponent, RequestDetailsResponseTimeComponent, RequestDetailsLatencyComponent, RequestDetailsIndicatorComponent, ActiveSessionsComponent }

class ComponentLibraryImpl extends ComponentLibrary {
	def getActiveSessionsChartComponent(series: Series[DateTime, Int]*): Component = new ActiveSessionsComponent(series: _*)
	def getRequestsChartComponent(allRequests: Series[DateTime, Int], failedRequests: Series[DateTime, Int], succeededRequests: Series[DateTime, Int], pieSeries: Series[String, Int], allActiveSessions: Series[DateTime, Int]): Component = new RequestsComponent(allRequests, failedRequests, succeededRequests, pieSeries, allActiveSessions)
	def getTransactionsChartComponent(allTransactions: Series[DateTime, Int], failedTransactions: Series[DateTime, Int], succeededTransactions: Series[DateTime, Int], pieSeries: Series[String, Int], allActiveSessions: Series[DateTime, Int]): Component = new TransactionsComponent(allTransactions, failedTransactions, succeededTransactions, pieSeries, allActiveSessions)
	def getRequestDetailsResponseTimeChartComponent(responseTimesSuccess: Series[DateTime, Int], responseTimesFailures: Series[DateTime, Int], allActiveSessions: Series[DateTime, Int]): Component = new RequestDetailsResponseTimeComponent(responseTimesSuccess, responseTimesFailures, allActiveSessions)
	def getRequestDetailsLatencyChartComponent(latencySuccess: Series[DateTime, Int], latencyFailures: Series[DateTime, Int], allActiveSessions: Series[DateTime, Int]): Component = new RequestDetailsLatencyComponent(latencySuccess, latencyFailures, allActiveSessions)
	def getRequestDetailsScatterChartComponent(successes: Series[Int, Long], failures: Series[Int, Long]): Component = new RequestDetailsScatterComponent(successes, failures)
	def getRequestDetailsIndicatorChartComponent(columnSeries: Series[String, Int], pieSeries: Series[String, Int]): Component = new RequestDetailsIndicatorComponent(columnSeries, pieSeries)
}