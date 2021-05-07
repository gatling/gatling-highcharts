/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.template

import io.gatling.charts.util.Colors._
import io.gatling.highcharts.series.NumberPerSecondSeries

class ActiveUsersTemplate(runStart: Long, series: Seq[NumberPerSecondSeries]) extends Template {

  override def js: String = s"""
allUsersData.yAxis = 0;

var allUsersChart = new Highcharts.StockChart({
  chart: {
    renderTo: 'container_active_users',
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
    itemStyle: { fontWeight: "normal" }
  },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  rangeSelector: {
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
  yAxis: {
    title: { text: 'Number of Active Users' },
    opposite: false,
    min: 0
  },
  series: [
    ${series.flatMap(serie => List("{", Template.renderUsersPerSecondSeries(runStart, serie), "},\n")).mkString}
    allUsersData
  ]
});


allUsersChart.setTitle({
  text: '<span class="chart_title">Active Users along the Simulation</span>',
  useHTML: true
});

allUsersData.yAxis = 1;
"""

  override def html: String = """
            <div class="schema geant">
              <a name="active_users"></a>
              <div id="container_active_users" class="geant"></div>
            </div>
"""
}
