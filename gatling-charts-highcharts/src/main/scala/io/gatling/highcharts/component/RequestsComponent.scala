/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.{ CountsPerSecSeries, PieSeries }
import io.gatling.highcharts.template.CountsPerSecTemplate

object RequestsComponent {

  def apply(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]) = {
    val template = new CountsPerSecTemplate(
      chartTitle = "Number of requests per second",
      yAxisTitle = "Number of requests",
      containerName = "container_requests",
      anchorName = "requests",
      countsSeries = new CountsPerSecSeries(runStart, counts.data, counts.colors),
      pieSeries = new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
      pieX = 760,
      allOnly = true
    )

    new HighchartsComponent(template)
  }
}
