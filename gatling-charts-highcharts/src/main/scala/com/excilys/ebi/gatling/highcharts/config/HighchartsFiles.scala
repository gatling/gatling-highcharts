/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.config

import com.excilys.ebi.gatling.charts.config.ChartsFiles.GATLING_TEMPLATE

object HighchartsFiles {
	val ACTIVE_SESSIONS_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "active_sessions.html.ssp"
	val ACTIVE_SESSIONS_JS_TEMPLATE_URL = GATLING_TEMPLATE + "active_sessions.js.ssp"
	val ALL_SESSIONS_JS_TEMPLATE_URL = GATLING_TEMPLATE + "all_sessions.js.ssp"
	val REQUEST_DETAILS_INDICATORS_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_indic.html.ssp"
	val REQUEST_DETAILS_INDICATORS_JS_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_indic.js.ssp"
	val REQUEST_DETAILS_RESPONSE_TIME_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_resp_time.html.ssp"
	val REQUEST_DETAILS_RESPONSE_TIME_JS_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_resp_time.js.ssp"
	val REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_resp_time_distrib.html.ssp"
	val REQUEST_DETAILS_RESPONSE_TIME_DISTRIBUTION_JS_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_resp_time_distrib.js.ssp"
	val REQUEST_DETAILS_LATENCY_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_latency.html.ssp"
	val REQUEST_DETAILS_LATENCY_JS_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_latency.js.ssp"
	val REQUEST_DETAILS_SCATTER_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_scatter.html.ssp"
	val REQUEST_DETAILS_SCATTER_JS_TEMPLATE_URL = GATLING_TEMPLATE + "req_details_scatter.js.ssp"
	val REQUESTS_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "requests.html.ssp"
	val REQUESTS_JS_TEMPLATE_URL = GATLING_TEMPLATE + "requests.js.ssp"
	val TRANSACTIONS_HTML_TEMPLATE_URL = GATLING_TEMPLATE + "transactions.html.ssp"
	val TRANSACTIONS_JS_TEMPLATE_URL = GATLING_TEMPLATE + "transactions.js.ssp"

	val HIGHCARTS_THEME_FILE = "theme.js"
	val HIGHCHARTS_FILE = "highcharts.js"
	val HIGHSTOCK_FILE = "highstock.js"
}