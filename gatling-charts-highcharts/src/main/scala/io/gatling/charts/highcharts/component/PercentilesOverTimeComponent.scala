/*
 * Copyright 2011-2024 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.series.PercentilesSeries
import io.gatling.charts.highcharts.template.PercentilesOverTimeTemplate
import io.gatling.charts.stats._

private[charts] object PercentilesOverTimeComponent {
  def apply(yAxisName: String, runStart: Long, durationsSuccess: Series[PercentilesVsTimePlot]): Component = {
    val template = new PercentilesOverTimeTemplate(
      yAxisName,
      PercentilesSeries(durationsSuccess.name, runStart, durationsSuccess.data, durationsSuccess.colors)
    )

    new HighchartsComponent(template)
  }
}
