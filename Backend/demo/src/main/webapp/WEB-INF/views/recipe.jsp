<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        .recipe-card {
            border: 1px solid #ccc;
            margin-bottom: 10px;
            padding: 10px;
            cursor: pointer;
        }
        .recipe-details {
            display: none;
            margin-top: 10px;
        }
    </style>
    <script>
        function toggleDetails(recipeId) {
            var details = document.getElementById(recipeId);
            details.style.display = details.style.display === 'none' ? 'block' : 'none';
        }
    </script>
</head>
<body>
    <h1>Recipes with Ingredient</h1>
    <c:forEach items="${recipes}" var="recipe">
        <div class="recipe-card" onclick="toggleDetails('details-${recipe.id}')">
            <h2>${recipe.dishName}</h2>
            <div id="details-${recipe.id}" class="recipe-details">
                <h3>Ingredients:</h3>
                <ul>
                    <c:forEach items="${recipe.ingredients}" var="ingredient">
                        <li>${ingredient}</li>
                    </c:forEach>
                </ul>
                <h3>Steps:</h3>
                <ol>
                    <c:forEach items="${recipe.steps}" var="step">
                        <li>${step}</li>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </c:forEach>
</body>

</html>

