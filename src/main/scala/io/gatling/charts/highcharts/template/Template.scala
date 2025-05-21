/*
 * Copyright 2011-2025 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Gatling Highcharts License
 */

package io.gatling.charts.highcharts.template

private[highcharts] abstract class Template {
  def js: String

  def html: String

  protected def truncateTimestampToSecond(fullTimestamp: Long): Long =
    fullTimestamp / 1000 * 1000
}
