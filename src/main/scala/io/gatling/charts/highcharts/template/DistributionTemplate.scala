/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import java.util.Locale

import io.gatling.charts.highcharts.series.StackedColumnSeries

private[highcharts] final class DistributionTemplate(
    title: String,
    yAxisName: String,
    successSeries: StackedColumnSeries,
    failureSeries: StackedColumnSeries
) extends Template {
  private val jsName = s"${title.replace(" ", "").toLowerCase(Locale.ROOT)}Distribution"
  private val chartName = s"${jsName}Chart"
  private val containerId = s"${jsName}Container"
  private val categories = if (successSeries.getXValues.nonEmpty) successSeries.getXValues else failureSeries.getXValues

  override def js: String = s"""
var $chartName = new Highcharts.Chart({
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
  	{${renderStackedColumnSeries(successSeries)}},
  	{${renderStackedColumnSeries(failureSeries)}}
  ]
});
"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
