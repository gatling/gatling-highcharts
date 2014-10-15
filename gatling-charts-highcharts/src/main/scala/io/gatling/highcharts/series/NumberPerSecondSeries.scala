/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import io.gatling.core.result.{ IntVsTimePlot, Series }

class NumberPerSecondSeries(name: String, val runStart: Long, data: Iterable[IntVsTimePlot], color: String) extends Series[IntVsTimePlot](name, data, List(color))
