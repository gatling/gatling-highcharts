/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntVsTimePlot, Series }
import io.gatling.highcharts.series.NumberPerSecondSeries
import io.gatling.highcharts.template.ActiveSessionsTemplate

object ActiveSessionsComponent {

	def apply(runStart: Long, series: Seq[Series[IntVsTimePlot]]) = {
		val template = new ActiveSessionsTemplate(runStart, series.map { s => new NumberPerSecondSeries(s.name, runStart, s.data, s.colors.head) })
		new HighchartsComponent(template)
	}
}