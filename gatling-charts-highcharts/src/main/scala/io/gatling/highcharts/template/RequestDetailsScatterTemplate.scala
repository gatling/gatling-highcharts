/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.highcharts.series.ScatterSeries

class RequestDetailsScatterTemplate(success: ScatterSeries, failures: ScatterSeries, containerName: String, chartTitle: String, yAxisTitle: String) extends Template {

  def js = fast"""
var scatterChart = new Highcharts.Chart({
  chart: {
    renderTo: '$containerName', 
    defaultSeriesType: 'scatter',
    zoomType: 'xy'
  },
  credits: { enabled: false },
  xAxis: {
    title: {
      enabled: true,
      text: 'Global number of requests per second',
      style: { fontWeight: 'bold' }
    },
    startOnTick: true,
    endOnTick: true,
    showLastLabel: true,
    min: 0
  },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  yAxis: {
    min: 0,
    title: { text: '$yAxisTitle' }
  },
  tooltip: {
    formatter: function() {
         return ''+ this.y +' ms at ' + this.x + ' allreq/s';
    }
  },
  legend: {
    layout: 'vertical',
    align: 'left',
    verticalAlign: 'top',
    x: 80,
    y: 10,
    floating: true,
    backgroundColor: '#FFFFFF',
    borderWidth: 1,
    borderRadius: 3,
    itemStyle: {
      fontWeight: "normal",
      color: "#274B6D"
    }
  },
  plotOptions: {
    scatter: {
      marker: {
        radius: 3,
        states: {
          hover: {
            enabled: true,
            lineColor: 'rgb(100,100,100)'
          }
        }
      },
      states: {
        hover: {
          marker: { enabled: false }
        }
      }
    }
  },
  series: [
	  {${renderScatterSeries(success)}},
	  {${renderScatterSeries(failures)}}
	]
});

scatterChart.setTitle({
  text: '<span class="chart_title">$chartTitle</span>',
  useHTML: true
});
"""

  val html = fast"""
            <div class="schema geant">
              <div id="$containerName" class="geant"></div>
            </div>
"""
}
