/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.highcharts.series.StackedColumnSeries

object RequestDetailsResponseTimeDistributionTemplate {
  val htmlContent = fast"""
            <div class="schema geant">
              <div id="container_distrib" class="geant"></div>
            </div>
"""
}

class RequestDetailsResponseTimeDistributionTemplate(successSeries: StackedColumnSeries, failuresSeries: StackedColumnSeries) extends Template {

  val categories = if (!successSeries.getXValues.isEmpty) successSeries.getXValues else failuresSeries.getXValues

  def js = fast"""
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
    categories: ['${categories.mkFastring("', '")}'],
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

  val html = RequestDetailsResponseTimeDistributionTemplate.htmlContent
}
