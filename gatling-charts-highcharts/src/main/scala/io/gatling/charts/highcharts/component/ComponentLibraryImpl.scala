/*
 * Copyright 2011-2023 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.charts.highcharts.component._
import io.gatling.charts.stats._

final class ComponentLibraryImpl extends ComponentLibrary {
  override def getAllUsersJs(runStart: Long, series: Series[IntVsTimePlot]): String = new AllUsersComponent(runStart, series).getJavascript
  override def getActiveSessionsComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component = ActiveUsersComponent(runStart, series)
  override def getRangesComponent(chartTitle: String, eventName: String, large: Boolean): Component = RangesComponent(chartTitle, eventName, large)
  override def getRequestCountPolarComponent: Component = RequestCountPolarComponent.Instance
  override def getDistributionComponent(
      title: String,
      yAxisName: String,
      responseTimesSuccess: Series[PercentVsTimePlot],
      responseTimesFailures: Series[PercentVsTimePlot]
  ): Component = DistributionComponent(title, yAxisName, responseTimesSuccess, responseTimesFailures)
  override def getPercentilesOverTimeComponent(
      yAxisName: String,
      runStart: Long,
      durationsSuccess: Series[PercentilesVsTimePlot]
  ): Component = PercentilesOverTimeComponent(yAxisName, runStart, durationsSuccess)
  override def getRequestsComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component =
    RequestsComponent(runStart, counts, pieSeries)
  override def getResponsesComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component =
    ResponsesComponent(runStart, counts, pieSeries)
  override def getResponseTimeScatterComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component =
    ResponseTimeScatterComponent(successes, failures)
}
