/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.component.impl

import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.charts.stats._
import io.gatling.highcharts.component._

class ComponentLibraryImpl extends ComponentLibrary {
  def getAllUsersJs(runStart: Long, series: Series[IntVsTimePlot]): String = new AllUsersComponent(runStart, series).getJavascript
  def getActiveSessionsChartComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component = ActiveUsersComponent(runStart, series)
  def getRequestsChartComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component =
    RequestsComponent(runStart, counts, pieSeries)
  def getResponsesChartComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component =
    ResponsesComponent(runStart, counts, pieSeries)
  def getRequestDetailsResponseTimeChartComponent(runStart: Long, responseTimesSuccess: Series[PercentilesVsTimePlot]): Component =
    RequestDetailsResponseTimeComponent(runStart, responseTimesSuccess)
  def getRequestDetailsResponseTimeDistributionChartComponent(
      responseTimesSuccess: Series[PercentVsTimePlot],
      responseTimesFailures: Series[PercentVsTimePlot]
  ): Component = RequestDetailsResponseTimeDistributionComponent(responseTimesSuccess, responseTimesFailures)
  def getRequestDetailsResponseTimeScatterChartComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component =
    RequestDetailsResponseTimeScatterComponent(successes, failures)
  def getRequestDetailsIndicatorChartComponent: Component = RequestDetailsIndicatorComponent()
  def getNumberOfRequestsChartComponent(numberOfRequestNames: Int): HighchartsComponent = NumberOfRequestsComponent(numberOfRequestNames)
  def getGroupDetailsDurationChartComponent(
      containerId: String,
      yAxisName: String,
      runStart: Long,
      durationsSuccess: Series[PercentilesVsTimePlot]
  ): Component = GroupDetailsDurationComponent(containerId, yAxisName, runStart, durationsSuccess)
  def getGroupDetailsDurationDistributionChartComponent(
      title: String,
      containerId: String,
      durationsSuccess: Series[PercentVsTimePlot],
      durationsFailure: Series[PercentVsTimePlot]
  ): Component = GroupDetailsDurationDistributionComponent(title, containerId, durationsSuccess, durationsFailure)
}
