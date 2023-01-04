/*
 * Copyright 2011-2023 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.series.StackedColumnSeries
import io.gatling.charts.highcharts.template.DistributionTemplate
import io.gatling.charts.stats._

private[charts] object DistributionComponent {
  def apply(
      title: String,
      yAxisName: String,
      durationDistributionSuccess: Series[PercentVsTimePlot],
      durationDistributionFailure: Series[PercentVsTimePlot]
  ): Component = {
    val template = new DistributionTemplate(
      title,
      yAxisName,
      StackedColumnSeries(
        durationDistributionSuccess.name,
        durationDistributionSuccess.data.map(plot => new PieSlice(plot.time.toString, plot.roundedUpValue)),
        durationDistributionSuccess.colors.head
      ),
      StackedColumnSeries(
        durationDistributionFailure.name,
        durationDistributionFailure.data.map(plot => new PieSlice(plot.time.toString, plot.roundedUpValue)),
        durationDistributionFailure.colors.head
      )
    )

    new HighchartsComponent(template)
  }
}
