<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recherche de Communes</title>
<link href="style.css" rel="stylesheet" />
<!-- Inclusion de Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $('#nomCommuneInput').keyup(function(){
    var nomCommune = $(this).val();
    if(nomCommune !== ''){
      $.ajax({
        url: 'CommuneSearchServlet',
        method: 'POST',
        data: {nomCommune: nomCommune},
        success: function(responseText){
          $('#communeDropdownContainer').html(responseText);
        }
      });
    } else {
      $('#communeDropdownContainer').html('');
    }
  });
});

function selectCommune() {
	  var selectedCommune = $('#communeDropdown').val();
	  $.ajax({
	    url: 'SearchPrelevementServlet',
	    method: 'GET',
	    data: {selectedCommune: selectedCommune},
	    success: function(responseText){
	      $('#prelevementAccordion').html(responseText);
	    }
	  });
	}

</script>

</head>
<body>
<h2 class="text-align-center justify-content-center">Recherche de Commune</h2>
<div class="container">
<div id=div_form>
<input type="text" placeholder="Recherchez une commune" id="nomCommuneInput" name="nomCommune">
<div id="communeDropdownContainer"></div>
</div>
<div class="accordion" id="prelevementAccordion"></div></div>
</body>
</html>
