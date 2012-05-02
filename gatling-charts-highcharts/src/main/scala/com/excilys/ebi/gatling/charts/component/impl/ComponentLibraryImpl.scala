/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.charts.component.impl

import com.excilys.ebi.gatling.charts.component.{ ComponentLibrary, Component }
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.component.{ AllSessionsComponent, TransactionsComponent, RequestsComponent, RequestDetailsScatterComponent, RequestDetailsResponseTimeComponent, RequestDetailsLatencyComponent, RequestDetailsIndicatorComponent, ActiveSessionsComponent }
import com.excilys.ebi.gatling.highcharts.component.RequestDetailsResponseTimeDistributionComponent

class ComponentLibraryImpl extends ComponentLibrary {
	def getAllSessionsJs(series: Series[Long, Int]): String = new AllSessionsComponent(series).getJavascript
	def getActiveSessionsChartComponent(series: Seq[Series[Long, Int]]): Component = new ActiveSessionsComponent(series)
	def getRequestsChartComponent(allRequests: Series[Long, Int], failedRequests: Series[Long, Int], succeededRequests: Series[Long, Int], pieSeries: Series[String, Int]): Component = new RequestsComponent(allRequests, failedRequests, succeededRequests, pieSeries)
	def getTransactionsChartComponent(allTransactions: Series[Long, Int], failedTransactions: Series[Long, Int], succeededTransactions: Series[Long, Int], pieSeries: Series[String, Int]): Component = new TransactionsComponent(allTransactions, failedTransactions, succeededTransactions, pieSeries)
	def getRequestDetailsResponseTimeChartComponent(responseTimesSuccess: Series[Long, Long], responseTimesFailures: Series[Long, Long]): Component = new RequestDetailsResponseTimeComponent(responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsResponseTimeDistributionChartComponent(responseTimesSuccess: Series[Long, Int], responseTimesFailures: Series[Long, Int]): Component = new RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsLatencyChartComponent(latencySuccess: Series[Long, Long], latencyFailures: Series[Long, Long]): Component = new RequestDetailsLatencyComponent(latencySuccess, latencyFailures)
	def getRequestDetailsScatterChartComponent(successes: Series[Int, Long], failures: Series[Int, Long]): Component = new RequestDetailsScatterComponent(successes, failures)
	def getRequestDetailsIndicatorChartComponent(columnSeries: Series[String, Int], pieSeries: Series[String, Int]): Component = new RequestDetailsIndicatorComponent(columnSeries, pieSeries)
}