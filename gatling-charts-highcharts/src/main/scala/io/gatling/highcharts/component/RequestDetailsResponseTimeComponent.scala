/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.charts.stats._
import io.gatling.highcharts.series.PercentilesSeries
import io.gatling.highcharts.template.PercentilesOverTimeTemplate

object RequestDetailsResponseTimeComponent {

  def apply(runStart: Long, responseTimesSuccess: Series[PercentilesVsTimePlot]): HighchartsComponent = {
    val template = new PercentilesOverTimeTemplate(
      "container",
      "Response Time (ms)",
      PercentilesSeries(responseTimesSuccess.name, runStart, responseTimesSuccess.data, responseTimesSuccess.colors)
    )

    new HighchartsComponent(template)
  }
}
