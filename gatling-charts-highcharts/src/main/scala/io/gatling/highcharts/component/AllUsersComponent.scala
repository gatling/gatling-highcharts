/**
 * Copyright 2011-2015 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.core.result.{ IntVsTimePlot, Series }
import io.gatling.highcharts.series.NumberPerSecondSeries
import io.gatling.highcharts.template.Template

class AllUsersComponent(runStart: Long, series: Series[IntVsTimePlot]) {

  def getJavascript: Fastring = {
    val numberPerSecondSeries = new NumberPerSecondSeries(series.name, runStart, series.data, series.colors.head)
    fast"""allUsersData = {
    ${Template.renderUsersPerSecondSeries(numberPerSecondSeries)}
    , zIndex: 20
    , yAxis: 1
};"""
  }
}
