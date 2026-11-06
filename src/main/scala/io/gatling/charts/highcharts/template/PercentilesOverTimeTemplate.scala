/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.template.Template._
import io.gatling.charts.stats.{ Percentiles, PercentilesVsTimePlot }
import io.gatling.charts.util.Color

private[highcharts] final class PercentilesOverTimeTemplate(
    containerId: String,
    title: String,
    yAxisName: String,
    runStart: Long,
    data: Seq[PercentilesVsTimePlot]
) extends Template {
  override def js: String = s"""
new Highcharts.StockChart({
  chart: {
    renderTo: ${jsString(containerId)},
    zoomType: 'x',
    marginBottom: 60
  },
  colors: [${Color.Requests.Percentiles.map(color => s"'$color'").mkString(", ")}],
  legend: {
    enabled: true,
    floating: true,
    y: -65
  },
  title: {
    text: ${htmlTitle(title)},
    useHTML: true
  },
  navigator: {
    baseSeries: 9
  },
  $StockRangeSelector,
  $StockXAxis,
  yAxis:[
    {
      min: 0,
      title: { text: ${jsString(svgText(s"$yAxisName (ms)"))} },
      opposite: false
    }
  ],
  tooltip: { valueSuffix: ' ms' },
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
name: ${jsString(name)},
data: [${joinJs(data, ", ") { (sb, plot) =>
          val withTimestamp = sb.append('[').append(truncateTimestampToSecond(runStart + plot.time)).append(", ")
          (plot.percentiles match {
            case Some(percentiles) => withTimestamp.append(f(percentiles))
            case None              => withTimestamp.append("null")
          }).append(']')
        }}],
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
              <div id="${escapeHtml(containerId)}" class="geant"></div>
            </div>
"""
}
