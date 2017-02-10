/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.stats._

class ColumnSeries(name: String, data: Iterable[PieSlice], colors: List[String]) extends Series[PieSlice](name, data, colors) {
  val getXValues = data.map(_.name)
  val getYValues = data.map(_.value)
}
