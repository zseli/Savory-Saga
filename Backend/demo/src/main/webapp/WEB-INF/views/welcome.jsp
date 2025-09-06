<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="pr.css">
    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
        }
 
        .background {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: url("https://c4.wallpaperflare.com/wallpaper/789/80/989/food-pizza-wallpaper-preview.jpg");
            background-size: cover;
            background-position: center;
            filter: blur(5px); /* Adjust the blur amount as needed */
        }
 
        .form-container {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            z-index: 1; /* Ensure the form container is above the blurred background */
            text-align: center; /* Center the content horizontally */
        }
 
        form {
            max-width: 400px;
            margin: 0 auto;
        }
 
        /* Updated styling for the button */
        button {
            type: submit;
            padding: 10px 20px;
            background-color: black; /* Change the background color to black */
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px; /* Add some space above the button */
        }
 
        button:hover {
            background-color: #333; /* Change the hover color if needed */
        }
 
        /* Add space between the form groups */
        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="background"></div>
    <div class="form-container">
        <h1 class="text-center mb-4">Welcome</h1>
        <form action="recipe" method="get">
            <div class="form-group">
                <label for="ingredient1">Ingredient 1:</label>
                <input type="text" class="form-control" id="ingredient1" name="ingredient1">
            </div>
            <div class="form-group">
                <label for="ingredient2">Ingredient 2:</label>
                <input type="text" class="form-control" id="ingredient2" name="ingredient2">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>
</body>
</html>