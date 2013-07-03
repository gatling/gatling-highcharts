/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Gatling Highcharts License
 */
package io.gatling.highcharts.template

import com.dongxiguo.fastring.Fastring.Implicits._
import io.gatling.core.util.StringHelper

class ErrorTableTemplate(errors: Seq[(String, Int, Int)]) extends Template {

	def js: Fastring = StringHelper.emptyFastring

	def html: Fastring = if (errors.isEmpty)
		StringHelper.emptyFastring
	else
		fast"""<div class="statistics extensible-geant collapsed">
    <div class="title">
        <div class="title_collapsed" style="cursor: auto;">ERRORS</div>
    </div>
    <table id="container_errors" class="statistics-in extensible-geant">
        <thead>
            <tr>
                <th class="header"><span>Error</span></th>
                <th class="header"><span>Count</span></th>
                <th class="header"><span>Percentage</span></th>
            </tr>
        </thead>
		<tbody>
		    ${errors.map { case (message, count, percentage) => fast"""<tr><td class="total">$message</td><td class="value total">$count</td><td class="value total">$percentage %</td></tr>""" }.mkFastring}   
		</tbody>
    </table>
</div>
"""
}
