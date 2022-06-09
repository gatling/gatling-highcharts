/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.series

import io.gatling.charts.stats._
import io.gatling.charts.util.Color

private[highcharts] final case class StackedColumnSeries(name: String, data: Iterable[PieSlice], color: Color) {

  def elements: Iterable[String] = data.map(_.value.toString)
  val getXValues: Iterable[String] = data.map(_.name)
  val getYValues: Iterable[Double] = data.map(_.value)
}
