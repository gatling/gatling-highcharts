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
color: '${serie.colors(0)}',
name: '${serie.name}',
data: [
  ${serie.elements.mkFastring(",")}
],
tooltip: { yDecimals: 0, ySuffix: '' }
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
center: [775, -40],
size: 70,
showInLegend: false,
dataLabels: { enabled: false },
dataGrouping: { enabled: false }
"""

  def renderStackedColumnSeries(serie: StackedColumnSeries) = fast"""
type: 'column',
color: '${serie.colors(0)}',
name: '${serie.name}',
data: [
  ${serie.elements.mkFastring(",")}
],
tooltip: { yDecimals: 0, ySuffix: 'ms' }
"""

  def renderScatterSeries(serie: ScatterSeries) = fast"""
type: 'scatter',
color: '${serie.colors(0)}',
name: '${serie.name}',
data: [
${serie.elements.mkFastring(",")}
]"""

  private def renderPercentileSeries(name: String, elements: Seq[String], zIndex: Int) =
    fast"""
pointInterval: 1000,
name: '$name',
data: [
  ${elements.mkFastring(",")}
],
tooltip: { yDecimals: 0, ySuffix: 'ms' },
type : 'area',
yAxis: 0,
zIndex: $zIndex
"""

  def renderPercentilesSeries(series: PercentilesSeries) =
    fast"""
    ${
      if (!series.data.isEmpty) {
        fast"""
         {${renderPercentileSeries("min", series.percentiles0, 10)}},
         {${renderPercentileSeries("25%", series.percentiles25, 9)}},
         {${renderPercentileSeries("50%", series.percentiles50, 8)}},
         {${renderPercentileSeries("75%", series.percentiles75, 7)}},
         {${renderPercentileSeries("80%", series.percentiles80, 6)}},
         {${renderPercentileSeries("85%", series.percentiles85, 5)}},
         {${renderPercentileSeries("90%", series.percentiles90, 4)}},
         {${renderPercentileSeries("95%", series.percentiles95, 3)}},
         {${renderPercentileSeries("99%", series.percentiles99, 2)}},
         {${renderPercentileSeries("max", series.percentiles100, 1)}},"""
      } else ""
    }"""

  def renderNumberPerSecondSeries(series: NumberPerSecondSeries) = Template.renderNumberPerSecondSeries(series)
}
