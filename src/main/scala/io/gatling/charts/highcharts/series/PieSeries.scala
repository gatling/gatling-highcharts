/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.series

import io.gatling.charts.stats._
import io.gatling.charts.util.Color

private[highcharts] final case class PieSeries(name: String, data: Iterable[PieSlice], colors: List[Color]) {
  def elements: Iterable[String] =
    data
      .zip(colors)
      .map { case (slice, color) => (slice.name, slice.value, color) }
      .map { case (name, count, color) => s"{name: '$name', y: $count, color: '$color'}" }
}
