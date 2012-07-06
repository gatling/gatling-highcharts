/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template

import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_INDICATORS_JS_TEMPLATE_URL, REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE_URL }

class RequestDetailsIndicatorTemplate extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_INDICATORS_JS_TEMPLATE_URL,
		Map("chartTitle" -> "Indicators"))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE_URL)
}

