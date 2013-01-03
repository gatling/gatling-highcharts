/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.StackedColumnSeries
import com.excilys.ebi.gatling.highcharts.template.GroupDetailsDurationDistributionTemplate

object GroupDetailsDurationDistributionComponent {

	def apply(durationDistribution: Series[Int, Int]) = {
		val template = new GroupDetailsDurationDistributionTemplate(
			new StackedColumnSeries(durationDistribution.name, durationDistribution.data.map { case (time, count) => (time.toString -> count) }, durationDistribution.colors.head))

		new HighchartsComponent(template)
	}
}