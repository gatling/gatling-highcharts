/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._

import io.gatling.charts.report.Container.{ Group, Request }

class NumberOfRequestsTemplate(numberOfRequestNames: Int) extends Template {

  val js = fast"""
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
    useHTML: true
  },
  xAxis:{
    tickmarkPlacement:'on',
    tickInterval: ${math.ceil(numberOfRequestNames.toDouble / 1000).toInt},
    categories:numberOfRequestsData.names,
    labels:{ enabled:false }
  },
  yAxis:{
    min:0
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
    	borderWidth: 0.5,
      borderRadius: 3,
      borderColor: 'black',
      itemStyle: { fontWeight: "normal" }
  },
  series:[
    {
      name:'KO',
      data:numberOfRequestsData.kos,
      color:"#FF0000"
    },
    {
      name:'OK',
      data:numberOfRequestsData.oks,
      color:"#4572A7"
    }
  ]
});
"""

  val html = fast"""
            <div class="schema polar">
              <div id="container_number_of_requests"></div>
            </div>
"""
}
