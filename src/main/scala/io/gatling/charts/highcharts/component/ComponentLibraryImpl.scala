/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.charts.highcharts.template._
import io.gatling.charts.report.GroupContainer
import io.gatling.charts.stats._

final class ComponentLibraryImpl extends ComponentLibrary {
  override def getUserStartRateComponent(containerId: String, runStart: Long, allUsersSeries: UserSeries, scenarioSeries: Seq[UserSeries]): Component =
    new HighchartsComponent(
      new UsersChartTemplate(
        containerId,
        "Number of users started per second",
        "Number of users started",
        runStart,
        allUsersSeries,
        scenarioSeries
      )
    )
  override def getMaxConcurrentUsersComponent(containerId: String, runStart: Long, allUsersSeries: UserSeries, scenarioSeries: Seq[UserSeries]): Component =
    new HighchartsComponent(
      new UsersChartTemplate(
        containerId,
        "Number of concurrent users",
        "Number of concurrent users",
        runStart,
        allUsersSeries,
        scenarioSeries
      )
    )

  override def getRangesComponent(containerId: String, chartTitle: String, eventName: String, ranges: Ranges, large: Boolean): Component =
    new HighchartsComponent(new RangesTemplate(containerId, chartTitle, eventName, ranges, large))

  override def getRequestCountPolarComponent(rootContainer: GroupContainer): Component =
    new HighchartsComponent(new RequestCountPolarTemplate(rootContainer))

  override def getDistributionComponent(
      containerId: String,
      title: String,
      yAxisName: String,
      responseTimesSuccess: Seq[PercentVsTimePlot],
      responseTimesFailures: Seq[PercentVsTimePlot]
  ): Component =
    new HighchartsComponent(
      new DistributionTemplate(
        containerId,
        title,
        yAxisName,
        responseTimesSuccess,
        responseTimesFailures
      )
    )

  override def getPercentilesOverTimeComponent(
      containerId: String,
      title: String,
      yAxisName: String,
      runStart: Long,
      data: Seq[PercentilesVsTimePlot]
  ): Component =
    new HighchartsComponent(
      new PercentilesOverTimeTemplate(
        containerId,
        title,
        yAxisName,
        runStart,
        data
      )
    )

  override def getRequestsComponent(containerId: String, runStart: Long, counts: Seq[CountsVsTimePlot]): Component =
    new HighchartsComponent(
      new CountsPerSecTemplate(
        containerId,
        chartTitle = "Number of requests per second",
        yAxisTitle = "Number of requests",
        runStart,
        counts,
        hasPie = false
      )
    )

  override def getResponsesComponent(containerId: String, runStart: Long, counts: Seq[CountsVsTimePlot]): Component =
    new HighchartsComponent(
      new CountsPerSecTemplate(
        containerId,
        chartTitle = "Number of responses per second",
        yAxisTitle = "Number of responses",
        runStart,
        counts,
        hasPie = true
      )
    )

  override def getResponseTimeScatterComponent(containerId: String, successes: Seq[IntVsTimePlot], failures: Seq[IntVsTimePlot]): Component =
    new HighchartsComponent(
      new ResponseTimeScatterTemplate(
        containerId,
        successes,
        failures,
        "Response Time against Global Throughput",
        "Response Time (ms)"
      )
    )
}
