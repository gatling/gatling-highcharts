/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.charts.component.impl

import com.dongxiguo.fastring.Fastring

import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.core.result._
import io.gatling.highcharts.component._

class ComponentLibraryImpl extends ComponentLibrary {
  def getAllUsersJs(runStart: Long, series: Series[IntVsTimePlot]): Fastring = new AllUsersComponent(runStart, series).getJavascript
  def getActiveSessionsChartComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component = ActiveUsersComponent(runStart, series)
  def getRequestsChartComponent(runStart: Long, allRequests: Series[IntVsTimePlot], failedRequests: Series[IntVsTimePlot], succeededRequests: Series[IntVsTimePlot], pieSeries: Series[PieSlice]): Component = RequestsComponent(runStart, allRequests, failedRequests, succeededRequests, pieSeries)
  def getResponsesChartComponent(runStart: Long, allResponses: Series[IntVsTimePlot], failedResponses: Series[IntVsTimePlot], succeededResponses: Series[IntVsTimePlot], pieSeries: Series[PieSlice]): Component = ResponsesComponent(runStart, allResponses, failedResponses, succeededResponses, pieSeries)
  def getRequestDetailsResponseTimeChartComponent(runStart: Long, responseTimesSuccess: Series[PercentilesVsTimePlot]): Component = RequestDetailsResponseTimeComponent(runStart, responseTimesSuccess)
  def getRequestDetailsResponseTimeDistributionChartComponent(responseTimesSuccess: Series[PercentVsTimePlot], responseTimesFailures: Series[PercentVsTimePlot]): Component = RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
  def getRequestDetailsLatencyChartComponent(runStart: Long, latencySuccess: Series[PercentilesVsTimePlot]): Component = RequestDetailsLatencyComponent(runStart, latencySuccess)
  def getRequestDetailsResponseTimeScatterChartComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component = RequestDetailsResponseTimeScatterComponent(successes, failures)
  def getRequestDetailsLatencyScatterChartComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component = RequestDetailsLatencyScatterComponent(successes, failures)
  def getRequestDetailsIndicatorChartComponent: Component = RequestDetailsIndicatorComponent()
  def getNumberOfRequestsChartComponent(numberOfRequestNames: Int) = NumberOfRequestsComponent(numberOfRequestNames)
  def getGroupDetailsDurationChartComponent(containerId: String, yAxisName: String, runStart: Long, durationsSuccess: Series[PercentilesVsTimePlot]): Component = GroupDetailsDurationComponent(containerId, yAxisName, runStart, durationsSuccess)
  def getGroupDetailsDurationDistributionChartComponent(title: String, containerId: String, durationsSuccess: Series[PercentVsTimePlot], durationsFailure: Series[PercentVsTimePlot]): Component = GroupDetailsDurationDistributionComponent(title, containerId, durationsSuccess, durationsFailure)
}
