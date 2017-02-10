/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.charts.util.Colors._

class RequestDetailsIndicatorTemplate extends Template {

  val js = fast"""
Highcharts.setOptions({
  global: { useUTC: false }
});

var indicatorsChart = new Highcharts.Chart({
  chart: {
    renderTo: 'container_indicators',
    marginRight: 150
  },
  credits: { enabled: false },
  legend: { enabled: false },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  xAxis: {
    categories: [
      pageStats.group1.name,
      pageStats.group2.name,
      pageStats.group3.name,
      pageStats.group4.name
    ]
  },
  yAxis: {
    title: { text: 'Number of Requests' }
  },
  tooltip: {
    formatter: function() {
      var s;
      if (this.point.name) { // the pie chart
        s = ''+ this.point.name +': '+ this.y +'% requests';
      } else {
        s = ''+ this.y + ' requests';
      }
      return s;
    }
  },
  plotOptions: {
    series: {
      stacking: 'normal',
      shadow: true
    }
  },
  series: [
    {
      type: 'column',
      color: '${Green.code}',
      data: [pageStats.group1.count,0,0,0],
      tooltip: { yDecimals: 0, ySuffix: 'ms' }
    },
    {
      type: 'column',
      color: '${Yellow.code}',
      data: [0,pageStats.group2.count,0,0],
      tooltip: { yDecimals: 0, ySuffix: 'ms' }
    },
    {
      type: 'column',
      color: '${Orange.code}',
      data: [0,0,pageStats.group3.count,0],
      tooltip: { yDecimals: 0, ySuffix: 'ms' }
    },
    {
      type: 'column',
      color: '${Red.code}',
      data: [0,0,0,pageStats.group4.count],
      tooltip: { yDecimals: 0, ySuffix: 'ms' }
    },
    {
      type: 'pie',
      name: 'Percentages',
      data: [
        {
          name: pageStats.group1.name,
          y: pageStats.group1.percentage,
          color: '${Green.code}'
        },
        {
          name: pageStats.group2.name,
          y: pageStats.group2.percentage,
          color: '${Yellow.code}'
        },
        {
          name: pageStats.group3.name,
          y: pageStats.group3.percentage,
          color: '${Orange.code}'
        },
        {
          name: pageStats.group4.name,
          y: pageStats.group4.percentage,
          color: '${Red.code}'
        }
      ],
      center: [470, 85],
      size: 100,
      showInLegend: false,
      dataLabels: { enabled: false }
    }
  ]
});

indicatorsChart.setTitle({
  text: '<span class="chart_title">Indicators</span>',
  useHTML: true
});
"""

  val html = fast"""
            <div class="schema demi">
              <div id="container_indicators" class="demi"></div>
            </div>
"""
}
