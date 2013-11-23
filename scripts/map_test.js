$(function() {
  var gm = google.maps
  var mapOptions = {
    center: new gm.LatLng(40.0000, -89.6500),
    zoom: 6,
    draggable: false,
    zoomControl: false,
    scrollwheel: false,
    disableDoubleClickZoom: true,    
    mapTypeId: gm.MapTypeId.ROADMAP
  }
  var layerOptions = {
    strokeColor: '#FFFFFF',
    strokeOpacity: 0.8,
    strokeWeight: 2,
    fillColor: '#FF0000',
    fillOpacity: 0.34
  }
  var map = new gm.Map(document.getElementById('mapCanvas'), mapOptions);
  var geoJSON = $.ajax({
    type: 'GET',
    url: '../data/counties.json',
    dataType: 'json',
    success: function() {},
    data: {},
    async: false
  });
  var geoData = $.parseJSON(geoJSON["responseText"]);
  var counties = {};
  var borders = {};
  $.each(geoData, function(countyName, coordList) {
    var countyBorder = coordList.map(
      function(coordPair) {
        return new gm.LatLng(coordPair["Lat"],coordPair["Long"]);
      }
    );
    layerOptions.paths = countyBorder;
    borders[countyName] = countyBorder;
    counties[countyName] = new gm.Polygon(layerOptions);
    counties[countyName].setMap(map);
  });
  geoData = null;
  function changeColor(countyName,color) {
    layerOptions.fillColor = color;
    layerOptions.paths=borders[countyName];
    counties[countyName].setOptions(layerOptions);
  }
  $("#changeColor").click(function() {
    var countyName = $("#countyName").val();
    changeColor(countyName,"#0000FF");
    return false;
  });
});