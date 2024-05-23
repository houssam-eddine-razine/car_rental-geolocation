<?php
// Database configuration
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "pfa";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Log received POST data
file_put_contents('php://stderr', print_r($_POST, TRUE));

// Get POST data
$city = $_POST["city"];
$region = $_POST["region"];
$country = $_POST["country"];
$latitude = $_POST["latitude"];
$longitude = $_POST["longitude"];

// Insert data into the database
$sql = "INSERT INTO locV (city, region, country, latitude, longitude) VALUES ('$city', '$region', '$country', '$latitude', '$longitude')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
