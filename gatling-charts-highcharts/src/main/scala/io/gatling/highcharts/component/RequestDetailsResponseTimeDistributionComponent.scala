/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.charts.stats._
import io.gatling.highcharts.series.StackedColumnSeries
import io.gatling.highcharts.template.RequestDetailsResponseTimeDistributionTemplate

object RequestDetailsResponseTimeDistributionComponent {

  def apply(successDistribution: Series[PercentVsTimePlot], failuresDistribution: Series[PercentVsTimePlot]): HighchartsComponent = {
    val template = new RequestDetailsResponseTimeDistributionTemplate(
      StackedColumnSeries(
        successDistribution.name,
        successDistribution.data.map(plot => new PieSlice(plot.time.toString, plot.roundedUpValue)),
        successDistribution.colors.head
      ),
      StackedColumnSeries(
        failuresDistribution.name,
        failuresDistribution.data.map(plot => new PieSlice(plot.time.toString, plot.roundedUpValue)),
        failuresDistribution.colors.head
      )
    )

    new HighchartsComponent(template)
  }
}
