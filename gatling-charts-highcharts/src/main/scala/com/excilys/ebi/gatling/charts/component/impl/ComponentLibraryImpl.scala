/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.charts.component.impl

import com.excilys.ebi.gatling.charts.component.{ ComponentLibrary, Component }
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.component._

class ComponentLibraryImpl extends ComponentLibrary {
	def getAllSessionsJs(runStart: Long, series: Series[Int, Int]): String = new AllSessionsComponent(runStart, series).getJavascript
	def getActiveSessionsChartComponent(runStart: Long, series: Seq[Series[Int, Int]]): Component = ActiveSessionsComponent(runStart, series)
	def getRequestsChartComponent(runStart: Long, allRequests: Series[Int, Int], failedRequests: Series[Int, Int], succeededRequests: Series[Int, Int], pieSeries: Series[String, Int]): Component = RequestsComponent(runStart, allRequests, failedRequests, succeededRequests, pieSeries)
	def getTransactionsChartComponent(runStart: Long, allTransactions: Series[Int, Int], failedTransactions: Series[Int, Int], succeededTransactions: Series[Int, Int], pieSeries: Series[String, Int]): Component = TransactionsComponent(runStart, allTransactions, failedTransactions, succeededTransactions, pieSeries)
	def getRequestDetailsResponseTimeChartComponent(runStart: Long, responseTimesSuccess: Series[Int, (Int, Int)], responseTimesFailures: Series[Int, (Int, Int)]): Component = RequestDetailsResponseTimeComponent(runStart, responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsResponseTimeDistributionChartComponent(responseTimesSuccess: Series[Int, Int], responseTimesFailures: Series[Int, Int]): Component = RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsLatencyChartComponent(runStart: Long, latencySuccess: Series[Int, (Int, Int)], latencyFailures: Series[Int, (Int, Int)]): Component = RequestDetailsLatencyComponent(runStart, latencySuccess, latencyFailures)
	def getRequestDetailsScatterChartComponent(successes: Series[Int, Int], failures: Series[Int, Int]): Component = RequestDetailsScatterComponent(successes, failures)
	def getRequestDetailsIndicatorChartComponent: Component = RequestDetailsIndicatorComponent()
	def getNumberOfRequestsChartComponent = NumberOfRequestsComponent()
	def getGroupDurationChartComponent(runStart: Long, durations: Series[Int, (Int, Int)]): Component = GroupDetailsDurationComponent(runStart, durations)
	def getGroupDetailsDurationDistributionChartComponent(durations: Series[Int, Int]): Component = GroupDetailsDurationDistributionComponent(durations)
}