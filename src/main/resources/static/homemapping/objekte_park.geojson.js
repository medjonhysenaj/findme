var objpark = {
"type": "FeatureCollection",
"name": "test",
  "features": [
    {
      "type": "Feature",
      "properties": {
        "name": "Air Albania", "image_url": "images/apartment/apartment.jpeg", "address": "Sheshi Italia 1"
      },
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          // Array of linear ring(s), where each linear ring is an array of coordinates
          [
            [19.810119485242343, 41.30960343562865],
            [19.80775914141615, 41.307209867923405],
            [19.80920753423268, 41.30638781426949],
            [19.811364030182975, 41.3083059232712],
            // ... additional vertices
            [19.810119485242343, 41.30960343562865]  // Repeat the first vertex to close the polygon
          ],
          // Additional linear ring(s) if the polygon has holes
        ]
      }
    },
    { "type": "Feature", "properties": { "name": "Parku Zoologjik i Tiranës", "distance": "3 km", "link": "https://aprtirana.al/parqe-rekreative/parku-zoologjik/", "image_url": "images/park/zoologjik.jpg", "address": "Kopshti Zoologjik" }, "geometry": { "type": "Point", "coordinates": [ 19.8096474164771, 41.30819309492137 ] } },
    {
      "type": "Feature",
      "properties": {
        "name": "Rruga e Sales", "image_url": "images/nightlife/sala.jpg", "address": "Rruga: Mustafa Matohiti"
      },
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          // Array of linear ring(s), where each linear ring is an array of coordinates
          [
            [19.805914902603742, 41.31197892233384],
            [19.80336586132126, 41.31005284558433],
            [19.80652181719481, 41.30804693001954],
            [19.808873611235192, 41.31123813031827],
            // ... additional vertices
            [19.805914902603742, 41.31197892233384]  // Repeat the first vertex to close the polygon
          ],
          // Additional linear ring(s) if the polygon has holes
        ]
      }
    },
    { "type": "Feature", "properties": { "name": "The Botanical Park of Tirana", "distance": "3 km", "link": "https://aprtirana.al/parqe-rekreative/parku-zoologjik/", "image_url": "images/park/botanik.jpg", "address": "8R65+48V, Rruga Selita e Vjeter" }, "geometry": { "type": "Point", "coordinates": [ 19.806036285521955, 41.310474535891906 ] } },
    {
      "type": "Feature",
      "properties": {
        "name": "Rruga Pjeter Bogdani", "image_url": "images/nightlife/blloku.jpg", "address": "Rruga: Mustafa Matohiti"
      },
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          // Array of linear ring(s), where each linear ring is an array of coordinates
          [
            [19.837683861469518, 41.30763031628254],
            [19.822148507558925, 41.30572830637073],
            [19.814337915261337, 41.31214333811297],
            [19.82957286177587, 41.31820319027792],
            // ... additional vertices
            [19.837683861469518, 41.30763031628254]  // Repeat the first vertex to close the polygon
          ],
          // Additional linear ring(s) if the polygon has holes
        ]
      }
    },
    { "type": "Feature", "properties": { "name": "Grand Park of Tirana", "distance": "2.2 km", "link": "https://aprtirana.al/", "image_url": "images/park/liqeni.jpg", "address": "8R6G+W32, Rruga Herman Gmeiner" }, "geometry": { "type": "Point", "coordinates": [ 19.82515258151954, 41.31307813929067 ] } },

    // Additional features representing other polygons, if needed
  ]
}





