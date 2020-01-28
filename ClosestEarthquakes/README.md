Closest EarthQuakes
---

An app to print nearest earthquakes that happened in last month closest to given latitude and longitude
       
Estimations are based on given data:

    https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson

The app fetch data, serializes geojson format and calculate 10 closest earthquakes that happened in last month

Example input:
        
        100
        85

Example output:

Earthquake title || Distance in km from given coordinates

        M 4.4 - 192km ENE of Nord, Greenland || 1373
        M 4.7 - North of Svalbard || 1408
        M 4.8 - North of Franz Josef Land || 1424
        M 5.2 - North of Svalbard || 1634
        M 4.4 - 296km NW of Longyearbyen, Svalbard and Jan Mayen || 1727
        M 1.2 - 66km S of Kaktovik, Alaska || 1747
        M 1.4 - 62km SSW of Kaktovik, Alaska || 1747
        M 2.5 - 64km SSW of Kaktovik, Alaska || 1750
        M 1.8 - 67km SSW of Kaktovik, Alaska || 1751
        M 1.1 - 74km SSW of Kaktovik, Alaska || 1756

Open project with given maven config to run build or tests