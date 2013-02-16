/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.core.result.{ IntVsTimePlot, PieSlice, Series }
import com.excilys.ebi.gatling.highcharts.series.{ NumberPerSecondSeries, PieSeries }
import com.excilys.ebi.gatling.highcharts.template.TransactionsTemplate

object TransactionsComponent {

	def apply(runStart: Long, allTransactions: Series[IntVsTimePlot], failedTransactions: Series[IntVsTimePlot], succeededTransactions: Series[IntVsTimePlot], pieSeries: Series[PieSlice]) = {
		val template = new TransactionsTemplate(
			Seq(new NumberPerSecondSeries(allTransactions.name, runStart, allTransactions.data, allTransactions.colors.head),
				new NumberPerSecondSeries(failedTransactions.name, runStart, failedTransactions.data, failedTransactions.colors.head),
				new NumberPerSecondSeries(succeededTransactions.name, runStart, succeededTransactions.data, succeededTransactions.colors.head)),
			new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors))

		new HighchartsComponent(template)
	}
}