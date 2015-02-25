/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.charts.util.Colors._
import io.gatling.highcharts.series.{ EventsPerSecSeries, PieSeries }

class EventsPerSecTemplate(chartTitle: String,
                           yAxisTitle: String,
                           containerName: String,
                           anchorName: String,
                           countsSeries: EventsPerSecSeries,
                           pieSeries: PieSeries,
                           pieX: Int) extends Template {

  private val UnpackedPlotsVarName = containerName

  def js = fast"""
var $UnpackedPlotsVarName = unpack(${countsSeries.render});

var requestsChart = new Highcharts.StockChart({
  chart: {
    renderTo: '$containerName',
    zoomType: 'x'
  },
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    itemDistance: 10,
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
      title: {
        text: '$yAxisTitle',
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
  series: [
    ${renderEventsPerSecSeries(countsSeries, UnpackedPlotsVarName)}
    allUsersData,
    {
      ${renderPieSeries(pieSeries, pieX)}
    }
  ]
});

requestsChart.setTitle({
  text: '<span class="chart_title">$chartTitle</span>',
  useHTML: true
});
"""

  val html = fast"""
            <div class="schema geant">
              <a name="$anchorName"></a>
                <div id="$containerName" class="geant"></div>
            </div>
"""
}
