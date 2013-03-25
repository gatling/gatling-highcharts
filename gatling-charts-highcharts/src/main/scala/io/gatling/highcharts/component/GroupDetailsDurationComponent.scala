/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntRangeVsTimePlot, Series }
import io.gatling.highcharts.series.ResponseTimeSeries
import io.gatling.highcharts.template.GroupDetailsDurationTemplate

object GroupDetailsDurationComponent {

	def apply(runStart: Long, durationsSuccess: Series[IntRangeVsTimePlot], durationsFailure: Series[IntRangeVsTimePlot]) = {
		val template = new GroupDetailsDurationTemplate(
			new ResponseTimeSeries(durationsSuccess.name, runStart, durationsSuccess.data, durationsSuccess.colors.head),
			new ResponseTimeSeries(durationsFailure.name, runStart, durationsFailure.data, durationsFailure.colors.head))

		new HighchartsComponent(template)
	}
}