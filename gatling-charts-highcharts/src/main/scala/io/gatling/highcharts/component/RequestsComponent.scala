/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ CountsVsTimePlot, IntVsTimePlot, PieSlice, Series }
import io.gatling.highcharts.series.{ EventsPerSecSeries, NumberPerSecondSeries, PieSeries }
import io.gatling.highcharts.template.EventsPerSecTemplate

object RequestsComponent {

  def apply(runStart: Long, counts: Series[CountsVsTimePlot], pieSeries: Series[PieSlice]) = {
    val template = new EventsPerSecTemplate(
      chartTitle = "Number of requests per second",
      yAxisTitle = "Number of requests",
      containerName = "container_requests",
      anchorName = "requests",
      countsSeries = new EventsPerSecSeries(runStart, counts.data, counts.colors),
      pieSeries = new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors),
      pieX = 760)

    new HighchartsComponent(template)
  }
}
