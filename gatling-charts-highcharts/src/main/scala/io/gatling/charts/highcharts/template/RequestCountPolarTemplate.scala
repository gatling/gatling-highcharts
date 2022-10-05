/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

import io.gatling.charts.report.Container.{ Group, Request }
import io.gatling.charts.util.Color

private[highcharts] object RequestCountPolarTemplate extends Template {
  override def js: String = s"""
function numberOfRequestsDataForGroup(group) {
  var data = {names: [], oks: [], kos: []};

  $$.each(group.contents, function(contentName, content) {
    if (content.type == '$Group') {
      var result = numberOfRequestsDataForGroup(content);
      data.names = data.names.concat(result.names);
      data.oks = data.oks.concat(result.oks);
      data.kos = data.kos.concat(result.kos);
    }
    else if (content.type == '$Request') {
      data.names.push(content.path);
      data.oks.push(parseInt(content.stats.numberOfRequests.ok));
      data.kos.push(parseInt(content.stats.numberOfRequests.ko));
    }
  });

  return data;
}

var numberOfRequestsData = numberOfRequestsDataForGroup(stats);
var tickInterval = Math.ceil(numberOfRequestsData.names.length / 1000);

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
    categories:numberOfRequestsData.names,
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
      data:numberOfRequestsData.oks,
      color:"${Color.Requests.Ok}"
    },
    {
      name:'KO',
      data:numberOfRequestsData.kos,
      color:"${Color.Requests.Ko}"
    }
  ]
});
"""

  override def html: String = """
            <div class="schema polar">
              <div id="container_number_of_requests"></div>
            </div>
"""
}
