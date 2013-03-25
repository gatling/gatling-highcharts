/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.highcharts.config.HighchartsFiles.HIGHCHARTS_RESOURCES
import io.gatling.highcharts.template.Template

class HighchartsComponent(template: Template) extends Component {

	def getHTMLContent: String = template.getHTMLContent

	def getJavascriptContent: String = template.getJSContent

	val getJavascriptFiles: Seq[String] = HIGHCHARTS_RESOURCES
}