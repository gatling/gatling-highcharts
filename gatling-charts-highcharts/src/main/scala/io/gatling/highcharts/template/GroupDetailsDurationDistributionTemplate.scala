/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.highcharts.series.StackedColumnSeries

class GroupDetailsDurationDistributionTemplate(title: String, containerId: String, durationSeriesSuccess: StackedColumnSeries, durationSeriesFailure: StackedColumnSeries) extends Template {

  def js = fast"""
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
    categories: ['${durationSeriesSuccess.getXValues.mkFastring("', '")}'],
    tickInterval: 20
  },
  yAxis: {
    min: 0,
    title: { text: 'Percentage of Requests' }
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

  val html = fast"""
            <div class="schema geant">
              <div id="$containerId" class="geant"></div>
            </div>
"""
}
