/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.highcharts.template.Template

class HighchartsComponent(template: Template) extends Component {

  def html = template.html

  def js = template.js

  val jsFiles: Seq[String] = List("highstock.js", "highcharts-more.js", "theme.js", "unpack.js")
}
