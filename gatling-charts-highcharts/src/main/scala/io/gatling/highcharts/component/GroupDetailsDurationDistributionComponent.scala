/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.StackedColumnSeries
import io.gatling.highcharts.template.GroupDetailsDurationDistributionTemplate

object GroupDetailsDurationDistributionComponent {

  def apply(title: String, containerId: String, durationDistributionSuccess: Series[PercentVsTimePlot], durationDistributionFailure: Series[PercentVsTimePlot]) = {
    val template = new GroupDetailsDurationDistributionTemplate(
      title,
      containerId,
      new StackedColumnSeries(durationDistributionSuccess.name, durationDistributionSuccess.data.map { plot => PieSlice(plot.time.toString, plot.roundedUpValue) }, durationDistributionSuccess.colors.head),
      new StackedColumnSeries(durationDistributionFailure.name, durationDistributionFailure.data.map { plot => PieSlice(plot.time.toString, plot.roundedUpValue) }, durationDistributionFailure.colors.head)
    )

    new HighchartsComponent(template)
  }
}
