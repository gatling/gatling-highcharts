/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.stats.{ IntVsTimePlot, Series }
import io.gatling.charts.util.Color

private[highcharts] final class ResponseTimeScatterTemplate(
    containerId: String,
    success: Seq[IntVsTimePlot],
    failures: Seq[IntVsTimePlot],
    chartTitle: String,
    yAxisTitle: String
) extends Template {
  override def js: String = s"""
new Highcharts.Chart({
  chart: {
    renderTo: '$containerId',
    defaultSeriesType: 'scatter',
    zoomType: 'xy',
    marginBottom: 90
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
  title: {
    text: '<span class="chart_title">$chartTitle</span>',
    useHTML: true
  },
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
    enabled: true,
    floating: true,
    y: 0,
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" },
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
	  {${renderScatterSeries(success, Series.OK, Color.Requests.Ok)}},
	  {${renderScatterSeries(failures, Series.KO, Color.Requests.Ko)}}
	]
});
"""

  private def renderScatterSeries(series: Seq[IntVsTimePlot], name: String, color: Color): String = s"""
type: 'scatter',
color: '$color',
name: '$name',
data: [
${series.map(plot => s"[${plot.time},${plot.value}]").mkString(",")}
]"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
