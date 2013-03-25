/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import io.gatling.charts.template.PageTemplate
import io.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_INDICATORS_JS_TEMPLATE_URL, REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE_URL }

class RequestDetailsIndicatorTemplate extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_INDICATORS_JS_TEMPLATE_URL,
		Map("chartTitle" -> "Indicators"))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE_URL)
}

