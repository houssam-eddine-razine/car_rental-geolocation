<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realtime location tracker</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Leaflet CSS -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" />
    <link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.css" />
    <!-- Leaflet routing CSS -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.css" />

    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
            font-family: Arial, sans-serif;
        }
        #map {
            flex: 3;
            height: 100%;
        }
        #sidebar {
            flex: 1;
            display: flex;
            flex-direction: column;
            padding: 10px;
            box-sizing: border-box;
            background-color: rgba(0, 23, 74, 0.8);
            color: white;
            border-left: 1px solid #ddd;
        }
        #searchBar {
            margin-bottom: 10px;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        #vehicleTable {
            width: 100%;
            border-collapse: collapse;
        }
        #vehicleTable th, #vehicleTable td {
            padding: 8px;
            border: 1px solid #ddd;
            text-align: left;
            color: white;
        }
        #vehicleTable th {
            background-color: #4e73df;
            color: white;
        }
        #vehicleTable tbody tr:hover {
            background-color: #0046004D;
        }
        .table-hover tbody tr:hover {
            background-color: #0046004D;
        }
    </style>

    <script type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/@emailjs/browser@4/dist/email.min.js">
    </script>
    <script type="text/javascript">
        (function(){
            emailjs.init({
                publicKey: "ICy8AWWgmSmEjut-8",
            });
        })();
    </script>
</head>
<body>
    <div id="map"></div>
    <div id="sidebar">
        <input type="text" id="searchBar" class="form-control" placeholder="Entrez la matricule du véhicule">
        <table id="vehicleTable" class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Marque</th>
                    <th>Matricule</th>
                    <th>Vitesse</th>
                    <th>Distance</th>
                    <th>Carburant</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>

    <!-- Leaflet JS -->
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
    <script src="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js"></script>
    <script src="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.js"></script>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        var map = L.map('map').setView([31.6341600, -7.9999400], 6);
        var osm = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        });
        osm.addTo(map);

        var carData = [];

        // Fonction pour afficher un véhicule sur la carte par sa matricule
        function showCarOnMap(matricule) {
            var car = carData.find(c => c.matricule === matricule);
            if (car) {
                var carIcon = L.icon({
                    iconUrl: 'img/' + car.marque + '.png',
                    iconSize: [60, 60]
                });

                var marker = L.marker([car.lat, car.lng], { icon: carIcon }).addTo(map);
                var popupContent = "<b>Car marque:</b> " + car.marque + "<br><b>Speed:</b> " + car.vitesse + " km/h<br><b>Distance parcourue:</b> " + car.distance + " km<br><b>Carburant:</b> " + car.carburant + " %";
                marker.bindPopup(popupContent).openPopup();

                map.setView([car.lat, car.lng], 14);
            } else {
                alert("Véhicule non trouvé!");
            }
        }

        // Fonction pour mettre à jour le tableau des véhicules
        function updateVehicleTable(filteredCars) {
            var tbody = document.querySelector('#vehicleTable tbody');
            tbody.innerHTML = ''; // Vider le tableau
            filteredCars.forEach(function(car) {
                var row = document.createElement('tr');
                row.innerHTML = `
                    <td>${car.marque}</td>
                    <td>${car.matricule}</td>
                    <td>${car.vitesse}</td>
                    <td>${car.distance}</td>
                    <td>${car.carburant}</td>
                `;
                row.addEventListener('click', function() {
                    showCarOnMap(car.matricule);
                });
                tbody.appendChild(row);
            });
        }

        // Récupérer les données des véhicules depuis le serveur
        function fetchVehicleData() {
            fetch('get_vehicles.php')
            .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            if (data.error) {
                console.error('Error from server:', data.error);
            } else {
                carData = data;
                updateVehicleTable(carData);
            }
        })
        .catch(error => console.error('Error fetching vehicle data:', error));
}

        // Afficher tous les véhicules au chargement de la page
        fetchVehicleData();

        // Ajouter un événement de recherche
        document.getElementById('searchBar').addEventListener('keyup', function(event) {
            var query = event.target.value.trim().toUpperCase();
            var filteredCars = carData.filter(function(car) {
                return car.matricule.toUpperCase().includes(query);
            });
            updateVehicleTable(filteredCars);
        });

        // Fonction pour envoyer une alerte par e-mail lorsque le carburant atteint 0
        function sendEmailAlert(car) {
            var params = {
                message: "Alerte: La voiture " + car.marque + " est à court de carburant!",
                email: "hakmaoui01@gmail.com",
                subject: "Alerte Carburant"
            };

            const serviceID = "service_t1khmia";
            const templateID = "template_juw6awx";

            emailjs.send(serviceID, templateID, params)
                .then(res => {
                    console.log(res);
                    alert("Votre message a été envoyé avec succès !");
                })
                .catch(err => console.log(err));
        }

        // Fonction pour calculer la consommation de carburant en fonction de la vitesse et de la distance
        function calculateFuelConsumption(speed, distance) {
            return (speed * distance) / 100;
        }

        // Ajouter la fonction de clic pour créer l'itinéraire
        map.on('click', function (e) {
            carData.forEach(function(car) {
                car.vitesse = Math.floor(Math.random() * (100 - 40 + 1)) + 40;
                var waypoints = [
                    L.latLng(car.lat, car.lng),
                    L.latLng(e.latlng.lat, e.latlng.lng)
                ];

                // Supprimer le contrôle de routage précédent s'il existe
                if (car.routingControl) {
                    map.removeControl(car.routingControl);
                }

                // Créer un nouvel itinéraire
                car.routingControl = L.Routing.control({
                    waypoints: waypoints,
                    routeWhileDragging: true
                }).addTo(map);

                // Mettre à jour la position de la voiture le long de l'itinéraire
                car.routingControl.on('routeselected', function(e) {
                    var route = e.route;
                    var i = 0;
                    var interval = setInterval(function() {
                        var coordinates = route.coordinates[i++];
                        if (i >= route.coordinates.length) {
                            clearInterval(interval);
                        }
                        car.lat = coordinates.lat;
                        car.lng = coordinates.lng;

                        // Calculer la distance restante
                        if (i > 1) {
                            var remainingDistance = route.coordinates[route.coordinates.length - 1].distanceTo(coordinates) / 1000; // Convertir en kilomètres
                            car.distance += remainingDistance;

                            // Calculer la consommation de carburant et mettre à jour la quantité de carburant restante
                            var distance = route.coordinates[i - 1].distanceTo(route.coordinates[i - 2]) / 1000; // Convertir en kilomètres
                            var fuelConsumption = calculateFuelConsumption(car.vitesse, distance);
                            car.carburant -= fuelConsumption;

                            // Vérifier si le carburant est épuisé
                            if (car.carburant <= 0) {
                                // Envoyer une alerte par e-mail
                                sendEmailAlert(car);
                                // Arrêter le suivi de l'itinéraire
                                clearInterval(interval);
                            }
                        }
                    }, 2000);
                });
            });
        });

        // Initialiser le geocoder
        L.Control.geocoder().addTo(map);
    </script>
</body>
</html>
