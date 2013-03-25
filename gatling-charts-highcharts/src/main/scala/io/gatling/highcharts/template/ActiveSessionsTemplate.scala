/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import io.gatling.charts.template.PageTemplate
import io.gatling.highcharts.config.HighchartsFiles.{ ACTIVE_SESSIONS_JS_TEMPLATE_URL, ACTIVE_SESSIONS_HTML_TEMPLATE_URL }
import io.gatling.highcharts.series.NumberPerSecondSeries

class ActiveSessionsTemplate(runStart: Long, series: Seq[NumberPerSecondSeries]) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(ACTIVE_SESSIONS_JS_TEMPLATE_URL,
		Map("chartTitle" -> "Active Sessions along the Simulation",
			"runStart" -> runStart,
			"series" -> series))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(ACTIVE_SESSIONS_HTML_TEMPLATE_URL)
}

