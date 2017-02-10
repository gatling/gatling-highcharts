/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.StackedColumnSeries
import io.gatling.highcharts.template.RequestDetailsResponseTimeDistributionTemplate

object RequestDetailsResponseTimeDistributionComponent {

  def apply(successDistribution: Series[PercentVsTimePlot], failuresDistribution: Series[PercentVsTimePlot]) = {
    val template = new RequestDetailsResponseTimeDistributionTemplate(
      new StackedColumnSeries(successDistribution.name, successDistribution.data.map { plot => PieSlice(plot.time.toString, plot.roundedUpValue) }, successDistribution.colors.head),
      new StackedColumnSeries(failuresDistribution.name, failuresDistribution.data.map { plot => PieSlice(plot.time.toString, plot.roundedUpValue) }, failuresDistribution.colors.head)
    )

    new HighchartsComponent(template)
  }
}
