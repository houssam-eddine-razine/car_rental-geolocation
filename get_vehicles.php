<?php
header('Content-Type: application/json'); // Assurez-vous que le contenu renvoyé est du JSON

$servername = "localhost"; 
$username = "root"; 
$password = "Root123root"; 
$dbname = "vehicle_tracker";

// Activer les erreurs pour le débogage
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Créer une connexion
$conn = new mysqli($servername, $username, $password, $dbname);

// Vérifier la connexion
if ($conn->connect_error) {
    die(json_encode(array('error' => 'Connection failed: ' . $conn->connect_error)));
}

// Récupérer les données des véhicules
$sql = "SELECT id, marque, matricule, lat, lng, vitesse, distance, carburant FROM vehicles";
$result = $conn->query($sql);

$vehicles = array();

if ($result->num_rows > 0) {
    // Stocker les données dans un tableau associatif
    while($row = $result->fetch_assoc()) {
        $vehicles[] = $row;
    }
} else {
    echo json_encode(array('error' => 'No results found'));
}
$conn->close();
// Convertir le tableau en JSON
echo json_encode($vehicles);
?>