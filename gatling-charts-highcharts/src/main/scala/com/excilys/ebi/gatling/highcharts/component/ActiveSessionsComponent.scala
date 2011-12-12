/**
 * Copyright 2011 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import org.joda.time.DateTime
import com.excilys.ebi.gatling.charts.series.Series
import com.excilys.ebi.gatling.highcharts.series.ResponseTimeSeries
import com.excilys.ebi.gatling.highcharts.template.ActiveSessionsTemplate
import com.excilys.ebi.gatling.highcharts.series.NumberPerSecondSeries

class ActiveSessionsComponent(series: Series[DateTime, Int]*)
	extends HighchartsComponent(new ActiveSessionsTemplate("Active Sessions along the simulation", series.map { s => new NumberPerSecondSeries(s.name, s.data, s.colors.head) }))