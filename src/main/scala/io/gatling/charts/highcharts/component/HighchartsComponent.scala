/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.template.Template

private[highcharts] final class HighchartsComponent(template: Template) extends Component {
  override def html: String = template.html

  override def js: String = template.js

  override def jsFiles: Seq[String] = List("highstock.js", "highcharts-more.js", "theme.js")
}
