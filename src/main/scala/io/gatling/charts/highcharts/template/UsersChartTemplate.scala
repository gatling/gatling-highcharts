/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.stats.UserSeries
import io.gatling.charts.util.Color

private[highcharts] final class UsersChartTemplate(
    title: String,
    yAxisTitle: String,
    containerId: String,
    runStart: Long,
    allUsersSeries: UserSeries,
    scenarioSeries: Seq[UserSeries]
) extends Template {

  private val scenarioSeriesColors = Iterator.continually(Color.Users.Base).flatten.take(scenarioSeries.size).toList

  override def js: String = s"""
new Highcharts.StockChart({
  chart: {
    renderTo: '$containerId',
    zoomType: 'x'
  },
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    align: 'right',
    verticalAlign: 'top',
    layout: 'vertical',
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" },
    symbolRadius: 0
  },
  title: {
    text: '<span class="chart_title">$title</span>',
    useHTML: true
  },
  navigator: {
    maskInside: false
  },
  rangeSelector: {
    buttonSpacing: 0,
    buttonTheme: {
      fill: '${Color.RangeSelector.Fill}',
      padding: 1,
      stroke: '${Color.RangeSelector.Border}',
      'stroke-width': 0.25,
      style: {
        color: '${Color.RangeSelector.Border}',
        fontWeight: 'bold',
      },
      states: {
        stroke: '${Color.RangeSelector.Border}',
        'stroke-width': 0.25,
        hover: {
          fill: '${Color.RangeSelector.Hover}',
          style: { color: 'black' }
        },
        select: {
          fill: '${Color.RangeSelector.Selected}',
          style: { color: 'white' }
        }
      }
    },
    buttons : [
      {
        type : 'minute',
        count : 1,
        text : '1m'
      }, {
        type : 'minute',
        count : 10,
        text : '10m'
      }, {
        type : 'hour',
        count : 1,
        text : '1h'
      }, {
        type : 'all',
        count : 1,
        text : 'All'
      }
    ],
    selected : 3,
    inputEnabled : false
  },
  xAxis: {
    type: 'datetime',
    ordinal: false,
    maxZoom: 10000 // three days
  },
  yAxis: {
    title: { text: '$yAxisTitle' },
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
name: '${series.name.replace("'", "\\'")}',
data: [
  ${series.data.map(plot => s"[${runStart + plot.time}, ${plot.value}]").mkString(",")}
],
tooltip: { yDecimals: 0, ySuffix: '', valueDecimals: 0 }
}"""

  override val html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
