/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.charts.component.impl

import com.excilys.ebi.gatling.charts.component.{ComponentLibrary, Component}
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.component.{TransactionsComponent, RequestsComponent, RequestDetailsScatterComponent, RequestDetailsResponseTimeComponent, RequestDetailsLatencyComponent, RequestDetailsIndicatorComponent, ActiveSessionsComponent}

class ComponentLibraryImpl extends ComponentLibrary {
	def getActiveSessionsChartComponent(series: Series[Long, Int]*): Component = new ActiveSessionsComponent(series: _*)
	def getRequestsChartComponent(allRequests: Series[Long, Int], failedRequests: Series[Long, Int], succeededRequests: Series[Long, Int], pieSeries: Series[String, Int], allActiveSessions: Series[Long, Int]): Component = new RequestsComponent(allRequests, failedRequests, succeededRequests, pieSeries, allActiveSessions)
	def getTransactionsChartComponent(allTransactions: Series[Long, Int], failedTransactions: Series[Long, Int], succeededTransactions: Series[Long, Int], pieSeries: Series[String, Int], allActiveSessions: Series[Long, Int]): Component = new TransactionsComponent(allTransactions, failedTransactions, succeededTransactions, pieSeries, allActiveSessions)
	def getRequestDetailsResponseTimeChartComponent(responseTimesSuccess: Series[Long, Int], responseTimesFailures: Series[Long, Int], allActiveSessions: Series[Long, Int]): Component = new RequestDetailsResponseTimeComponent(responseTimesSuccess, responseTimesFailures, allActiveSessions)
	def getRequestDetailsLatencyChartComponent(latencySuccess: Series[Long, Int], latencyFailures: Series[Long, Int], allActiveSessions: Series[Long, Int]): Component = new RequestDetailsLatencyComponent(latencySuccess, latencyFailures, allActiveSessions)
	def getRequestDetailsScatterChartComponent(successes: Series[Int, Long], failures: Series[Int, Long]): Component = new RequestDetailsScatterComponent(successes, failures)
	def getRequestDetailsIndicatorChartComponent(columnSeries: Series[String, Int], pieSeries: Series[String, Int]): Component = new RequestDetailsIndicatorComponent(columnSeries, pieSeries)
}