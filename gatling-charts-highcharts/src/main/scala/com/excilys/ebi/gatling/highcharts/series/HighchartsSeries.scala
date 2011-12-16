/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Gatling Highcharts License
 */
package com.excilys.ebi.gatling.highcharts.series

class HighchartsSeries[X, Y](val name: String, val data: List[(X, Y)], val colors: List[String])
