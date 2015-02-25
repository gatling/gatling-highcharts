'use strict';

var unpack = function (array) {
  var i, j;
  var nbPlots = array.length;
  var nbSeries = array[0][1].length;

  // Prepare unpacked array
  var unpackedArray = new Array(nbSeries);

  for (i = 0; i < nbSeries; i++) {
    unpackedArray[i] = new Array(nbPlots);
  }

  // Unpack the array
  for (i = 0; i < nbPlots; i++) {
    var timestamp = array[i][0];
    var values = array[i][1];
    for (j = 0; j < nbSeries; j++) {
      unpackedArray[j][i] = [timestamp * 1000, values[j]];
    }
  }

  return unpackedArray;
};
