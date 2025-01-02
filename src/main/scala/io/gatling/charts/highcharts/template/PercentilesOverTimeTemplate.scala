/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import java.util.Locale

import io.gatling.charts.highcharts.series.PercentilesSeries
import io.gatling.charts.util.Color

private[highcharts] final class PercentilesOverTimeTemplate(yAxisName: String, series: PercentilesSeries) extends Template {
  private val title = series.name
  private val jsName = s"${title.replaceAll("[ ()]", "").toLowerCase(Locale.ROOT)}Percentiles"
  private val chartName = s"${jsName}Chart"
  private val containerId = s"${jsName}Container"

  override def js: String = s"""
var $jsName = unpack(${series.render});

var $chartName = new Highcharts.StockChart({
  chart: {
    renderTo: '$containerId',
    zoomType: 'x',
    marginBottom: 60
  },
  colors: [${series.colors.map(color => s"'$color'").mkString(", ")}],
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    y: -65,
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" },
    symbolRadius: 0
  },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  navigator: {
    maskInside: false,
    baseSeries: 9
  },
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
      title: { text: '$yAxisName (ms)' },
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
  ${renderPercentilesSeries(series, jsName)}
  allUsersData
  ]
});

$chartName.setTitle({
  text: '<span class="chart_title chart_title_">$title</span>',
  useHTML: true
});
"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
