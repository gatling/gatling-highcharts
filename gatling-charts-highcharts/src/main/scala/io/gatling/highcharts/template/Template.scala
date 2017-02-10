/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._
import io.gatling.core.stats._
import io.gatling.highcharts.series._

object Template {

  def millisToSeconds(millis: Long): Long = millis / 1000

  def renderUsersPerSecondSeries(runStart: Long, serie: NumberPerSecondSeries) = fast"""
color: '${serie.colors(0)}',
name: '${serie.name.replace("'", "\\'")}',
data: [
  ${
    serie.data.map {
      //case IntVsTimePlot(time, 0)     => fast"[${serie.runStart + time},null]"
      case IntVsTimePlot(time, value) => fast"[${millisToSeconds(runStart + time) * 1000},$value]"
    }.mkFastring(",")
  }
],
tooltip: { yDecimals: 0, ySuffix: '', valueDecimals: 0 }"""
}

abstract class Template {

  def js: Fastring

  def html: Fastring

  def renderPieSeries(serie: PieSeries, pieX: Int) = fast"""
type: 'pie',
name: '${serie.name}',
data: [
  ${serie.elements.mkFastring(",")}
],
center: [$pieX, -40],
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

  private def renderPercentileSeries(name: String, chartVariableName: String, index: Int, zIndex: Int) =
    fast"""
pointInterval: 1000,
name: '$name',
data: $chartVariableName[$index],
tooltip: { yDecimals: 0, ySuffix: 'ms' },
type : 'area',
yAxis: 0,
zIndex: $zIndex
"""

  def renderPercentilesSeries(series: PercentilesSeries, chartVariableName: String) =
    fast"""
    ${
      if (series.data.nonEmpty) {
        fast"""
         {${renderPercentileSeries("min", chartVariableName, 0, 10)}},
         {${renderPercentileSeries("25%", chartVariableName, 1, 9)}},
         {${renderPercentileSeries("50%", chartVariableName, 2, 8)}},
         {${renderPercentileSeries("75%", chartVariableName, 3, 7)}},
         {${renderPercentileSeries("80%", chartVariableName, 4, 6)}},
         {${renderPercentileSeries("85%", chartVariableName, 5, 5)}},
         {${renderPercentileSeries("90%", chartVariableName, 6, 4)}},
         {${renderPercentileSeries("95%", chartVariableName, 7, 3)}},
         {${renderPercentileSeries("99%", chartVariableName, 8, 2)}},
         {${renderPercentileSeries("max", chartVariableName, 9, 1)}},"""
      } else ""
    }"""

  private def renderCountsPerSecSeries(name: String, chartVariableName: String, color: String, index: Int, area: Boolean): Fastring = fast"""
color: '$color',
name: '$name',
data: $chartVariableName[$index],
tooltip: { yDecimals: 0, ySuffix: '', valueDecimals: 0 }
${if (area) ",type: 'area'" else ""}"""

  def renderCountsPerSecSeries(series: CountsPerSecSeries, chartVariableName: String, allOnly: Boolean): Fastring =
    if (allOnly) {
      fast"""{${renderCountsPerSecSeries(series.names(0), chartVariableName, series.colors(0), 0, true)}},"""
    } else {
      fast"""{${renderCountsPerSecSeries(series.names(0), chartVariableName, series.colors(0), 0, false)}},
{${renderCountsPerSecSeries(series.names(1), chartVariableName, series.colors(1), 1, true)}},
{${renderCountsPerSecSeries(series.names(2), chartVariableName, series.colors(2), 2, true)}},"""
    }
}
