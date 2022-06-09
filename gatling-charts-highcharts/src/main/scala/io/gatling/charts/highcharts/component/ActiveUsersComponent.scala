/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.series.NumberPerSecondSeries
import io.gatling.charts.highcharts.template.ActiveUsersTemplate
import io.gatling.charts.stats._

private[charts] object ActiveUsersComponent {

  def apply(runStart: Long, series: Seq[Series[IntVsTimePlot]]): Component = {
    val template = new ActiveUsersTemplate(
      runStart,
      series.map(s => NumberPerSecondSeries(s.name, s.data, s.colors.head))
    )
    new HighchartsComponent(template)
  }
}
