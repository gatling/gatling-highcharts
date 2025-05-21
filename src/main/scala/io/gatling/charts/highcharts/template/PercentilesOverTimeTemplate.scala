/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import java.util.Locale

import io.gatling.charts.stats.{ Percentiles, PercentilesVsTimePlot }
import io.gatling.charts.util.Color

private[highcharts] final class PercentilesOverTimeTemplate(title: String, yAxisName: String, runStart: Long, data: Seq[PercentilesVsTimePlot])
    extends Template {
  private val containerId = s"${title.replaceAll("[ ()]", "").toLowerCase(Locale.ROOT)}PercentilesContainer"

  override def js: String = s"""
new Highcharts.StockChart({
  chart: {
    renderTo: '$containerId',
    zoomType: 'x',
    marginBottom: 60
  },
  colors: [${Color.Requests.Percentiles.map(color => s"'$color'").mkString(", ")}],
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    y: -65,
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" },
    symbolRadius: 0
  },
  title: {
    text: '<span class="chart_title chart_title_">$title</span>',
    useHTML: true
  },
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
    }
  ],
  plotOptions: {
    arearange: { lineWidth: 1 },
    series: {
      dataGrouping: { enabled: false }
    }
  },
  series: [
  $renderPercentilesSeries
  ]
});
"""

  private def renderPercentilesSeries: String = {
    def renderPercentileSeries(f: Percentiles => Int, name: String, zIndex: Int): String =
      s"""
pointInterval: 1000,
name: '$name',
data: ${data
          .map(plot =>
            s"""[${truncateTimestampToSecond(runStart + plot.time)}, ${plot.percentiles.map(percentiles => f(percentiles).toString).getOrElse("null")}]"""
          )
          .mkString("[", ", ", "]")},
tooltip: { yDecimals: 0, ySuffix: 'ms' },
type : 'area',
yAxis: 0,
zIndex: $zIndex
"""

    if (data.nonEmpty) {
      s"""
         {${renderPercentileSeries(_.percentile0, "min", 10)}},
         {${renderPercentileSeries(_.percentile25, "25%", 9)}},
         {${renderPercentileSeries(_.percentile50, "50%", 8)}},
         {${renderPercentileSeries(_.percentile75, "75%", 7)}},
         {${renderPercentileSeries(_.percentile80, "80%", 6)}},
         {${renderPercentileSeries(_.percentile85, "85%", 5)}},
         {${renderPercentileSeries(_.percentile90, "90%", 4)}},
         {${renderPercentileSeries(_.percentile95, "95%", 3)}},
         {${renderPercentileSeries(_.percentile99, "99%", 2)}},
         {${renderPercentileSeries(_.percentile100, "max", 1)}}"""
    } else {
      ""
    }
  }

  override def html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
