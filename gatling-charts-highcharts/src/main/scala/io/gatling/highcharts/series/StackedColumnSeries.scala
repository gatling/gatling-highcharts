/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.charts.stats._

final case class StackedColumnSeries(name: String, data: Iterable[PieSlice], color: String) {

  def elements: Iterable[String] = data.map(_.value.toString)
  val getXValues: Iterable[String] = data.map(_.name)
  val getYValues: Iterable[Double] = data.map(_.value)
}
