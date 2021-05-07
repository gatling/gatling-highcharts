/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.template

import io.gatling.highcharts.series._

object Template {

  def millisToSeconds(millis: Long): Long = millis / 1000

  def renderUsersPerSecondSeries(runStart: Long, serie: NumberPerSecondSeries): String =
    s"""
color: '${serie.color}',
name: '${serie.name.replace("'", "\\'")}',
data: [
  ${serie.data
      .map {
        //case IntVsTimePlot(time, 0)     => fast"[${serie.runStart + time},null]"
        plot =>
          s"[${millisToSeconds(runStart + plot.time) * 1000},${plot.value}]"
      }
      .mkString(",")}
],
tooltip: { yDecimals: 0, ySuffix: '', valueDecimals: 0 }"""
}

abstract class Template {

  def js: String

  def html: String

  def renderPieSeries(serie: PieSeries, pieX: Int): String = s"""
type: 'pie',
name: '${serie.name}',
data: [
  ${serie.elements.mkString(",")}
],
center: [$pieX, -40],
size: 70,
showInLegend: false,
dataLabels: { enabled: false },
dataGrouping: { enabled: false }
"""

  def renderStackedColumnSeries(serie: StackedColumnSeries): String = s"""
type: 'column',
color: '${serie.color}',
name: '${serie.name}',
data: [
  ${serie.elements.mkString(",")}
],
tooltip: { yDecimals: 0, ySuffix: 'ms' }
"""

  def renderScatterSeries(serie: ScatterSeries): String = s"""
type: 'scatter',
color: '${serie.color}',
name: '${serie.name}',
data: [
${serie.elements.mkString(",")}
]"""

  private def renderPercentileSeries(name: String, chartVariableName: String, index: Int, zIndex: Int): String =
    s"""
pointInterval: 1000,
name: '$name',
data: $chartVariableName[$index],
tooltip: { yDecimals: 0, ySuffix: 'ms' },
type : 'area',
yAxis: 0,
zIndex: $zIndex
"""

  def renderPercentilesSeries(series: PercentilesSeries, chartVariableName: String): String =
    s"""
    ${if (series.data.nonEmpty) {
      s"""
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
    } else ""}"""

  private def renderCountsPerSecSeries(name: String, chartVariableName: String, color: String, index: Int, area: Boolean): String = s"""
color: '$color',
name: '$name',
data: $chartVariableName[$index],
tooltip: { yDecimals: 0, ySuffix: '', valueDecimals: 0 }
${if (area) ",type: 'area'" else ""}"""

  def renderCountsPerSecSeries(series: CountsPerSecSeries, chartVariableName: String, allOnly: Boolean): String =
    if (allOnly) {
      s"""{${renderCountsPerSecSeries(series.names.head, chartVariableName, series.colors.head, 0, area = true)}},"""
    } else {
      s"""{${renderCountsPerSecSeries(series.names.head, chartVariableName, series.colors.head, 0, area = false)}},
{${renderCountsPerSecSeries(series.names(1), chartVariableName, series.colors(1), 1, area = true)}},
{${renderCountsPerSecSeries(series.names(2), chartVariableName, series.colors(2), 2, area = true)}},"""
    }
}
