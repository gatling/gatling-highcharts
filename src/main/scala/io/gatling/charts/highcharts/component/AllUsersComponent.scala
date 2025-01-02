/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.highcharts.series.NumberPerSecondSeries
import io.gatling.charts.highcharts.template.Template
import io.gatling.charts.stats._

private[charts] final class AllUsersComponent(runStart: Long, series: Series[IntVsTimePlot]) {
  def getJavascript: String = {
    val numberPerSecondSeries = NumberPerSecondSeries(series.name, series.data, series.colors.head)
    s"""allUsersData = {
    ${Template.renderUsersPerSecondSeries(runStart, numberPerSecondSeries)}
    , zIndex: 20
    , yAxis: 1
};"""
  }
}
