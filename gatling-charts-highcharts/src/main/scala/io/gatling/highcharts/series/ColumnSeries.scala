/**
 * Copyright 2011-2015 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.stats._

class ColumnSeries(name: String, data: Iterable[PieSlice], colors: List[String]) extends Series[PieSlice](name, data, colors) {
  val getXValues = data.map(_.name)
  val getYValues = data.map(_.value)
}
