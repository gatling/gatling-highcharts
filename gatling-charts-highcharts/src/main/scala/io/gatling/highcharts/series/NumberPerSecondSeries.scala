/*
 * Copyright 2011-2020 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.series

import io.gatling.core.stats._

final case class NumberPerSecondSeries(name: String, data: Iterable[IntVsTimePlot], color: String)
