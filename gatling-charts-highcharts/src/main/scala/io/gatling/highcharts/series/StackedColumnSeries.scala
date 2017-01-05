/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.stats._

class StackedColumnSeries(name: String, data: Iterable[PieSlice], color: String) extends ColumnSeries(name, data, List(color)) {

  def elements: Iterable[String] = data.map(_.value.toString)
}
