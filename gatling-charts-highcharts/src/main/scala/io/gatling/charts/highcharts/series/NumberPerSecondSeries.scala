/*
 * Copyright 2011-2024 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.series

import io.gatling.charts.stats._
import io.gatling.charts.util.Color

private[highcharts] final case class NumberPerSecondSeries(name: String, data: Iterable[IntVsTimePlot], color: Color)
