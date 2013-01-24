/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.template.GroupDetailsDurationTemplate

object GroupDetailsDurationComponent {

	def apply(runStart: Long, durationsSuccess: Series[Int, (Int, Int)], durationsFailure: Series[Int, (Int, Int)]) = {
		val template = new GroupDetailsDurationTemplate(
			new ResponseTimeSeries(durationsSuccess.name, runStart, durationsSuccess.data, durationsSuccess.colors.head),
			new ResponseTimeSeries(durationsFailure.name, runStart, durationsFailure.data, durationsFailure.colors.head))

		new HighchartsComponent(template)
	}
}