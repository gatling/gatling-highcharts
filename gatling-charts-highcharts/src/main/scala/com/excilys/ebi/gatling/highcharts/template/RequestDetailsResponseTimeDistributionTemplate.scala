/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template
import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_JS_TEMPLATE_URL, REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_HTML_TEMPLATE_URL }
import com.excilys.ebi.gatling.highcharts.series.StackedColumnSeries

class RequestDetailsResponseTimeDistributionTemplate(chartTitle: String, successSeries: StackedColumnSeries, failuresSeries: StackedColumnSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_JS_TEMPLATE_URL, Map("chartTitle" -> chartTitle,
		"categories" -> successSeries.getXValues,
		"series" -> List(successSeries, failuresSeries)))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_HTML_TEMPLATE_URL)
}

