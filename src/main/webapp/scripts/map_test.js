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
    $.each(geoData, function(rawCountyName, coordList) {
      var countyBorder = coordList.map(function(coordPair) {
          return new gm.LatLng(coordPair["Lat"],coordPair["Long"]);
      });
      var countyName = rawCountyName.replace(/\s+/, "");
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
  }
  
  $.ajax({
    type: 'GET',
    url: '/data/counties.json',
    dataType: 'json',
    success: function(geoData, status, xhr) { drawBorders(geoData); },
    data: {},
    async: false
  });
  
  function listStats(optionData) {
    $.each(optionData, function(stat_name, stat_abbr) {
      $("#stat_vars").append("<option value=" + stat_abbr + ">" + stat_name + "</option>");
    });
  }
  
  /* Load list of statistic choices */
  var statVars = $.ajax({
    type: 'GET',
    url: '/data/stat_vars.json',
    dataType: 'json',
    success: function(optionData, status, xhr) { listStats(optionData); },
    data: {},
    async: false
  });
  
  /* Submit user input */
  function drawHeatMap(colorData, stat) {
    var countyData = colorData[stat];
    $.each(countyData, function(countyName, text) {
      var data = $.parseJSON(text);
      color = "hsl(" + data["color"] + ")";
      if (counties[countyName]) {
        counties[countyName].setOptions({
          paths: borders[countyName],
          strokeColor: '#FFFFFF',
          strokeOpacity: 0.8,
          strokeWeight: 2,
          fillColor: color,
          fillOpacity: 0.34
        });
     }
     else {
      alert("Could not find county: " + countyName + "!");
     }
    });
  }
  $("#loadMap").click(function() {
    var stat = $("#stat_vars").val();
    var stats = new Array();
    stats[0] = stat;
    var bounce = $.ajax({
      type: "GET",
      url: "/services/bounce",
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
        },
      dataType: "json",
      success: function(data, status, xhr) { drawHeatMap(data,stat); },
      data: "stats="+stats,
      async: false
    });
    return false;
  });
});