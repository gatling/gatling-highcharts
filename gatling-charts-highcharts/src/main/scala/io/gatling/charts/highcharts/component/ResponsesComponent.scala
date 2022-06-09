/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.series.{ CountsPerSecSeries, PieSeries }
import io.gatling.charts.highcharts.template.CountsPerSecTemplate
import io.gatling.charts.stats._

private[charts] object ResponsesComponent {

  def apply(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]): Component = {
    val template = new CountsPerSecTemplate(
      chartTitle = "Number of responses per second",
      yAxisTitle = "Number of responses",
      containerName = "container_responses",
      anchorName = "responses",
      countsSeries = CountsPerSecSeries(runStart, counts.data, counts.colors),
      pieSeries = PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
      pieX = 775,
      allOnly = false
    )

    new HighchartsComponent(template)
  }
}
