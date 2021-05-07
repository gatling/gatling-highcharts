/*
 * Copyright 2011-2021 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.highcharts.component

import io.gatling.highcharts.template.RequestDetailsIndicatorTemplate

object RequestDetailsIndicatorComponent {

  def apply(): HighchartsComponent = new HighchartsComponent(new RequestDetailsIndicatorTemplate)
}
