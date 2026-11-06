/*
 * Copyright 2011-2022 Gatling Corp
 *
 * Licensed under the Gatling Highcharts License
 */
Highcharts.theme = {
	// display times in the browser's time zone
	global: {
		useUTC: false
	},

	chart: {
		backgroundColor: '#f7f7f7',
		borderWidth: 0,
		borderRadius: 8,
		plotBackgroundColor: null,
		plotShadow: false,
		plotBorderWidth: 0
	},
	xAxis: {
		gridLineWidth: 0,
		lineColor: '#666',
		tickColor: '#666',
		labels: {
			style: {
				color: '#666'
			}
		},
		title: {
		  style: {
		     color: '#666'
		  }
		}
	},
	yAxis: {
		alternateGridColor: null,
		minorTickInterval: null,
		gridLineColor: '#999',
		lineWidth: 0,
		tickWidth: 0,
		labels: {
			style: {
				color: '#666',
				fontWeight: 'bold'
			}
		},
		title: {
			style: {
				color: '#666',
				font: 'bold 12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			}
		}
	},
	labels: {
		style: {
			color: '#CCC'
		}
	},

	credits: {
		enabled: false
	},

	legend: {
		borderWidth: 0,
		itemStyle: {
			fontWeight: 'normal'
		},
		symbolRadius: 0
	},

	navigator: {
		maskInside: false,
		handles: {
			backgroundColor: '#f7f7f7',
			borderColor: '#92918C'
		},
		outlineColor: '#92918C',
		outlineWidth: 1,
		maskFill: 'rgba(146, 145, 140, 0.5)',
		series: {
			color: '#5E7BE2',
			lineColor: '#5E7BE2'
		}
	},

	scrollbar: {
		buttonBackgroundColor: '#f7f7f7',
		buttonBorderWidth: 1,
		buttonBorderColor: '#92918C',
		buttonArrowColor: '#92918C',
		buttonBorderRadius: 2,

		barBorderWidth: 1,
		barBorderRadius: 0,
		barBackgroundColor: '#92918C',
		barBorderColor: '#92918C',
		
		rifleColor: '#92918C',
		
		trackBackgroundColor: '#b0b0a8',
		trackBorderWidth: 1,
		trackBorderColor: '#b0b0a8'
	}
};

Highcharts.setOptions(Highcharts.theme);
