/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.template.Template._
import io.gatling.charts.stats.{ PercentVsTimePlot, Series }
import io.gatling.charts.util.Color

private[highcharts] final class DistributionTemplate(
    containerId: String,
    title: String,
    yAxisName: String,
    successSeries: Seq[PercentVsTimePlot],
    failureSeries: Seq[PercentVsTimePlot]
) extends Template {
  private val categories = (if (successSeries.nonEmpty) successSeries else failureSeries).map(plot => s"'${plot.time}'").mkString("[", ", ", "]")

  override def js: String = s"""
new Highcharts.Chart({
  chart: {
    renderTo: ${jsString(containerId)},
    type: 'column',
    marginBottom: 60
  },
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    y: 5,
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" },
    symbolRadius: 0
  },
  title: {
    text: ${htmlTitle(s"$title Distribution")},
    useHTML: true
  },
  navigator: {
    maskInside: false
  },
  xAxis: {
    categories: $categories,
    tickInterval: 20
  },
  yAxis: {
    min: 0,
    title: { text: ${jsString(svgText(s"Percentage of $yAxisName"))} },
    reversedStacks: false
  },
  tooltip: {
    formatter: function() {
      return '<b>'+ this.x +' ms</b><br/>'+
      this.series.name +': '+ this.y +' %<br/>'+
      'Total: '+ this.point.stackTotal + ' %';
    }
  },
  plotOptions: {
    series: {
      groupPadding: 0,
      stacking: 'normal',
	    shadow: true
    }
  },
  series: [
  	{${renderStackedColumnSeries(successSeries, Series.OK, Color.Requests.Ok)}},
  	{${renderStackedColumnSeries(failureSeries, Series.KO, Color.Requests.Ko)}}
  ]
});
"""

  private def renderStackedColumnSeries(series: Iterable[PercentVsTimePlot], name: String, color: Color): String = s"""
type: 'column',
color: '$color',
name: ${jsString(name)},
data: [
  ${series.map(plot => math.round(plot.value * 100) / 100.0).mkString(",")}
],
tooltip: { yDecimals: 0, ySuffix: 'ms' }
"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="${escapeHtml(containerId)}" class="geant"></div>
            </div>
"""
}
