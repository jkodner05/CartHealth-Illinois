$(function() {
  /* Load Google Map */
  var gm = google.maps;
  var map_options = {
    center: new gm.LatLng(40.0000, -89.6500),
    zoom: 6,
    draggable: false,
    zoomControl: false,
    scrollwheel: false,
    disableDoubleClickZoom: true,    
    mapTypeId: gm.MapTypeId.ROADMAP
  };
  var map = new gm.Map(document.getElementById('map_canvas'), map_options);
  
  /* Draw Illinois counties */
  var counties = {};
  var borders = {};
  
  function draw_borders(geo_data) {
    $.each(geo_data, function(raw_county_name, coord_list) {
      var county_border = coord_list.map(function(coord_pair) {
          return new gm.LatLng(coord_pair["Lat"],coord_pair["Long"]);
      });
      var county_name = raw_county_name.replace(/\s+/, "");
      borders[county_name] = county_border;
      counties[county_name] = new gm.Polygon({
        paths: county_border,
        strokeColor: '#FFFFFF',
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#FF0000',
        fillOpacity: 0.34
      });
      counties[county_name].setMap(map);
   });
  }
  
  $.ajax({
    type: 'GET',
    url: '/data/counties.json',
    dataType: 'json',
    success: function(geo_data, status, xhr) { draw_borders(geo_data); },
    data: {},
    async: false
  });
  
  /* Create a menu for the user to choose statistics from */
  function create_menu(option_data) {
    $.each(option_data, function(category_name, category_statistics) {
      $("#choose_stats").append("<button class=\"btn btn-default\" id=" + category_name + ">" + category_name + "</button>");
      $("#choose_stats").append("<div id=" + category_name + "-STATS>");
      $.each(category_statistics, function(stat_name, stat_abbr) {
        $("#"+category_name+"-STATS").append("<button class=\"btn btn-default\" id=" + stat_abbr + ">" + stat_name + "</button>");
        $("#"+stat_abbr).click(function() {
          $("#choose_stats div").children().attr("class","btn btn-default");
          $(this).attr("class","btn btn-success");
          var stats = new Array();
          stats[0] = stat_abbr;
          load_data(stats);
          $("#choose_stats div").slideUp();
          $("#map_canvas").slideDown();
          return false;
        });
      });
      $("#"+category_name+"-STATS").slideUp();
      $("#"+category_name).click(function() {
        $("#map_canvas").slideUp();
        $("#choose_stats button").attr("class", "btn btn-default");
        $(this).attr("class","btn btn-info");
        $("#choose_stats div").slideUp();
        $("#"+category_name+"-STATS").slideDown();
        return false;
      });
    });
  }
  
  /* Load list of statistic choices */
  $.ajax({
    type: 'GET',
    url: '/data/stat_vars_new.json',
    dataType: 'json',
    success: function(option_data, status, xhr) { create_menu(option_data); },
    data: {},
    async: false
  });
  
  /* Redraw map based on statistic values */
  function draw_heat_map(color_data, stat) {
    var county_data = color_data[stat];
    $.each(county_data, function(county_name, text) {
      var data = $.parseJSON(text);
      color = "hsl(" + data["color"] + ")";
      if (counties[county_name]) {
        counties[county_name].setOptions({
          paths: borders[county_name],
          strokeColor: '#FFFFFF',
          strokeOpacity: 0.8,
          strokeWeight: 2,
          fillColor: color,
          fillOpacity: 0.34
        });
     }
     else {
      alert("Could not find county: " + county_name + "!");
     }
    });
  }
  
  /* Load data from server for statistics chosen by user */
  function load_data(stats) {
    var bounce = $.ajax({
      type: "GET",
      url: "/services/bounce",
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
        },
      dataType: "json",
      success: function(data, status, xhr) { draw_heat_map(data,stat); },
      data: "stats="+stats,
      async: false
    });
    return false;
  }
});