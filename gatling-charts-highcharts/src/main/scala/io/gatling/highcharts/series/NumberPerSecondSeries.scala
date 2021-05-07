/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.charts.stats._

final case class NumberPerSecondSeries(name: String, data: Iterable[IntVsTimePlot], color: String)
