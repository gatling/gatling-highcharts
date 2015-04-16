/**
 * Copyright 2011-2015 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ PieSlice, Series }

class PieSeries(name: String, data: Iterable[PieSlice], seriesColors: List[String]) extends Series[PieSlice](name, data, seriesColors) {

  def elements: Iterable[String] = data
    .zip(seriesColors)
    .map { case (slice, color) => (slice.name, slice.value, color) }
    .map { case (name, count, color) => "{name: '" + name + "', y: " + count + ", color: '" + color + "'}" }
}
