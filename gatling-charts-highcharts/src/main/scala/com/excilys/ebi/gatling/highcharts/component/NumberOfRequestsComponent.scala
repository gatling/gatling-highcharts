/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.highcharts.template.NumberOfRequestsTemplate

object NumberOfRequestsComponent {
	def apply() = new HighchartsComponent(new NumberOfRequestsTemplate)
}
