/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.PercentilesSeries
import io.gatling.highcharts.template.PercentilesOverTimeTemplate

object GroupDetailsDurationComponent {

  def apply(containerId: String, yAxisName: String, runStart: Long, durationsSuccess: Series[PercentilesVsTimePlot]) = {
    val template = new PercentilesOverTimeTemplate(
      containerId,
      yAxisName,
      new PercentilesSeries(durationsSuccess.name, runStart, durationsSuccess.data, durationsSuccess.colors)
    )

    new HighchartsComponent(template)
  }
}
