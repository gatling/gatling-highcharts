/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template
import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ACTIVE_SESSIONS_JS_TEMPLATE_URL, ACTIVE_SESSIONS_HTML_TEMPLATE_URL}
import com.excilys.ebi.gatling.highcharts.series.NumberPerSecondSeries

class ActiveSessionsTemplate(chartTitle: String, series: Seq[NumberPerSecondSeries]) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(ACTIVE_SESSIONS_JS_TEMPLATE_URL, Map("chartTitle" -> chartTitle,
		"series" -> series))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(ACTIVE_SESSIONS_HTML_TEMPLATE_URL)
}

