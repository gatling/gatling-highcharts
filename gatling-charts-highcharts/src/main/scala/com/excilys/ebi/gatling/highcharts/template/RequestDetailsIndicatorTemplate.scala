/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.template
import com.excilys.ebi.gatling.charts.template.PageTemplate
import com.excilys.ebi.gatling.core.util.PathHelper.path2string
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ REQUEST_DETAILS_INDICATORS_JS_TEMPLATE, REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE }
import com.excilys.ebi.gatling.highcharts.series.{ StackedColumnSeries, PieSeries }

class RequestDetailsIndicatorTemplate(okColumnSeries: StackedColumnSeries, mediumColumnSeries: StackedColumnSeries, koColumnSeries: StackedColumnSeries, failedColumnSeries: StackedColumnSeries, pieSeries: PieSeries) extends Template {

	def getJSContent = {
		PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_INDICATORS_JS_TEMPLATE, Map("pieSeries" -> pieSeries,
			"categories" -> okColumnSeries.getXValues,
			"series" -> List(okColumnSeries, mediumColumnSeries, koColumnSeries, failedColumnSeries)))
	}
	def getHTMLContent = PageTemplate.TEMPLATE_ENGINE.layout(REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE)
}

