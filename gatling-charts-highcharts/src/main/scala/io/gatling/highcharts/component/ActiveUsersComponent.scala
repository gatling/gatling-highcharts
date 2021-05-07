/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.charts.stats._
import io.gatling.highcharts.series.NumberPerSecondSeries
import io.gatling.highcharts.template.ActiveUsersTemplate

object ActiveUsersComponent {

  def apply(runStart: Long, series: Seq[Series[IntVsTimePlot]]): HighchartsComponent = {
    val template = new ActiveUsersTemplate(
      runStart,
      series.map(s => NumberPerSecondSeries(s.name, s.data, s.colors.head))
    )
    new HighchartsComponent(template)
  }
}
