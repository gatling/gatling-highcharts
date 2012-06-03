/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.{ PieSeries, NumberPerSecondSeries }
import com.excilys.ebi.gatling.highcharts.template.TransactionsTemplate

object TransactionsComponent {

	def apply(allTransactions: Series[Long, Int], failedTransactions: Series[Long, Int], succeededTransactions: Series[Long, Int], pieSeries: Series[String, Int]) = {
		val template = new TransactionsTemplate(
			Seq(new NumberPerSecondSeries(allTransactions.name, allTransactions.data, allTransactions.colors.head),
				new NumberPerSecondSeries(failedTransactions.name, failedTransactions.data, failedTransactions.colors.head),
				new NumberPerSecondSeries(succeededTransactions.name, succeededTransactions.data, succeededTransactions.colors.head)),
			new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors))

		new HighchartsComponent(template)
	}
}