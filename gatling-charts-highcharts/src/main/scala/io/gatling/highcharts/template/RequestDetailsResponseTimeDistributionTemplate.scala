/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.template

import io.gatling.highcharts.series.StackedColumnSeries

class RequestDetailsResponseTimeDistributionTemplate(successSeries: StackedColumnSeries, failuresSeries: StackedColumnSeries) extends Template {

  val categories: Iterable[String] = if (successSeries.getXValues.nonEmpty) successSeries.getXValues else failuresSeries.getXValues

  override def js: String = s"""
var responseTimeDistributionChart = new Highcharts.Chart({
  chart: {
    renderTo: 'container_distrib',
    type: 'column'
  },
  credits: {
    enabled: false
  },
  legend: {
    enabled: true,
    floating: true,
    y: -285,
    borderWidth: 0,
    itemStyle: {
      fontWeight: "normal"
    }
  },
  title: {
    text: 'A title to let highcharts reserve the place for the title set later'
  },
  xAxis: {
    categories: ['${categories.mkString("', '")}'],
    tickInterval: 20
  },
  yAxis: {
    min: 0,
    title: {
      text: 'Percentage of Requests'
    }
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
  	{${renderStackedColumnSeries(failuresSeries)}}
  ]
});

responseTimeDistributionChart.setTitle({
  text: '<span class="chart_title">Response Time Distribution</span>',
  useHTML: true
});
"""

  override def html: String =
    s"""
            <div class="schema geant">
              <div id="container_distrib" class="geant"></div>
            </div>
"""
}
