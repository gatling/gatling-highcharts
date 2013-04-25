/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.charts.template.PageTemplate
import io.gatling.highcharts.series.StackedColumnSeries

class GroupDetailsDurationDistributionTemplate(durationSeriesSuccess: StackedColumnSeries, durationSeriesFailure: StackedColumnSeries) extends Template {

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
        borderWidth: 0
    },
    title: {
        text: 'A title to let highcharts reserve the place for the title set later'
    },
    xAxis: {
        categories: ['${durationSeriesSuccess.getXValues.mkFastring("', '")}'],
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
            stacking: 'normal'
        }
    },
    series: [
    	{${renderStackedColumnSeries(durationSeriesSuccess)}},
    	{${renderStackedColumnSeries(durationSeriesFailure)}}
    ]
});

responseTimeDistributionChart.setTitle({
    text: '<span class="chart_title">Group Duration Distribution</span>',
    useHTML: true
});
"""

	val html = RequestDetailsResponseTimeDistributionTemplate.htmlContent
}

