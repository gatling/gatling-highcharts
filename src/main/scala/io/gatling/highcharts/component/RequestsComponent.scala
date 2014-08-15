/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntVsTimePlot, PieSlice, Series }
import io.gatling.highcharts.series.{ NumberPerSecondSeries, PieSeries }
import io.gatling.highcharts.template.EventsPerSecTemplate

object RequestsComponent {

  def apply(runStart: Long, allRequests: Series[IntVsTimePlot], failedRequests: Series[IntVsTimePlot], succeededRequests: Series[IntVsTimePlot], pieSeries: Series[PieSlice]) = {
    val template = new EventsPerSecTemplate(
      chartTitle = "Number of requests per second",
      yAxisTitle = "Number of requests",
      containerName = "container_requests",
      anchorName = "requests",
      series = Seq(new NumberPerSecondSeries(allRequests.name, runStart, allRequests.data, allRequests.colors.head),
        new NumberPerSecondSeries(failedRequests.name, runStart, failedRequests.data, failedRequests.colors.head),
        new NumberPerSecondSeries(succeededRequests.name, runStart, succeededRequests.data, succeededRequests.colors.head)),
      pieSeries = new PieSeries(pieSeries.name, pieSeries.data, pieSeries.colors))

    new HighchartsComponent(template)
  }
}
