/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template

import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{REQUESTS_JS_TEMPLATE, REQUESTS_HTML_TEMPLATE}
import com.excilys.ebi.gatling.highcharts.series.{PieSeries, NumberPerSecondSeries}

class RequestsTemplate(chartTitle: String, series: Seq[NumberPerSecondSeries], pieSeries: PieSeries, activeSessionsSeries: NumberPerSecondSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUESTS_JS_TEMPLATE, Map("yAxisTitle" -> "Number of Requests /s",
		"chartTitle" -> chartTitle,
		"series" -> series,
		"pieSeries" -> pieSeries,
		"activeSessions" -> activeSessionsSeries))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUESTS_HTML_TEMPLATE)
}