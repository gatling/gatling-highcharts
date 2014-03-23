/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.component

import io.gatling.core.result.{ IntRangeVsTimePlot, Series }
import io.gatling.highcharts.series.ResponseTimeSeries
import io.gatling.highcharts.template.RequestDetailsResponseTimeTemplate

object RequestDetailsResponseTimeComponent {

  def apply(runStart: Long, responseTimesSuccess: Series[IntRangeVsTimePlot], responseTimesFailures: Series[IntRangeVsTimePlot]) = {
    val template = new RequestDetailsResponseTimeTemplate(
      new ResponseTimeSeries(responseTimesSuccess.name, runStart, responseTimesSuccess.data, responseTimesSuccess.colors.head),
      new ResponseTimeSeries(responseTimesFailures.name, runStart, responseTimesFailures.data, responseTimesFailures.colors.head))

    new HighchartsComponent(template)
  }
}
