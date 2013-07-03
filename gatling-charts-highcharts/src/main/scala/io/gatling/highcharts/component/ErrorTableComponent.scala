/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.highcharts.template.ErrorTableTemplate

object ErrorTableComponent {

	def apply(errors: Seq[(String, Int, Int)]) = {
		val template = new ErrorTableTemplate(errors)

		new HighchartsComponent(template)
	}
}
