/*
 * Copyright 2011-2023 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.util.Color

private[highcharts] class RangesTemplate(chartTitle: String, eventName: String, large: Boolean) extends Template {
  override def js: String = s"""
Highcharts.setOptions({
  global: { useUTC: false }
});

var rangesChart = new Highcharts.Chart({
  chart: {
    renderTo: 'ranges',
    marginRight: 100
  },
  credits: { enabled: false },
  legend: { enabled: false },
  title: { text: 'A title to let highcharts reserve the place for the title set later' },
  xAxis: {
    categories: [
      pageStats.group1.htmlName,
      pageStats.group2.htmlName,
      pageStats.group3.htmlName,
      pageStats.group4.htmlName
    ]
  },
  yAxis: {
    title: { text: 'Number of ${eventName.capitalize}' },
    reversedStacks: false
  },
  tooltip: {
    formatter: function() {
      var s;
      if (this.point.name) { // the pie chart
        s = ''+ this.point.name +': '+ this.y +'% $eventName';
      } else {
        s = ''+ this.y + ' $eventName';
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
      	color: '${Color.Requests.Ok}',
      	y: pageStats.group1.count
      },
      {
      	color: '${Color.Requests.Fine}',
      	y: pageStats.group2.count
      },
      {
      	color: '${Color.Requests.Poor}',
      	y: pageStats.group3.count
      },
      {
      	color: '${Color.Requests.Ko}',
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
          color: '${Color.Requests.Ok}'
        },
        {
          name: pageStats.group2.name,
          y: pageStats.group2.percentage,
          color: '${Color.Requests.Fine}'
        },
        {
          name: pageStats.group3.name,
          y: pageStats.group3.percentage,
          color: '${Color.Requests.Poor}'
        },
        {
          name: pageStats.group4.name,
          y: pageStats.group4.percentage,
          color: '${Color.Requests.Ko}'
        }
      ],
      center: [${if (large) "368" else "345"}, 0],
      size: 90,
      showInLegend: false,
      dataLabels: { enabled: false }
    }
  ]
});

rangesChart.setTitle({
  text: '<span class="chart_title">$chartTitle</span>',
  useHTML: true
});
"""

  override def html: String = s"""
            <div id="ranges" class="schema ${if (large) "ranges-large" else "ranges"}">
            </div>
"""
}
