/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.series.ScatterSeries
import io.gatling.charts.highcharts.template.ResponseTimeScatterTemplate
import io.gatling.charts.stats._

private[charts] object ResponseTimeScatterComponent {
  def apply(success: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): Component = {
    val template = new ResponseTimeScatterTemplate(
      ScatterSeries(success.name, success.data, success.colors.head),
      ScatterSeries(failures.name, failures.data, failures.colors.head),
      "container_response_time_dispersion",
      "Response Time against Global Throughput",
      "Response Time (ms)"
    )
    new HighchartsComponent(template)
  }
}
