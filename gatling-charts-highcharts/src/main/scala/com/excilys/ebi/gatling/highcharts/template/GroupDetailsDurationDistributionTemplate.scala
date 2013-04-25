/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template

import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_JS_TEMPLATE_URL, REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_HTML_TEMPLATE_URL }
import com.excilys.ebi.gatling.highcharts.series.StackedColumnSeries

class GroupDetailsDurationDistributionTemplate(durationSeriesSuccess: StackedColumnSeries, durationSeriesFailure: StackedColumnSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_JS_TEMPLATE_URL,
		Map("chartTitle" -> "Group Duration Distribution",
			"categories" -> durationSeriesSuccess.getXValues,
			"series" -> List(durationSeriesSuccess, durationSeriesFailure)))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_HTML_TEMPLATE_URL)
}

