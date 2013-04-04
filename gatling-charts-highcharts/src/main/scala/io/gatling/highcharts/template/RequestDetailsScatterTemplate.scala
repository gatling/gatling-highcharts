/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.charts.template.PageTemplate
import io.gatling.highcharts.series.ScatterSeries

class RequestDetailsScatterTemplate(success: ScatterSeries, failures: ScatterSeries) extends Template {

	def js = fast"""
var scatterChart = new Highcharts.Chart({
    chart: {
        renderTo: 'container_dispersion', 
        defaultSeriesType: 'scatter',
        zoomType: 'xy'
    },
    credits: {
        enabled: false
    },
    xAxis: {
        title: {
            enabled: true,
            text: 'Global number of requests per second'
        },
        startOnTick: true,
        endOnTick: true,
        showLastLabel: true
    },
    title: {
        text: 'A title to let highcharts reserve the place for the title set later'
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Response time'
        }
    },
    tooltip: {
        formatter: function() {
                 return ''+
            this.y +' ms at ' + this.x + ' allreq/s';
        }
    },
    legend: {
        enabled: false
    },
    plotOptions: {
        scatter: {
            marker: {
            radius: 3,
            states: {
                hover: {
                    enabled: true,
                    lineColor: 'rgb(100,100,100)'
                }
            }
        },
        states: {
            hover: {
                marker: {
                   enabled: false
                }
            }
            }
        }
    },
    series: [
	    {${renderScatterSeries(success)}},
	    {${renderScatterSeries(failures)}}
	]
});

scatterChart.setTitle({
    text: '<span class="chart_title">Response Time against the Global Number of Requests per Second</span>',
    useHTML: true
});
"""

	val html = fast"""
                        <div class="schema geant">
                            <div id="container_dispersion" class="geant"></div>
                        </div>
"""
}
