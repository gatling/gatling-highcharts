/**
 * Copyright 2011-2015 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.stats._
import io.gatling.highcharts.series.PercentilesSeries
import io.gatling.highcharts.template.PercentilesOverTimeTemplate

object RequestDetailsLatencyComponent {

  def apply(runStart: Long, latencySuccess: Series[PercentilesVsTimePlot]) = {
    val template = new PercentilesOverTimeTemplate(
      "container_latency",
      "Latency (ms)",
      new PercentilesSeries(latencySuccess.name, runStart, latencySuccess.data, latencySuccess.colors))

    new HighchartsComponent(template)
  }
}
