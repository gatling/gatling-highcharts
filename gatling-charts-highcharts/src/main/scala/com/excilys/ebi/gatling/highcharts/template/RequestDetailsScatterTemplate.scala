/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template

import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_SCATTER_JS_TEMPLATE_URL, REQUEST_DETAILS_SCATTER_HTML_TEMPLATE_URL }
import com.excilys.ebi.gatling.highcharts.series.ScatterSeries

class RequestDetailsScatterTemplate(success: ScatterSeries, failures: ScatterSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_SCATTER_JS_TEMPLATE_URL,
		Map("chartTitle" -> "Response Time against the Global Number of Requests per Second",
			"successes" -> success,
			"failures" -> failures))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_SCATTER_HTML_TEMPLATE_URL)
}
