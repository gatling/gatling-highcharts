/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component
import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.template.RequestDetailsIndicatorTemplate
import com.excilys.ebi.gatling.highcharts.series.StackedColumnSeries
import com.excilys.ebi.gatling.highcharts.series.PieSeries

class RequestDetailsIndicatorComponent(columnSeries: Series[String, Int], pieSeries: Series[String, Int])
	extends HighchartsComponent({
		val d = columnSeries.data
		val colors = columnSeries.colors
		val d0 = d.map { entry => (entry._1, 0) };
		new RequestDetailsIndicatorTemplate(
			new StackedColumnSeries("OK", List(d(0), d0(1), d0(2), d0(3)), colors(0)),
			new StackedColumnSeries("Acceptable", List(d0(0), d(1), d0(2), d0(3)), colors(1)),
			new StackedColumnSeries("Off Boundaries", List(d0(0), d0(1), d(2), d0(3)), colors(2)),
			new StackedColumnSeries("Failed", List(d0(0), d0(1), d0(2), d(3)), colors(3)),
			new PieSeries("Percentages", pieSeries.data, pieSeries.colors))
	})