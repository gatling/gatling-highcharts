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

class GroupDetailsDurationTemplate(title: String, containerId: String, yAxisName: String, durationSeriesSuccess: ResponseTimeSeries, durationSeriesFailure: ResponseTimeSeries) extends Template {

	def js = fast"""
var responseTimeChart = new Highcharts.StockChart({
    chart: {
        renderTo: '$containerId',
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
            text: '$yAxisName',
            style: {
                color: '${BLUE}'
            }
        }
    }, {
        min: 0,
        title: {
            text: 'Active Sessions',
            style: {
                color: '${ORANGE}'
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
		if (!durationSeriesSuccess.data.isEmpty)
			fast"{${renderResponseTimeSeries(durationSeriesSuccess, None)}},"
		else ""
	}	
    ${
		if (!durationSeriesFailure.data.isEmpty)
			fast"{${renderResponseTimeSeries(durationSeriesFailure, None)}},"
		else ""
	}	
    allSessionsData
    ]
});

responseTimeChart.setTitle({
    text: '<span class="chart_title chart_title_">$title</span>',
    useHTML: true
});
"""

	val html = fast"""
                        <div class="schema geant">
                            <div id="$containerId" class="geant"></div>
                        </div>
"""
}
