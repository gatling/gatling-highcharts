/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.charts.component.impl

import com.excilys.ebi.gatling.charts.component.{ ComponentLibrary, Component }
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.component.{ TransactionsComponent, RequestsComponent, RequestDetailsScatterComponent, RequestDetailsResponseTimeDistributionComponent, RequestDetailsResponseTimeComponent, RequestDetailsLatencyComponent, RequestDetailsIndicatorComponent, AllSessionsComponent, ActiveSessionsComponent }

class ComponentLibraryImpl extends ComponentLibrary {
	def getAllSessionsJs(series: Series[Long, Long]): String = new AllSessionsComponent(series).getJavascript
	def getActiveSessionsChartComponent(series: Seq[Series[Long, Long]]): Component = ActiveSessionsComponent(series)
	def getRequestsChartComponent(allRequests: Series[Long, Long], failedRequests: Series[Long, Long], succeededRequests: Series[Long, Long], pieSeries: Series[String, Long]): Component = RequestsComponent(allRequests, failedRequests, succeededRequests, pieSeries)
	def getTransactionsChartComponent(allTransactions: Series[Long, Long], failedTransactions: Series[Long, Long], succeededTransactions: Series[Long, Long], pieSeries: Series[String, Long]): Component = TransactionsComponent(allTransactions, failedTransactions, succeededTransactions, pieSeries)
	def getRequestDetailsResponseTimeChartComponent(responseTimesSuccess: Series[Long, (Long, Long)], responseTimesFailures: Series[Long, (Long, Long)]): Component = RequestDetailsResponseTimeComponent(responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsResponseTimeDistributionChartComponent(responseTimesSuccess: Series[Long, Long], responseTimesFailures: Series[Long, Long]): Component = RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsLatencyChartComponent(latencySuccess: Series[Long, (Long, Long)], latencyFailures: Series[Long, (Long, Long)]): Component = RequestDetailsLatencyComponent(latencySuccess, latencyFailures)
	def getRequestDetailsScatterChartComponent(successes: Series[Long, Long], failures: Series[Long, Long]): Component = RequestDetailsScatterComponent(successes, failures)
	def getRequestDetailsIndicatorChartComponent: Component = RequestDetailsIndicatorComponent()
}