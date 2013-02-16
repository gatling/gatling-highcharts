/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.core.result.{ IntVsTimePlot, Series }
import com.excilys.ebi.gatling.highcharts.series.NumberPerSecondSeries
import com.excilys.ebi.gatling.highcharts.template.ActiveSessionsTemplate

object ActiveSessionsComponent {

	def apply(runStart: Long, series: Seq[Series[IntVsTimePlot]]) = {
		val template = new ActiveSessionsTemplate(runStart, series.map { s => new NumberPerSecondSeries(s.name, runStart, s.data, s.colors.head) })
		new HighchartsComponent(template)
	}
}