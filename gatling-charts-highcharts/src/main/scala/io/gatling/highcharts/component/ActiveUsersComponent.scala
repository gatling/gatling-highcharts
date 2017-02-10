/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.NumberPerSecondSeries
import io.gatling.highcharts.template.ActiveUsersTemplate

object ActiveUsersComponent {

  def apply(runStart: Long, series: Seq[Series[IntVsTimePlot]]) = {
    val template = new ActiveUsersTemplate(runStart, series.map { s => new NumberPerSecondSeries(s.name, s.data, s.colors.head) })
    new HighchartsComponent(template)
  }
}
