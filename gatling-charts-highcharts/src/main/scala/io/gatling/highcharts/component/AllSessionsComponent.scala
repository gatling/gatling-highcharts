/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.charts.template.PageTemplate
import io.gatling.core.result.{ IntVsTimePlot, Series }
import io.gatling.highcharts.config.HighchartsFiles.ALL_SESSIONS_JS_TEMPLATE_URL
import io.gatling.highcharts.series.NumberPerSecondSeries

class AllSessionsComponent(runStart: Long, series: Series[IntVsTimePlot]) {

	def getJavascript: String = {
		val numberPerSecondSeries = new NumberPerSecondSeries(series.name, runStart, series.data, series.colors.head)
		PageTemplate.TEMPLATE_ENGINE.layout(ALL_SESSIONS_JS_TEMPLATE_URL, Map("activeSessions" -> numberPerSecondSeries))
	}
}