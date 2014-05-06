/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.charts.util.Colors._
import io.gatling.highcharts.series.{ PieSeries, NumberPerSecondSeries }

class RequestsTemplate(series: Seq[NumberPerSecondSeries], pieSeries: PieSeries) extends Template {

  def js = fast"""
var requestsChart = new Highcharts.StockChart({
    chart: {
        renderTo: 'container_requests',
        zoomType: 'x'
    },
    credits: {
        enabled: false
    },
    legend: {
        enabled: true,
        floating: true,
        itemDistance: 10,
        y: -285,
        borderWidth: 0,
        itemStyle: {
          fontWeight: "normal"
        }
    },
    title: {
        text: 'A title to let highcharts reserve the place for the title set later'
    },
    rangeSelector: {
        buttonSpacing: 0,
        buttonTheme: {
          fill: '$LIGHT_GREY',
          padding: 1,
          stroke: '$BLACK',
          'stroke-width': 0.25,
          style: {
            color: '$BLACK',
            fontWeight: 'bold',
          },
          states: {
            stroke: '$BLACK',
            'stroke-width': 0.25,
            hover: {
              fill: '$DARK_GREY',
              style: {
                color: 'black'
              }
           },
           select: {
              fill: '$DARK_ORANGE',
              style: {
                color: 'white'
              }
            }
          }
        },
        buttons : [{
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
        }],
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
            text: 'Number of Requests',
            style: {
                color: '$BLUE'
            }
        },
        opposite: false
    }, {
        min: 0,
        title: {
            text: 'Active Sessions',
            style: {
                color: '$ORANGE'
            }
        },
        opposite: true
    }],
    series:
    [
        ${series.map(s => List("{", renderNumberPerSecondSeries(s), "},")).flatten.mkFastring}
        allSessionsData,
        {
            ${renderPieSeries(pieSeries)}
        }
    ]
});

requestsChart.setTitle({
    text: '<span class="chart_title">Number of requests per second</span>',
    useHTML: true
});
"""

  val html = fast"""
                        <div class="schema geant">
                            <a name="requests"></a>
                            <div id="container_requests" class="geant"></div>
                        </div>
"""
}
