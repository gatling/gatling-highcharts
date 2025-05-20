/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.series.NumberPerSecondSeries
import io.gatling.charts.util.Color

private[highcharts] final class UsersChartTemplate(
    title: String,
    yAxisTitle: String,
    jsVariablePrefix: String,
    runStart: Long,
    series: Seq[NumberPerSecondSeries]
) extends Template {
  override def js: String = s"""
var ${jsVariablePrefix}Chart = new Highcharts.StockChart({
  chart: {
    renderTo: '${jsVariablePrefix}Div',
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
    ${series.map(ser => Template.renderUsersPerSecondSeries(runStart, ser)).mkString(",\n")}
  ]
});
"""

  override val html: String = s"""
            <div class="schema geant">
              <div id="${jsVariablePrefix}Div" class="geant"></div>
            </div>
"""
}
