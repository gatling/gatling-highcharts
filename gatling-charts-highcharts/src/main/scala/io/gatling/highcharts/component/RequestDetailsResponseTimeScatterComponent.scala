/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.charts.stats._
import io.gatling.highcharts.series.ScatterSeries
import io.gatling.highcharts.template.RequestDetailsScatterTemplate

object RequestDetailsResponseTimeScatterComponent {

  def apply(success: Series[IntVsTimePlot], failures: Series[IntVsTimePlot]): HighchartsComponent = {
    val template = new RequestDetailsScatterTemplate(
      ScatterSeries(success.name, success.data, success.colors.head),
      ScatterSeries(failures.name, failures.data, failures.colors.head),
      "container_response_time_dispersion",
      "Response Time against Global RPS",
      "Response Time"
    )
    new HighchartsComponent(template)
  }
}
