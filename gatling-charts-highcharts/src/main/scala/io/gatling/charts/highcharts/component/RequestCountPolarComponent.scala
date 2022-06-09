/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.component

import io.gatling.charts.component.Component
import io.gatling.charts.highcharts.template.RequestCountPolarTemplate

private[charts] object RequestCountPolarComponent {
  val Instance: Component = new HighchartsComponent(RequestCountPolarTemplate)
}
