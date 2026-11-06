/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.template.Template._
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
    renderTo: ${jsString(containerId)},
    zoomType: 'x',
    marginBottom: 60
  },
  legend: {
    enabled: true,
    floating: true,
    y: -65
  },
  title: {
    text: ${htmlTitle(chartTitle)},
    useHTML: true
  },
  $StockRangeSelector,
  plotOptions: {
    series: {
      dataGrouping: { enabled: false }
    },
    area: {
      stacking: 'normal'
    }
  },
  $StockXAxis,
  yAxis:[
    {
      min: 0,
      title: { text: ${jsString(svgText(yAxisTitle))} },
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
name: ${jsString(name)},
data: [${joinJs(countsData, ", ")((sb, plot) =>
          sb.append('[').append(truncateTimestampToSecond(runStart + plot.time)).append(", ").append(f(plot)).append(']')
        )}],
tooltip: { valueDecimals: 0 }
${if (area) ",type: 'area'" else ""}
}"""

    def renderPieSeries: String =
      s"""{
type: 'pie',
name: '${Series.Distribution}',
data: [
  {name: '${Series.OK}', y: ${countsData.sumBy(_.oks.toLong)}, color: '${Color.Requests.Ok}'},
  {name: '${Series.KO}', y: ${countsData.sumBy(_.kos.toLong)}, color: '${Color.Requests.Ko}'}
],
center: ['87.37%', -40], // relative to the plot width, so it follows the chart width
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
                <div id="${escapeHtml(containerId)}" class="geant"></div>
            </div>
"""
}
