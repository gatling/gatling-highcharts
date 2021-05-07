/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.template

import io.gatling.charts.util.Colors._

class RequestDetailsIndicatorTemplate extends Template {

  override def js: String = s"""
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
      data: [{
      	color: '${Green.code}',
      	y: pageStats.group1.count
      },
      {
      	color: '${Yellow.code}',
      	y: pageStats.group2.count
      },
      {
      	color: '${Orange.code}',
      	y: pageStats.group3.count
      },
      {
      	color: '${Red.code}',
      	y: pageStats.group4.count
      }]
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

  override def html: String = s"""
            <div class="schema demi">
              <div id="container_indicators" class="demi"></div>
            </div>
"""
}
