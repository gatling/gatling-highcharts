/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.series

import io.gatling.charts.stats._

private[highcharts] final case class StackedColumnSeries(name: String, data: Iterable[PercentVsTimePlot])
