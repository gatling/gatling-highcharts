/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template

import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ GROUP_DETAILS_DURATION_JS_TEMPLATE_URL, GROUP_DETAILS_DURATION_HTML_TEMPLATE_URL }
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries

class GroupDetailsDurationTemplate(durationSeriesSuccess: ResponseTimeSeries, durationSeriesFailure: ResponseTimeSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(GROUP_DETAILS_DURATION_JS_TEMPLATE_URL,
		Map("chartTitle" -> "Group duration during Simulation",
			"durationSeriesSuccess" -> durationSeriesSuccess,
			"durationSeriesFailure" -> durationSeriesFailure))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(GROUP_DETAILS_DURATION_HTML_TEMPLATE_URL)
}

