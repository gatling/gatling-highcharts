/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.charts.component.impl

import com.excilys.ebi.gatling.charts.component.{ Component, ComponentLibrary }
import com.excilys.ebi.gatling.core.result.{ IntRangeVsTimePlot, IntVsTimePlot, PieSlice, Series }
import com.excilys.ebi.gatling.highcharts.component._

class ComponentLibraryImpl extends ComponentLibrary {
	def getAllSessionsJs(runStart: Long, series: Series[IntVsTimePlot]): String = new AllSessionsComponent(runStart, series).getJavascript
	def getActiveSessionsChartComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component = ActiveSessionsComponent(runStart, series)
	def getRequestsChartComponent(runStart: Long, allRequests: Series[IntVsTimePlot], failedRequests: Series[IntVsTimePlot], succeededRequests: Series[IntVsTimePlot], pieSeries: Series[PieSlice]): Component = RequestsComponent(runStart, allRequests, failedRequests, succeededRequests, pieSeries)
	def getTransactionsChartComponent(runStart: Long, allTransactions: Series[IntVsTimePlot], failedTransactions: Series[IntVsTimePlot], succeededTransactions: Series[IntVsTimePlot], pieSeries: Series[PieSlice]): Component = TransactionsComponent(runStart, allTransactions, failedTransactions, succeededTransactions, pieSeries)
	def getRequestDetailsResponseTimeChartComponent(runStart: Long, responseTimesSuccess: Series[IntRangeVsTimePlot], responseTimesFailures: Series[IntRangeVsTimePlot]): Component = RequestDetailsResponseTimeComponent(runStart, responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsResponseTimeDistributionChartComponent(responseTimesSuccess: Series[IntVsTimePlot], responseTimesFailures: Series[IntVsTimePlot]): Component = RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
	def getRequestDetailsLatencyChartComponent(runStart: Long, latencySuccess: Series[IntRangeVsTimePlot], latencyFailures: Series[IntRangeVsTimePlot]): Component = RequestDetailsLatencyComponent(runStart, latencySuccess, latencyFailures)
	def getRequestDetailsScatterChartComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component = RequestDetailsScatterComponent(successes, failures)
	def getRequestDetailsIndicatorChartComponent: Component = RequestDetailsIndicatorComponent()
	def getNumberOfRequestsChartComponent = NumberOfRequestsComponent()
	def getGroupDurationChartComponent(runStart: Long, durationsSuccess: Series[IntRangeVsTimePlot], durationsFailure: Series[IntRangeVsTimePlot]): Component = GroupDetailsDurationComponent(runStart, durationsSuccess, durationsFailure)
	def getGroupDetailsDurationDistributionChartComponent(durationsSuccess: Series[IntVsTimePlot], durationsFailure: Series[IntVsTimePlot]): Component = GroupDetailsDurationDistributionComponent(durationsSuccess, durationsFailure)
}