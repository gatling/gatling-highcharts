/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.report.{ GroupContainer, RequestContainer }
import io.gatling.charts.util.Color

private[highcharts] final class RequestCountPolarTemplate(rootContainer: GroupContainer) extends Template {
  override def js: String = {

    @SuppressWarnings(Array("org.wartremover.warts.Recursion"))
    def collectRequestContainersRec(groupContainer: GroupContainer): List[RequestContainer] =
      groupContainer.groups.values.flatMap(collectRequestContainersRec).toList ::: groupContainer.requests.values.toList

    val requestContainers = collectRequestContainersRec(rootContainer)
    val ticksInterval = math.ceil(requestContainers.size / 1000.0)
    val categories = requestContainers.map(_.name).mkString("['", "', '", "']")
    val okData = requestContainers.map(_.stats.numberOfRequestsStatistics.success).mkString("[", ", ", "]")
    val koData = requestContainers.map(_.stats.numberOfRequestsStatistics.failure).mkString("[", ", ", "]")

    s"""
var tickInterval = $ticksInterval;

new Highcharts.Chart({
  chart: {
    renderTo:'container_number_of_requests',
    polar:true,
    type:'column',
    height:330
  },
  credits:{
    enabled:false
  },
  title:{
    text:'<span class="chart_title">Number of requests</span>',
    useHTML: true,
    widthAdjust:-20
  },
  xAxis:{
    tickmarkPlacement:'on',
    tickInterval: tickInterval,
    categories: $categories,
    labels:{ enabled:false }
  },
  yAxis:{
    min:0,
    reversedStacks: false
  },
  plotOptions:{
    series:{
      stacking:'normal',
      groupPadding:0,
      pointPlacement:'on',
      shadow: true
    }
  },
  legend: {
      borderWidth: 0,
      itemStyle: { fontWeight: "normal" },
      symbolRadius: 0
  },
  series:[
      {
      name:'OK',
      data:$okData,
      color:"${Color.Requests.Ok}"
    },
    {
      name:'KO',
      data:$koData,
      color:"${Color.Requests.Ko}"
    }
  ]
});
"""
  }

  override def html: String = """
            <div class="schema polar">
              <div id="container_number_of_requests"></div>
            </div>
"""
}
