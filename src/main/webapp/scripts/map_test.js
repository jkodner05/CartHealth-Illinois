$(function() {
  alert("hi");
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
  var map = new gm.Map(document.getElementById('mapCanvas'), mapOptions);
  var geoJSON = $.ajax({
    type: 'GET',
    url: '../data/counties.json',
    dataType: 'json',
    success: function(blah) {alert("Google: "); alert(json.stringify(blah));},
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
    borders[countyName] = countyBorder;
    counties[countyName] = new gm.Polygon({
      paths: countyBorder,
      strokeColor: '#FFFFFF',
      strokeOpacity: 0.8,
      strokeWeight: 2,
      fillColor: '#FF0000',
      fillOpacity: 0.34
    });
    counties[countyName].setMap(map);
  });
  geoData = null;
  $("#changeColor").click(function() {
    var bounce = $.ajax({
      type: 'POST',
      url: '../bounce',
      dataType: 'json',
      success: function(response) {
          alert(json.stringify(response));
        },
      data: ["stat1","stat2","stat3"],
      async: false
    });
    alert(bounce);
    var countyName = $("#countyName").val();
    counties[countyName].setOptions({
      paths: borders[countyName],
      strokeColor: '#FFFFFF',
      strokeOpacity: 0.8,
      strokeWeight: 2,
      fillColor: '#0000FF',
      fillOpacity: 0.34
    });
    return false;
  });
});