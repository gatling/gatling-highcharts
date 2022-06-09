/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.series.NumberPerSecondSeries
import io.gatling.charts.util.Color

private[highcharts] final class ActiveUsersTemplate(runStart: Long, series: Seq[NumberPerSecondSeries]) extends Template {

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

  override val html: String = """
            <div class="schema geant">
              <a name="active_users"></a>
              <div id="container_active_users" class="geant"></div>
            </div>
"""
}
