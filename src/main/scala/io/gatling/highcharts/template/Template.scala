/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.highcharts.series._

object Template {
	def renderNumberPerSecondSeries(serie: NumberPerSecondSeries) = fast"""
color: '${serie.colors(0)}'
, name: '${serie.name}'
, data: [
${serie.elements.mkFastring(",")}
]
, tooltip: { yDecimals: 0, ySuffix: '' }
"""
}

abstract class Template {

	def js: Fastring

	def html: Fastring

	def renderPieSeries(serie: PieSeries) = fast"""
type: 'pie',
name: '${serie.name}',
data: [
${serie.elements.mkFastring(",")}
],
center: [800, -30],
size: 70,
showInLegend: false,
dataLabels: { enabled: false },
dataGrouping: { enabled: false }
"""

	def renderStackedColumnSeries(serie: StackedColumnSeries) = fast"""
type: 'column'
, color: '${serie.colors(0)}'
, name: '${serie.name}'
, data: [
${serie.elements.mkFastring(",")}
]
, tooltip: { yDecimals: 0, ySuffix: 'ms' }
"""

	def renderScatterSeries(serie: ScatterSeries) = fast"""
type: 'scatter'
, color: '${serie.colors(0)}'
, name: '${serie.name}'
, data: [
${serie.elements.mkFastring(",")}
]"""

	def renderResponseTimeSeries(series: ResponseTimeSeries, marker: Option[String]) = fast"""
color: '${series.colors(0)}'
, pointInterval: 1000
, name: '${series.name}'
, data: [
${series.elements.mkFastring(",")}
]
, type: 'arearange'
, tooltip: { yDecimals: 0, ySuffix: 'ms' }
, yAxis: 0
${marker.map(m => s", marker: { $m }").getOrElse("")}
"""

	def renderNumberPerSecondSeries(serie: NumberPerSecondSeries) = Template.renderNumberPerSecondSeries(serie)
}
