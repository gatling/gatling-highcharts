/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.highcharts.series.PercentilesSeries
import io.gatling.charts.util.Colors._

class PercentilesOverTimeTemplate(containerId: String, yAxisName: String, series: PercentilesSeries) extends Template {

  private val UnpackedPlotsVarName = "responseTimePercentiles"

  def js = fast"""
var $UnpackedPlotsVarName = unpack(${series.render});

var responseTimeChart = new Highcharts.StockChart({
  chart: {
    renderTo: '$containerId',
    zoomType: 'x'
  },
  colors: ['#C4FD90', '#7FF77F', '#6FF2AD', '#60ECE5', '#51A8E7', '#4353E2', '#7335DC', '#BC28D7', '#D11C97', '#C73905', '$Orange'],
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    y: -65,
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" }
  },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  navigator: { baseSeries: 9 },
  rangeSelector: {
    rangeSelector: { align: "left" },
    buttonSpacing: 0,
    buttonTheme: {
      fill: '$LightGrey',
      padding: 1,
      stroke: '$Black',
      'stroke-width': 0.25,
      style: {
        color: '$Black',
        fontWeight: 'bold',
      },
      states: {
        stroke: '$Black',
        'stroke-width': 0.25,
        hover: {
          fill: '$DarkGrey',
          style: { color: 'black' }
        },
        select: {
          fill: '$DarkOrange',
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
      title: {
        text: '$yAxisName',
        style: { color: '${Blue.code}' }
      },
      opposite: false
    }, {
      min: 0,
      title: {
        text: 'Active Users',
        style: { color: '${Orange.code}' }
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

  val html = fast"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
