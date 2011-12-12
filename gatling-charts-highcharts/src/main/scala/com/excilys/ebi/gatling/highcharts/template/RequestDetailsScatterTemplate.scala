/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template
import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_SCATTER_JS_TEMPLATE, REQUEST_DETAILS_SCATTER_HTML_TEMPLATE }
import com.excilys.ebi.gatling.highcharts.series.ScatterSeries

class RequestDetailsScatterTemplate(chartTitle: String, success: ScatterSeries, failures: ScatterSeries) extends Template {

	def getJSContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_SCATTER_JS_TEMPLATE, Map("successes" -> success, "failures" -> failures))

	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_SCATTER_HTML_TEMPLATE)
}
