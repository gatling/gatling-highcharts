/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.highcharts.series.NumberPerSecondSeries
import io.gatling.charts.util.Colors._

class ActiveSessionsTemplate(runStart: Long, series: Seq[NumberPerSecondSeries]) extends Template {

  def js = fast"""
allSessionsData.yAxis = 0;

var allSessionsChart = new Highcharts.StockChart({
  chart: {
    renderTo: 'container_active_sessions',
    zoomType: 'x'
  },
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    y: -285,
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
    title: { text: 'Number of Active Sessions' },
    opposite: false
  },
  series: [
    ${series.map(serie => List("{", renderNumberPerSecondSeries(serie), "},\n")).flatten.mkFastring}
    allSessionsData
  ]
});


allSessionsChart.setTitle({
  text: '<span class="chart_title">Active Sessions along the Simulation</span>',
  useHTML: true
});

allSessionsData.yAxis = 1;
"""

  val html = fast"""
            <div class="schema geant">
              <a name="active_sessions"></a>
              <div id="container_active_sessions" class="geant"></div>
            </div>
"""
}
