$(function() {
    var gm = google.maps
    var mapOptions = {
      center: new gm.LatLng(40.0000, -89.6500),
      zoom: 6,
      mapTypeId: gm.MapTypeId.ROADMAP
    }
    var map = new gm.Map(document.getElementById('map_canvas'), mapOptions);
    var kmlUrl = "./Illinois_counties.kml";
    var kmlOptions = {
      suppressInfoWindows: true,
      preserveViewport: false,
      map: map
    }
    var kmlLayer = new gm.KmlLayer(kmlUrl, kmlOptions);
});