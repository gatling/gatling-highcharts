/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.{ CountsPerSecSeries, PieSeries }
import io.gatling.highcharts.template.CountsPerSecTemplate

object ResponsesComponent {

  def apply(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]) = {
    val template = new CountsPerSecTemplate(
      chartTitle = "Number of responses per second",
      yAxisTitle = "Number of responses",
      containerName = "container_responses",
      anchorName = "responses",
      countsSeries = new CountsPerSecSeries(runStart, counts.data, counts.colors),
      pieSeries = new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
      pieX = 775,
      allOnly = false
    )

    new HighchartsComponent(template)
  }
}
