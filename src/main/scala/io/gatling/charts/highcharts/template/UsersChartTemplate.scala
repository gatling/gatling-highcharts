/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.template.Template._
import io.gatling.charts.stats.UserSeries
import io.gatling.charts.util.Color

private[highcharts] final class UsersChartTemplate(
    containerId: String,
    title: String,
    yAxisTitle: String,
    runStart: Long,
    allUsersSeries: UserSeries,
    scenarioSeries: Seq[UserSeries]
) extends Template {

  private val scenarioSeriesColors = Iterator.continually(Color.Users.Base).flatten.take(scenarioSeries.size).toList

  override def js: String = s"""
new Highcharts.StockChart({
  chart: {
    renderTo: ${jsString(containerId)},
    zoomType: 'x'
  },
  legend: {
    enabled: true,
    floating: true,
    align: 'right',
    verticalAlign: 'top',
    layout: 'vertical'
  },
  title: {
    text: ${htmlTitle(title)},
    useHTML: true
  },
  $StockRangeSelector,
  $StockXAxis,
  yAxis: {
    title: { text: ${jsString(svgText(yAxisTitle))} },
    opposite: false,
    min: 0
  },
  series: [
    ${scenarioSeries.zip(scenarioSeriesColors).map { case (series, color) => renderUsersPerSecondSeries(series, color) }.mkString(",\n")},
    ${renderUsersPerSecondSeries(allUsersSeries, Color.Users.All)}
  ]
});
"""

  private def renderUsersPerSecondSeries(series: UserSeries, color: Color): String =
    s"""{
color: '$color',
name: ${jsString(svgText(series.name))},
data: [
  ${joinJs(series.data, ",")((sb, plot) => sb.append('[').append(truncateTimestampToSecond(runStart + plot.time)).append(", ").append(plot.value).append(']'))}
],
tooltip: { valueDecimals: 0 }
}"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="${escapeHtml(containerId)}" class="geant"></div>
            </div>
"""
}
