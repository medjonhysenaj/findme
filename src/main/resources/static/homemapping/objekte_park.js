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
            [19.82291437160299, 41.318921183802836],
            [19.824583411887126, 41.31932095582536],
            [19.825124722249544, 41.317220431351785],
            [19.82347372564416, 41.316996823146894],
            // ... additional vertices
            [19.82291437160299, 41.318921183802836]  // Repeat the first vertex to close the polygon
          ],
          // Additional linear ring(s) if the polygon has holes
        ]
      }
    },
    { "type": "Feature", "properties": { "name": "Air Albania", "distance": "1.5 km", "link": "-", "image_url": "images/nightlife/arena.jpg", "address": "Sheshi Italia 1" }, "geometry": { "type": "Point", "coordinates": [ 19.823887376463478, 41.31853471497896 ] } },
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
            [19.82483769589278, 41.3227750752056],
            [19.822570604974043, 41.3223954162711],
            [19.82261229860013, 41.32225255434668],
            [19.82498883528736, 41.32260285905579],
            // ... additional vertices
            [19.82483769589278, 41.3227750752056]  // Repeat the first vertex to close the polygon
          ],
          // Additional linear ring(s) if the polygon has holes
        ]
      }
    },
    { "type": "Feature", "properties": { "name": "Rruga e Sales", "distance": "1km", "link": "-", "image_url": "images/nightlife/sala.jpg", "address": "Rruga: Mustafa Matohiti" }, "geometry": { "type": "Point", "coordinates": [ 19.82427282028387, 41.32258676929099] } },
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
            [19.817639726042582, 41.32255288115236],
            [19.811417001285722, 41.32170684847599],
            [19.811830061456522, 41.32016784658539],
            [19.818346674864348, 41.319970639452286],
            // ... additional vertices
            [19.817639726042582, 41.32255288115236]  // Repeat the first vertex to close the polygon
          ],
          // Additional linear ring(s) if the polygon has holes
        ]
      }
    },
    { "type": "Feature", "properties": { "name": "Rruga Pjeter Bogdani", "distance": "1km", "link": "-", "image_url": "images/nightlife/blloku.jpg", "address": "Rruga Pjeter Bogdani" }, "geometry": { "type": "Point", "coordinates": [19.81693421764345, 41.32211771326503] } },

    // Additional features representing other polygons, if needed
  ]
}





