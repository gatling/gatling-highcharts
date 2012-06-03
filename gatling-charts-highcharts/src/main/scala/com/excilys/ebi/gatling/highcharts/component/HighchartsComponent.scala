/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.component

import com.excilys.ebi.gatling.charts.component.Component
import com.excilys.ebi.gatling.highcharts.config.HighchartsFiles.{ HIGHSTOCK_FILE, HIGHCHARTS_FILE, HIGHCARTS_THEME_FILE }
import com.excilys.ebi.gatling.highcharts.template.Template

class HighchartsComponent(template: Template) extends Component {

	def getHTMLContent: String = template.getHTMLContent

	def getJavascriptContent: String = template.getJSContent

	val getJavascriptFiles: Seq[String] = Seq(HIGHCHARTS_FILE, HIGHSTOCK_FILE, HIGHCARTS_THEME_FILE)
}