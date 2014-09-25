/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.PieSlice

class StackedColumnSeries(name: String, data: Iterable[PieSlice], color: String) extends ColumnSeries(name, data, List(color)) {

  def elements: Iterable[String] = data.map(_.value.toString)
}
