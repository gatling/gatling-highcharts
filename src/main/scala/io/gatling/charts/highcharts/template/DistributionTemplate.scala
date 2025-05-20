/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import java.util.Locale

import io.gatling.charts.stats.{ PercentVsTimePlot, Series }
import io.gatling.charts.util.Color

private[highcharts] final class DistributionTemplate(
    title: String,
    yAxisName: String,
    successSeries: Seq[PercentVsTimePlot],
    failureSeries: Seq[PercentVsTimePlot]
) extends Template {
  private val containerId = s"${title.replace(" ", "").toLowerCase(Locale.ROOT)}DistributionContainer"
  private val categories = (if (successSeries.nonEmpty) successSeries else failureSeries).map(_.time)

  override def js: String = s"""
new Highcharts.Chart({
  chart: {
    renderTo: '$containerId',
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
    text: '<span class="chart_title">$title Distribution</span>',
    useHTML: true
  },
  navigator: {
    maskInside: false
  },
  xAxis: {
    categories: ['${categories.mkString("', '")}'],
    tickInterval: 20
  },
  yAxis: {
    min: 0,
    title: { text: 'Percentage of $yAxisName' },
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
name: '$name',
data: [
  ${series.map(plot => (plot.value * 100).toInt / 100.0).mkString(",")}
],
tooltip: { yDecimals: 0, ySuffix: 'ms' }
"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
