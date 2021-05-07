/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.charts.stats._
import io.gatling.highcharts.series.{ CountsPerSecSeries, PieSeries }
import io.gatling.highcharts.template.CountsPerSecTemplate

object RequestsComponent {

  def apply(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): HighchartsComponent = {
    val template = new CountsPerSecTemplate(
      chartTitle = "Number of requests per second",
      yAxisTitle = "Number of requests",
      containerName = "container_requests",
      anchorName = "requests",
      countsSeries = CountsPerSecSeries(runStart, counts.data, counts.colors),
      pieSeries = PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
      pieX = 760,
      allOnly = true
    )

    new HighchartsComponent(template)
  }
}
