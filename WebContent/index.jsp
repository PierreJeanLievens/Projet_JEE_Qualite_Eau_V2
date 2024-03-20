<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire de recherche</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
		$(document).ready(function() {
		    $('#nomCommune').on('keyup', function() {
		        var inputVal = $(this).val();
		        if(inputVal.length > 0) {
		            $.ajax({
		                url: 'getSuggestions.jsp',
		                type: 'GET',
		                data: { 'nomCommune': inputVal },
		                success: function(response) {
		                    $('#suggestions').html(response);
		                    $('#suggestions p').on('click', function() {
		                        var selectedCommune = $(this).text();
		                        $('#nomCommune').val(selectedCommune);
		                        $('#suggestions').html('');
		
		                        // Effectuer une requête AJAX pour obtenir les prélèvements et les afficher
		                        $.ajax({
		                            url: 'getPrelevements.jsp', // Pointe vers votre JSP ou Servlet qui retourne les prélèvements
		                            type: 'GET',
		                            data: { 'nomCommune': selectedCommune },
		                            success: function(data) {
		                                // Afficher les données dans un élément de votre choix
		                                $('#prelevementsContainer').html(data);
		                            }
		                        });
		                    });
		                }
		            });
		        } else {
		            $('#suggestions').html('');
		        }
		    });
		});
	</script>


</head>
<body>
    <h2>Formulaire de recherche</h2>
    <input type="text" id="nomCommune" name="nomCommune" autocomplete="off">
    <div id="suggestions"></div>
    <div id="prelevementsContainer"></div>
    
</body>
</html>
