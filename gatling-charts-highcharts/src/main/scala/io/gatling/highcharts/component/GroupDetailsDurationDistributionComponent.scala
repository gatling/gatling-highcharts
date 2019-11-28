/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.StackedColumnSeries
import io.gatling.highcharts.template.GroupDetailsDurationDistributionTemplate

object GroupDetailsDurationDistributionComponent {

  def apply(
      title: String,
      containerId: String,
      durationDistributionSuccess: Series[PercentVsTimePlot],
      durationDistributionFailure: Series[PercentVsTimePlot]
  ): HighchartsComponent = {
    val template = new GroupDetailsDurationDistributionTemplate(
      title,
      containerId,
      StackedColumnSeries(
        durationDistributionSuccess.name,
        durationDistributionSuccess.data.map { plot =>
          PieSlice(plot.time.toString, plot.roundedUpValue)
        },
        durationDistributionSuccess.colors.head
      ),
      StackedColumnSeries(
        durationDistributionFailure.name,
        durationDistributionFailure.data.map { plot =>
          PieSlice(plot.time.toString, plot.roundedUpValue)
        },
        durationDistributionFailure.colors.head
      )
    )

    new HighchartsComponent(template)
  }
}
