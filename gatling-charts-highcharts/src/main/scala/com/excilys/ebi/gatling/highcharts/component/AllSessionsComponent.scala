/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.ALL_SESSIONS_JS_TEMPLATE_URL
import com.excilys.ebi.gatling.highcharts.series.NumberPerSecondSeries

class AllSessionsComponent(series: Series[Long, Int]) {

	def getJavascript: String = {
		val numberPerSecondSeries = new NumberPerSecondSeries(series.name, series.data, series.colors.head)
		PageTemplate.TEMPLATE_ENGINE.layout(ALL_SESSIONS_JS_TEMPLATE_URL, Map("activeSessions" -> numberPerSecondSeries))
	}
}