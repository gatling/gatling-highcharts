/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.{ Component, ComponentLibrary }
import io.gatling.charts.highcharts.series._
import io.gatling.charts.highcharts.template._
import io.gatling.charts.report.GroupContainer
import io.gatling.charts.stats._

final class ComponentLibraryImpl extends ComponentLibrary {
  override def getUserStartRateComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component =
    new HighchartsComponent(
      new UsersChartTemplate(
        "Number of users started per second",
        "Number of users started",
        "userStartRate",
        runStart,
        series.map(s => NumberPerSecondSeries(s.name, s.data, s.colors.head))
      )
    )
  override def getMaxConcurrentUsersComponent(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component =
    new HighchartsComponent(
      new UsersChartTemplate(
        "Number of concurrent users",
        "Number of concurrent users",
        "concurrentUsers",
        runStart,
        series.map(s => NumberPerSecondSeries(s.name, s.data, s.colors.head))
      )
    )

  override def getRangesComponent(chartTitle: String, eventName: String, ranges: Ranges, large: Boolean): Component =
    new HighchartsComponent(new RangesTemplate(chartTitle, eventName, ranges, large))

  override def getRequestCountPolarComponent(rootContainer: GroupContainer): Component =
    new HighchartsComponent(new RequestCountPolarTemplate(rootContainer))

  override def getDistributionComponent(
      title: String,
      yAxisName: String,
      responseTimesSuccess: Series[PercentVsTimePlot],
      responseTimesFailures: Series[PercentVsTimePlot]
  ): Component =
    new HighchartsComponent(
      new DistributionTemplate(
        title,
        yAxisName,
        StackedColumnSeries(
          responseTimesSuccess.name,
          responseTimesSuccess.data.map(plot => new PieSlice(plot.time.toString, plot.roundedUpValue)),
          responseTimesSuccess.colors.head
        ),
        StackedColumnSeries(
          responseTimesFailures.name,
          responseTimesFailures.data.map(plot => new PieSlice(plot.time.toString, plot.roundedUpValue)),
          responseTimesFailures.colors.head
        )
      )
    )

  override def getPercentilesOverTimeComponent(
      yAxisName: String,
      runStart: Long,
      durationsSuccess: Series[PercentilesVsTimePlot]
  ): Component =
    new HighchartsComponent(
      new PercentilesOverTimeTemplate(
        yAxisName,
        PercentilesSeries(durationsSuccess.name, runStart, durationsSuccess.data, durationsSuccess.colors)
      )
    )

  override def getRequestsComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component =
    new HighchartsComponent(
      new CountsPerSecTemplate(
        chartTitle = "Number of requests per second",
        yAxisTitle = "Number of requests",
        containerName = "requests",
        countsSeries = CountsPerSecSeries(runStart, counts.data, counts.colors),
        pieSeries = PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
        pieX = 760,
        hasPieCharts = false
      )
    )

  override def getResponsesComponent(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component =
    new HighchartsComponent(
      new CountsPerSecTemplate(
        chartTitle = "Number of responses per second",
        yAxisTitle = "Number of responses",
        containerName = "responses",
        countsSeries = CountsPerSecSeries(runStart, counts.data, counts.colors),
        pieSeries = PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
        pieX = 775,
        hasPieCharts = true
      )
    )

  override def getResponseTimeScatterComponent(successes: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component =
    new HighchartsComponent(
      new ResponseTimeScatterTemplate(
        ScatterSeries(successes.name, successes.data, successes.colors.head),
        ScatterSeries(failures.name, failures.data, failures.colors.head),
        "container_response_time_dispersion",
        "Response Time against Global Throughput",
        "Response Time (ms)"
      )
    )
}
