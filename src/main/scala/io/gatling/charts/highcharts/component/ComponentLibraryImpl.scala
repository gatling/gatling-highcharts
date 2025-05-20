/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.charts.highcharts.template._
import io.gatling.charts.report.GroupContainer
import io.gatling.charts.stats._

final class ComponentLibraryImpl extends ComponentLibrary {
  override def getUserStartRateComponent(runStart: Long, allUsersSeries: UserSeries, scenarioSeries: Seq[UserSeries]): Component =
    new HighchartsComponent(
      new UsersChartTemplate(
        "Number of users started per second",
        "Number of users started",
        "userStartRate",
        runStart,
        allUsersSeries,
        scenarioSeries
      )
    )
  override def getMaxConcurrentUsersComponent(runStart: Long, allUsersSeries: UserSeries, scenarioSeries: Seq[UserSeries]): Component =
    new HighchartsComponent(
      new UsersChartTemplate(
        "Number of concurrent users",
        "Number of concurrent users",
        "concurrentUsers",
        runStart,
        allUsersSeries,
        scenarioSeries
      )
    )

  override def getRangesComponent(chartTitle: String, eventName: String, ranges: Ranges, large: Boolean): Component =
    new HighchartsComponent(new RangesTemplate(chartTitle, eventName, ranges, large))

  override def getRequestCountPolarComponent(rootContainer: GroupContainer): Component =
    new HighchartsComponent(new RequestCountPolarTemplate(rootContainer))

  override def getDistributionComponent(
      title: String,
      yAxisName: String,
      responseTimesSuccess: Seq[PercentVsTimePlot],
      responseTimesFailures: Seq[PercentVsTimePlot]
  ): Component =
    new HighchartsComponent(
      new DistributionTemplate(
        title,
        yAxisName,
        responseTimesSuccess,
        responseTimesFailures
      )
    )

  override def getPercentilesOverTimeComponent(
      title: String,
      yAxisName: String,
      runStart: Long,
      data: Seq[PercentilesVsTimePlot]
  ): Component =
    new HighchartsComponent(
      new PercentilesOverTimeTemplate(
        title,
        yAxisName,
        runStart,
        data
      )
    )

  override def getRequestsComponent(runStart: Long, counts: Seq[CountsVsTimePlot]): Component =
    new HighchartsComponent(
      new CountsPerSecTemplate(
        chartTitle = "Number of requests per second",
        yAxisTitle = "Number of requests",
        containerName = "requests",
        runStart,
        counts,
        hasPie = false
      )
    )

  override def getResponsesComponent(runStart: Long, counts: Seq[CountsVsTimePlot]): Component =
    new HighchartsComponent(
      new CountsPerSecTemplate(
        chartTitle = "Number of responses per second",
        yAxisTitle = "Number of responses",
        containerName = "responses",
        runStart,
        counts,
        hasPie = true
      )
    )

  override def getResponseTimeScatterComponent(successes: Seq[IntVsTimePlot], failures: Seq[IntVsTimePlot]): Component =
    new HighchartsComponent(
      new ResponseTimeScatterTemplate(
        successes,
        failures,
        "container_response_time_dispersion",
        "Response Time against Global Throughput",
        "Response Time (ms)"
      )
    )
}
