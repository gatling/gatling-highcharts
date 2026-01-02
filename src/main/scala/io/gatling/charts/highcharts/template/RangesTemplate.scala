/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.stats.Ranges
import io.gatling.charts.util.Color

private[highcharts] class RangesTemplate(containerId: String, chartTitle: String, eventName: String, ranges: Ranges, large: Boolean) extends Template {
  override def js: String = s"""
Highcharts.setOptions({
  global: { useUTC: false }
});

new Highcharts.Chart({
  chart: {
    renderTo: '$containerId',
    marginRight: 100
  },
  credits: { enabled: false },
  legend: { enabled: false },
  title: {
    text: '<span class="chart_title">$chartTitle</span>',
    useHTML: true
  },
  xAxis: {
    categories: [
      "t < ${ranges.lowerBound} ms",
      "t >= ${ranges.lowerBound} ms <br> t < ${ranges.higherBound} ms",
      "t >= ${ranges.higherBound} ms",
      "failed"
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
      	y: ${ranges.lowCount}
      },
      {
      	color: '${Color.Requests.Fine}',
      	y: ${ranges.middleCount}
      },
      {
      	color: '${Color.Requests.Poor}',
      	y: ${ranges.highCount}
      },
      {
      	color: '${Color.Requests.Ko}',
      	y: ${ranges.koCount}
      }]
    },
    {
      type: 'pie',
      name: 'Percentages',
      data: [
        {
          name: "t < ${ranges.lowerBound} ms",
          y: ${ranges.lowPercentage},
          color: '${Color.Requests.Ok}'
        },
        {
          name: "${ranges.lowerBound} ms <= t < ${ranges.higherBound} ms",
          y: ${ranges.middlePercentage},
          color: '${Color.Requests.Fine}'
        },
        {
          name: "t >= ${ranges.higherBound} ms",
          y: ${ranges.highPercentage},
          color: '${Color.Requests.Poor}'
        },
        {
          name: "failed",
          y: ${ranges.koPercentage},
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
"""

  override def html: String = s"""
            <div id="$containerId" class="schema ${if (large) "ranges-large" else "ranges"}">
            </div>
"""
}
