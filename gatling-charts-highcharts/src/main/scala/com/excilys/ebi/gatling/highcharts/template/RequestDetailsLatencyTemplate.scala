/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template

import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_LATENCY_JS_TEMPLATE_URL, REQUEST_DETAILS_LATENCY_HTML_TEMPLATE_URL }
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries

class RequestDetailsLatencyTemplate(successSeries: ResponseTimeSeries, failuresSeries: ResponseTimeSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_LATENCY_JS_TEMPLATE_URL,
		Map("chartTitle" -> "Latency during Simulation",
			"successSeries" -> successSeries,
			"failureSeries" -> failuresSeries))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_LATENCY_HTML_TEMPLATE_URL)
}

