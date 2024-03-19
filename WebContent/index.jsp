<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.CommuneDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire de recherche</title>
</head>
<body>
<% 
    response.setContentType("text/html");
    List<String> nomCommunes = new ArrayList<String>();
    CommuneDAO dao = new CommuneDAO();
    Cookie[] cookies = request.getCookies();
    String nomCommune = null;
    if(cookies != null) {
        for (Cookie cookie : cookies) {
            if ("NameCommune".equals(cookie.getName())) {
                nomCommune = cookie.getValue();
                break;
            }
        }
    }
    if (nomCommune == null) {
        // This is the user's first visit or no country was selected before
        nomCommunes = null;
        
    }else{
        nomCommunes = dao.getAllCommunesNames(nomCommune);
    }
%>

<h2>Formulaire de recherche</h2>
<form action="CommuneSearchServlet" method="post">
    <label for="nomCommune">Nom de la commune :</label>
    <input type="text" id="nomCommune" name="nomCommune" value="<%= nomCommune %>">
    <br><br>
    <label for="menuDeroulant">Commune :</label>
    <select id="menuDeroulant" name="menuDeroulant">
        <% 
            if (nomCommunes != null) {
                for (String commune : nomCommunes) {
                    out.println("<option value=\"" + commune + "\">" + commune + "</option>");
                }
            }
        %>
    </select>
    <br><br>
    <input type="submit" value="Rechercher">
</form>

</body>
</html>
