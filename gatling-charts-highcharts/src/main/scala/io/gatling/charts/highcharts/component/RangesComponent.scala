/*
 * Copyright 2011-2023 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.template.RangesTemplate

private[charts] object RangesComponent {
  def apply(chartTitle: String, eventName: String, large: Boolean): Component = new HighchartsComponent(new RangesTemplate(chartTitle, eventName, large))
}
