/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.template

import io.gatling.highcharts.series.StackedColumnSeries

class GroupDetailsDurationDistributionTemplate(
    title: String,
    containerId: String,
    durationSeriesSuccess: StackedColumnSeries,
    durationSeriesFailure: StackedColumnSeries
) extends Template {

  override def js: String = s"""
var responseTimeDistributionChart = new Highcharts.Chart({
  chart: {
    renderTo: '$containerId',
    type: 'column'
  },
  credits: { enabled: false },
  legend: {
    enabled: true,
    floating: true,
    y: -285,
    borderWidth: 0,
    itemStyle: { fontWeight: "normal" }
  },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  xAxis: {
    categories: ['${durationSeriesSuccess.getXValues.mkString("', '")}'],
    tickInterval: 20
  },
  yAxis: {
    min: 0,
    title: { text: 'Percentage of Requests' },
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
  	{${renderStackedColumnSeries(durationSeriesSuccess)}},
  	{${renderStackedColumnSeries(durationSeriesFailure)}}
  ]
});

responseTimeDistributionChart.setTitle({
  text: '<span class="chart_title">$title</span>',
  useHTML: true
});
"""

  override def html: String = s"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
