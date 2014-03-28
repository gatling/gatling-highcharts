/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.charts.component.impl

import com.dongxiguo.fastring.Fastring

import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.core.result.{ IntRangeVsTimePlot, IntVsTimePlot, PieSlice, Series }
import io.gatling.highcharts.component._

class ComponentLibraryImpl extends ComponentLibrary {
  def getAllSessionsJs(runStart: Long, series: Series[IntVsTimePlot]): Fastring = new AllSessionsComponent(runStart, series).getJavascript
  def getActiveSessionsChartComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component = ActiveSessionsComponent(runStart, series)
  def getRequestsChartComponent(runStart: Long, allRequests: Series[IntVsTimePlot], failedRequests: Series[IntVsTimePlot], succeededRequests: Series[IntVsTimePlot], pieSeries: Series[PieSlice]): Component = RequestsComponent(runStart, allRequests, failedRequests, succeededRequests, pieSeries)
  def getResponsesChartComponent(runStart: Long, allResponses: Series[IntVsTimePlot], failedResponses: Series[IntVsTimePlot], succeededResponses: Series[IntVsTimePlot], pieSeries: Series[PieSlice]): Component = ResponsesComponent(runStart, allResponses, failedResponses, succeededResponses, pieSeries)
  def getRequestDetailsResponseTimeChartComponent(runStart: Long, responseTimesSuccess: Series[IntRangeVsTimePlot], responseTimesFailures: Series[IntRangeVsTimePlot]): Component = RequestDetailsResponseTimeComponent(runStart, responseTimesSuccess, responseTimesFailures)
  def getRequestDetailsResponseTimeDistributionChartComponent(responseTimesSuccess: Series[IntVsTimePlot], responseTimesFailures: Series[IntVsTimePlot]): Component = RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
  def getRequestDetailsLatencyChartComponent(runStart: Long, latencySuccess: Series[IntRangeVsTimePlot], latencyFailures: Series[IntRangeVsTimePlot]): Component = RequestDetailsLatencyComponent(runStart, latencySuccess, latencyFailures)
  def getRequestDetailsResponseTimeScatterChartComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component = RequestDetailsResponseTimeScatterComponent(successes, failures)
  def getRequestDetailsLatencyScatterChartComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component = RequestDetailsLatencyScatterComponent(successes, failures)
  def getRequestDetailsIndicatorChartComponent: Component = RequestDetailsIndicatorComponent()
  def getNumberOfRequestsChartComponent(numberOfRequestNames: Int) = NumberOfRequestsComponent(numberOfRequestNames)
  def getGroupDurationChartComponent(title: String, containerId: String, yAxisName: String, runStart: Long, durationsSuccess: Series[IntRangeVsTimePlot], durationsFailure: Series[IntRangeVsTimePlot]): Component = GroupDetailsDurationComponent(title, containerId, yAxisName, runStart, durationsSuccess, durationsFailure)
  def getGroupDetailsDurationDistributionChartComponent(title: String, containerId: String, durationsSuccess: Series[IntVsTimePlot], durationsFailure: Series[IntVsTimePlot]): Component = GroupDetailsDurationDistributionComponent(title, containerId, durationsSuccess, durationsFailure)
}
