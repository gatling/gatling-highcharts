/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.stats.{ CountsVsTimePlot, Series }
import io.gatling.charts.util.Color
import io.gatling.commons.util.Collections._

private[highcharts] final class CountsPerSecTemplate(
    containerId: String,
    chartTitle: String,
    yAxisTitle: String,
    runStart: Long,
    countsData: Seq[CountsVsTimePlot],
    hasPie: Boolean
) extends Template {
  override def js: String =
    s"""
new Highcharts.StockChart({
  chart: {
    renderTo: '$containerId',
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
  title: {
    text: '<span class="chart_title">$chartTitle</span>',
    useHTML: true
  },
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
    }
  ],
  series: [
    $renderCountsPerSecSeries
  ]
});
"""

  private def renderCountsPerSecSeries: String = {

    def renderCountsPerSecSeries(f: CountsVsTimePlot => Int, name: String, color: Color, area: Boolean): String =
      s"""{
color: '$color',
name: '$name',
data: ${countsData.map(plot => s"""[${truncateTimestampToSecond(runStart + plot.time)}, ${f(plot)}]""").mkString("[", ", ", "]")},
tooltip: { yDecimals: 0, ySuffix: '', valueDecimals: 0 }
${if (area) ",type: 'area'" else ""}
}"""

    def renderPieSeries: String =
      s"""{
type: 'pie',
name: '${Series.Distribution}',
data: [
  {name: '${Series.OK}', y: ${countsData.sumBy(_.oks)}, color: '${Color.Requests.Ok}'},
  {name: '${Series.KO}', y: ${countsData.sumBy(_.kos)}, color: '${Color.Requests.Ko}'}
],
center: [775, -40],
size: 70,
showInLegend: false,
dataLabels: { enabled: false },
dataGrouping: { enabled: false }
}"""

    if (hasPie) {
      s"""${renderCountsPerSecSeries(plot => plot.oks + plot.kos, Series.All, Color.Requests.All, area = false)},
${renderCountsPerSecSeries(_.oks, Series.OK, Color.Requests.Ok, area = true)},
${renderCountsPerSecSeries(_.kos, Series.KO, Color.Requests.Ko, area = true)},
$renderPieSeries"""
    } else {
      s"""${renderCountsPerSecSeries(plot => plot.oks + plot.kos, Series.All, Color.Requests.All, area = true)}"""
    }
  }

  override def html: String = s"""
            <div class="schema geant">
                <div id="$containerId" class="geant"></div>
            </div>
"""
}
