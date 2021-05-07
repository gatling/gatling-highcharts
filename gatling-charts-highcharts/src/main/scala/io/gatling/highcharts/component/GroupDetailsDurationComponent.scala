/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.charts.stats._
import io.gatling.highcharts.series.PercentilesSeries
import io.gatling.highcharts.template.PercentilesOverTimeTemplate

object GroupDetailsDurationComponent {

  def apply(containerId: String, yAxisName: String, runStart: Long, durationsSuccess: Series[PercentilesVsTimePlot]): HighchartsComponent = {
    val template = new PercentilesOverTimeTemplate(
      containerId,
      yAxisName,
      PercentilesSeries(durationsSuccess.name, runStart, durationsSuccess.data, durationsSuccess.colors)
    )

    new HighchartsComponent(template)
  }
}
