$(function() {
  /* Load Google Map */
  var gm = google.maps;
  var mapOptions = {
    center: new gm.LatLng(40.0000, -89.6500),
    zoom: 6,
    draggable: false,
    zoomControl: false,
    scrollwheel: false,
    disableDoubleClickZoom: true,    
    mapTypeId: gm.MapTypeId.ROADMAP
  };
  var map = new gm.Map(document.getElementById('mapCanvas'), mapOptions);
  
  /* Draw Illinois counties */
  var counties = {};
  var borders = {};
  
  function drawBorders(geoData) {
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
  
  var geoJSON = $.ajax({
    type: 'GET',
    url: '/data/counties.json',
    dataType: 'json',
    success: function(geoData, status, xhr) { drawBorders(geoData); },
    data: {},
    async: false
  });
  
  alert(geoJSON);
  
  /* Load list of statistic choices */
  var statVars = $.ajax({
    type: 'GET',
    url: '/data/stat_vars.json',
    dataType: 'json',
    success: function() {},
    data: {},
    async: false
  });
  alert(statVars);
  var optionData = $.parseJSON(statVars["responseText"]);
  alert(optionData);
  $.each(optionData, function(stat_name, stat_abbr) {
    $("#stat_vars").append("<option value=" + stat_abbr + ">" + stat_name + "</option>")
  });
  
  /* Submit user input */
  $("#changeColor").click(function() {
    var countyName = $("#countyName").val();
    var bounce = $.ajax({
      type: "GET",
      url: "/services/bounce",
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
        },
      dataType: "json",
      success: function() {},
      data: "stat="+countyName,
      async: false
    });
    alert(bounce);
    alert(JSON.stringify(bounce));
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