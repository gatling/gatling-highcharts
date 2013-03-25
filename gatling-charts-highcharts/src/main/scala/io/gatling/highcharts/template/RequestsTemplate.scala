/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import io.gatling.charts.template.PageTemplate
import io.gatling.highcharts.config.HighchartsFiles.{ REQUESTS_JS_TEMPLATE_URL, REQUESTS_HTML_TEMPLATE_URL }
import io.gatling.highcharts.series.{ PieSeries, NumberPerSecondSeries }

class RequestsTemplate(series: Seq[NumberPerSecondSeries], pieSeries: PieSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUESTS_JS_TEMPLATE_URL, Map("yAxisTitle" -> "Number of Requests /s",
		"chartTitle" -> "Number of Requests per Second",
		"series" -> series,
		"pieSeries" -> pieSeries))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUESTS_HTML_TEMPLATE_URL)
}