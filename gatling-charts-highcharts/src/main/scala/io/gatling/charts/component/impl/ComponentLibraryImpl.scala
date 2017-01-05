/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.charts.component.impl

import com.dongxiguo.fastring.Fastring
import io.gatling.core.stats._
import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.highcharts.component._

class ComponentLibraryImpl extends ComponentLibrary {
  def getAllUsersJs(runStart: Long, series: Series[IntVsTimePlot]): Fastring = new AllUsersComponent(runStart, series).getJavascript
  def getActiveSessionsChartComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component = ActiveUsersComponent(runStart, series)
  def getRequestsChartComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component = RequestsComponent(runStart, counts, pieSeries)
  def getResponsesChartComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component = ResponsesComponent(runStart, counts, pieSeries)
  def getRequestDetailsResponseTimeChartComponent(runStart: Long, responseTimesSuccess: Series[PercentilesVsTimePlot]): Component = RequestDetailsResponseTimeComponent(runStart, responseTimesSuccess)
  def getRequestDetailsResponseTimeDistributionChartComponent(responseTimesSuccess: Series[PercentVsTimePlot], responseTimesFailures: Series[PercentVsTimePlot]): Component = RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
  def getRequestDetailsResponseTimeScatterChartComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component = RequestDetailsResponseTimeScatterComponent(successes, failures)
  def getRequestDetailsIndicatorChartComponent: Component = RequestDetailsIndicatorComponent()
  def getNumberOfRequestsChartComponent(numberOfRequestNames: Int) = NumberOfRequestsComponent(numberOfRequestNames)
  def getGroupDetailsDurationChartComponent(containerId: String, yAxisName: String, runStart: Long, durationsSuccess: Series[PercentilesVsTimePlot]): Component = GroupDetailsDurationComponent(containerId, yAxisName, runStart, durationsSuccess)
  def getGroupDetailsDurationDistributionChartComponent(title: String, containerId: String, durationsSuccess: Series[PercentVsTimePlot], durationsFailure: Series[PercentVsTimePlot]): Component = GroupDetailsDurationDistributionComponent(title, containerId, durationsSuccess, durationsFailure)
}
