/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.core.stats._

case class StackedColumnSeries(name: String, data: Iterable[PieSlice], color: String) {

  def elements: Iterable[String] = data.map(_.value.toString)
  val getXValues = data.map(_.name)
  val getYValues = data.map(_.value)
}
