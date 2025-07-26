var objnightlife = {
{
  "type": "FeatureCollection",
  "features": [
    {
      "type": "Feature",
      "properties": {
        "name": "Klara's Apartment", "phone": "067 69 67 670", "image_url": "images/apartment/apartment.jpeg", "address": "Rruga: Dede Gjo Luli"
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
    // Additional features representing other polygons, if needed
  ]
}