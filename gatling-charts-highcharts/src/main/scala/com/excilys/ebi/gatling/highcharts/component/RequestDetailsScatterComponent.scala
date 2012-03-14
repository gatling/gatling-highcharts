/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component
import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsScatterTemplate
import com.excilys.ebi.gatling.highcharts.series.ScatterSeries

class RequestDetailsScatterComponent(success: Series[Int, Long], failures: Series[Int, Long])
	extends HighchartsComponent(
		new RequestDetailsScatterTemplate("Active Sessions along the simulation",
			new ScatterSeries(success.name, success.data, success.colors.head),
			new ScatterSeries(failures.name, failures.data, failures.colors.head)))