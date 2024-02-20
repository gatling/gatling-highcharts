/*
 * Copyright 2011-2024 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.template.Template

private[highcharts] final class HighchartsComponent(template: Template) extends Component {
  def html: String = template.html

  def js: String = template.js

  val jsFiles: Seq[String] = List("highstock.js", "highcharts-more.js", "theme.js", "unpack.js")
}
