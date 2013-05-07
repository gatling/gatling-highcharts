/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.charts.template.PageTemplate
import io.gatling.charts.util.Colors._
import io.gatling.highcharts.series.ResponseTimeSeries

class RequestDetailsResponseTimeTemplate(successSeries: ResponseTimeSeries, failuresSeries: ResponseTimeSeries) extends Template {

	def js = fast"""
var responseTimeChart = new Highcharts.StockChart({
    chart: {
        renderTo: 'container',
        zoomType: 'x'
    },
    credits: {
        enabled: false
    },
    legend: {
        enabled: true,
        floating: true,
        y: -285,
        borderWidth: 0
    },
    title: {
        text: 'A title to let highcharts reserve the place for the title set later'
    },
    rangeSelector: {
        buttons : [{
        type : 'minute',
        count : 1,
        text : '1m'
    }, {
        type : 'minute',
        count : 10,
        text : '10m'
    }, {
        type : 'hour',
        count : 1,
        text : '1h'
    }, {
        type : 'all',
        count : 1,
        text : 'All'
    }],
    selected : 3,
    inputEnabled : false
    },
    xAxis: {
        type: 'datetime',
        ordinal: false,
        maxZoom: 10000 // three days
    },
    yAxis:[
    {
        min: 0,
        title: {
            text: 'Response Time (ms)',
            style: {
                color: '$BLUE'
            }
        }
    }, {
        min: 0,
        title: {
            text: 'Active Sessions',
            style: {
                color: '$ORANGE'
            }
        },
        opposite: true
    }],
    plotOptions: {
        arearange: {
            lineWidth: 1
        },
        series: {
            shadow: true
        }
    },
    series: [
    ${
		if (!successSeries.data.isEmpty)
			fast"{${renderResponseTimeSeries(successSeries, None)}},"
		else ""
	}
    ${
		if (!failuresSeries.data.isEmpty)
			fast"{${renderResponseTimeSeries(failuresSeries, Some("radius: 3, enabled: true"))}},"
		else ""
	}
    allSessionsData
    ]
});

responseTimeChart.setTitle({
    text: '<span class="chart_title chart_title_">Response Time during Simulation</span>',
    useHTML: true
});
"""

	val html = fast"""
                        <div class="schema geant">
                            <div id="container" class="geant"></div>
                        </div>
"""
}

