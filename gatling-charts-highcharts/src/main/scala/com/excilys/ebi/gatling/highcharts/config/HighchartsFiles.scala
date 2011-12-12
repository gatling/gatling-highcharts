/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.config

import org.joda.time.DateTime
import scala.tools.nsc.io.Path.string2path
import com.excilys.ebi.gatling.charts.config.ChartsFiles._

object HighchartsFiles {
	val ACTIVE_SESSIONS_HTML_TEMPLATE = GATLING_TEMPLATE / "active_sessions.html.ssp"
	val ACTIVE_SESSIONS_JS_TEMPLATE = GATLING_TEMPLATE / "active_sessions.js.ssp"
	val REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE = GATLING_TEMPLATE / "req_details_indic.html.ssp"
	val REQUEST_DETAILS_INDICATORS_JS_TEMPLATE = GATLING_TEMPLATE / "req_details_indic.js.ssp"
	val REQUEST_DETAILS_RESPONSE_TIME_HTML_TEMPLATE = GATLING_TEMPLATE / "req_details_resp_time.html.ssp"
	val REQUEST_DETAILS_RESPONSE_TIME_JS_TEMPLATE = GATLING_TEMPLATE / "req_details_resp_time.js.ssp"
	val REQUEST_DETAILS_SCATTER_HTML_TEMPLATE = GATLING_TEMPLATE / "req_details_scatter.html.ssp"
	val REQUEST_DETAILS_SCATTER_JS_TEMPLATE = GATLING_TEMPLATE / "req_details_scatter.js.ssp"
	val REQUESTS_HTML_TEMPLATE = GATLING_TEMPLATE / "requests.html.ssp"
	val REQUESTS_JS_TEMPLATE = GATLING_TEMPLATE / "requests.js.ssp"

	val HIGHCHARTS_FILE = "highcharts.js"
	val HIGHSTOCK_FILE = "highstock.js"

	def printHighChartsDate(dateTime: DateTime) = dateTime.getMillis.toString
}