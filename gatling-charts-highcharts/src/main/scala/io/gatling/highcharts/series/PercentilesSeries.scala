/**
 * Copyright 2011-2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.series

import com.dongxiguo.fastring.Fastring.Implicits._
import io.gatling.core.result.{ PercentilesVsTimePlot, Series }

class PercentilesSeries(name: String, runStart: Long, data: Iterable[PercentilesVsTimePlot], colors: List[String])
  extends Series[PercentilesVsTimePlot](name, data, colors) {

  def render: Fastring = {
    fast"[${data.map(renderPercentilesVsTimePlot).mkString(",")}"
  }

  def renderPercentilesVsTimePlot(perc: PercentilesVsTimePlot) = {
    fast"[${(runStart + perc.time) / 1000}, [${perc.percentiles.map(_.productIterator.mkString(",")).getOrElse("null")}]]"
  }
}
