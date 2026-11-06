/*
 * Copyright 2011-2026 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.highcharts.template.Template._
import io.gatling.charts.stats.Ranges
import io.gatling.charts.util.Color

private[highcharts] final class RangesTemplate(containerId: String, chartTitle: String, eventName: String, ranges: Ranges, large: Boolean) extends Template {
  override def js: String = s"""
new Highcharts.Chart({
  chart: {
    renderTo: ${jsString(containerId)},
    marginRight: 100
  },
  legend: { enabled: false },
  title: {
    text: ${htmlTitle(chartTitle)},
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
    title: { text: ${jsString(svgText(s"Number of ${eventName.capitalize}"))} },
    reversedStacks: false
  },
  tooltip: {
    formatter: function() {
      var s;
      if (this.point.name) { // the pie chart
        s = ''+ this.point.name +': '+ this.y + ${jsString(svgText(s"% $eventName"))};
      } else {
        s = ''+ this.y + ${jsString(svgText(s" $eventName"))};
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
            <div id="${escapeHtml(containerId)}" class="schema ${if (large) "ranges-large" else "ranges"}">
            </div>
"""
}
