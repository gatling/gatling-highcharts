/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.series.{ CountsPerSecSeries, PieSeries }
import io.gatling.charts.util.Color

private[highcharts] final class CountsPerSecTemplate(
    chartTitle: String,
    yAxisTitle: String,
    containerName: String,
    countsSeries: CountsPerSecSeries,
    pieSeries: PieSeries,
    pieX: Int,
    allOnly: Boolean
) extends Template {
  private val UnpackedPlotsVarName = containerName

  override def js: String =
    s"""
var $UnpackedPlotsVarName = unpack(${countsSeries.render});

var requestsChart = new Highcharts.StockChart({
  chart: {
    renderTo: '$containerName',
    zoomType: 'x',
    marginBottom: 60
  },
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
  plotOptions: {
    series: {
      dataGrouping: { enabled: false }
    },
    area: {
      stacking: 'normal'
    }
  },
  xAxis: {
    type: 'datetime',
    ordinal: false,
    maxZoom: 10000 // three days
  },
  yAxis:[
    {
      min: 0,
      title: { text: '$yAxisTitle' },
      opposite: false,
      reversedStacks: false
    }, {
      min: 0,
      title: {
        text: 'Active Users',
        style: { color: '${Color.Users.All}' }
      },
      opposite: true
    }
  ],
  series: [
    ${renderCountsPerSecSeries(countsSeries, UnpackedPlotsVarName, allOnly)}
    allUsersData${if (!allOnly) {
        s""",
{
  ${renderPieSeries(pieSeries, pieX)}
}
"""
      } else {
        ""
      }}
  ]
});

requestsChart.setTitle({
  text: '<span class="chart_title">$chartTitle</span>',
  useHTML: true
});
"""

  override def html: String = s"""
            <div class="schema geant">
                <div id="$containerName" class="geant"></div>
            </div>
"""
}
