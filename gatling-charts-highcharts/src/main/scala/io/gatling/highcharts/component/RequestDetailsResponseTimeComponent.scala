/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.PercentilesSeries
import io.gatling.highcharts.template.PercentilesOverTimeTemplate

object RequestDetailsResponseTimeComponent {

  def apply(runStart: Long, responseTimesSuccess: Series[PercentilesVsTimePlot]) = {
    val template = new PercentilesOverTimeTemplate(
      "container",
      "Response Time (ms)",
      new PercentilesSeries(responseTimesSuccess.name, runStart, responseTimesSuccess.data, responseTimesSuccess.colors)
    )

    new HighchartsComponent(template)
  }
}
