/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import io.gatling.charts.template.PageTemplate
import io.gatling.highcharts.config.HighchartsFiles.{ NUMBER_OF_REQUESTS_HTML_TEMPLATE_URL, NUMBER_OF_REQUESTS_JS_TEMPLATE_URL }

class NumberOfRequestsTemplate extends Template {
	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(NUMBER_OF_REQUESTS_JS_TEMPLATE_URL)

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(NUMBER_OF_REQUESTS_HTML_TEMPLATE_URL)
}
