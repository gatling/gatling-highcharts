/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.template

import io.gatling.charts.util.Color
import io.gatling.highcharts.series.PercentilesSeries

class PercentilesOverTimeTemplate(containerId: String, yAxisName: String, series: PercentilesSeries) extends Template {

  private val UnpackedPlotsVarName = "responseTimePercentiles"

  override def js: String = s"""
var $UnpackedPlotsVarName = unpack(${series.render});

var responseTimeChart = new Highcharts.StockChart({
  chart: {
    renderTo: '$containerId',
    zoomType: 'x'
  },
  colors: [${series.colors.map(color => s"'$color'").mkString(", ")}],
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    y: -55,
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" }
  },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  navigator: { baseSeries: 9 },
  rangeSelector: {
    rangeSelector: { align: "left" },
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
        stroke: '${Color.RangeSelector.Hover}',
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
  yAxis:[
    {
      min: 0,
      title: { text: '$yAxisName' },
      opposite: false
    }, {
      min: 0,
      title: {
        text: 'Active Users',
        style: { color: '${Color.Users.All}' }
      },
      opposite: true
    }
  ],
  plotOptions: {
    arearange: { lineWidth: 1 },
    series: {
      dataGrouping: { enabled: false }
    }
  },
  series: [
  ${renderPercentilesSeries(series, UnpackedPlotsVarName)}
  allUsersData
  ]
});

responseTimeChart.setTitle({
  text: '<span class="chart_title chart_title_">${series.name}</span>',
  useHTML: true
});
"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
