/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.charts.stats._

final case class PieSeries(name: String, data: Iterable[PieSlice], seriesColors: List[String]) {

  def elements: Iterable[String] =
    data
      .zip(seriesColors)
      .map { case (slice, color) => (slice.name, slice.value, color) }
      .map { case (name, count, color) => s"{name: '$name', y: $count, color: '$color'}" }
}
