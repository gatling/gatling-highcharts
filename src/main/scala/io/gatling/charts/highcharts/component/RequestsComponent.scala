/*
 * Copyright 2011-2024 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.series.{ CountsPerSecSeries, PieSeries }
import io.gatling.charts.highcharts.template.CountsPerSecTemplate
import io.gatling.charts.stats._

private[charts] object RequestsComponent {
  def apply(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component = {
    val template = new CountsPerSecTemplate(
      chartTitle = "Number of requests per second",
      yAxisTitle = "Number of requests",
      containerName = "requests",
      countsSeries = CountsPerSecSeries(runStart, counts.data, counts.colors),
      pieSeries = PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
      pieX = 760,
      allOnly = true
    )

    new HighchartsComponent(template)
  }
}
