/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.HIGHCHARTS_RESOURCES
import com.excilys.ebi.gatling.highcharts.template.Template

class HighchartsComponent(template: Template) extends Component {

	def getHTMLContent: String = template.getHTMLContent

	def getJavascriptContent: String = template.getJSContent

	val getJavascriptFiles: Seq[String] = HIGHCHARTS_RESOURCES
}