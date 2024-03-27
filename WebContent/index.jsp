<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recherche de Communes</title>
<link href="style.css" rel="stylesheet" />
<link href="style1.css" rel="stylesheet" />
<link href="style2.css" rel="stylesheet" />

<!-- Inclusion de Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
// fonction if the nomCommuneInput value change
$(document).ready(function(){
  $('#nomCommuneInput').keyup(function(){
	// get the value
    var nomCommune = $(this).val();
	// if the value is not null
    if(nomCommune !== ''){
    	// send data to the servlet, get the response and put it in the communeDropdownContainer
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
// If a commune is selected
function selectCommune() {
	  // get the selected value
	  var selectedCommune = $('#communeDropdown').val();
	  // send data to the servlet, (this will get all communes) and put the data to the prelevementAccordion div
	  $.ajax({
	    url: 'SearchPrelevementServlet',
	    method: 'GET',
	    data: {selectedCommune: selectedCommune},
	    success: function(responseText){
	      $('#prelevementAccordion').html(responseText);
	    }
	  });
	}
// function to send the departement code to the servlet and get the asscociated data
function selectDept(){
	var selectedDept = $('#codeDeptInput').val();
	$.ajax({
	    url: 'SearchPrelevementByDeptServlet',
	    method: 'GET',
	    data: {selectedDept: selectedDept},
	    success: function(responseText){
	      $('#prelevementAccordion').html(responseText);
	    }
	  });
}

</script>
<style>
      body {
        background-color: #f9f9f9f9;
      }
    </style>
</head>
<body>
<h2 class="text-align-center justify-content-center">Recherche de Commune</h2>
<div class="container">

<input type="text" placeholder="Recherchez une commune par nom ou code insee" id="nomCommuneInput" name="nomCommune" class="form-control">
<div id="communeDropdownContainer"></div>
<input type="text" placeholder="Entrez le code departement (3 chiffres)" id="codeDeptInput" name="codeDept" class="form-control" onChange="selectDept()">
</div>
<hr>
<div class="accordion accordion-flush" id="prelevementAccordion"></div>
</body>
</html>
