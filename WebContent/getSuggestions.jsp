<%@page import="java.util.List"%>
<%@page import="dao.CommuneDAO"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String nomCommune = request.getParameter("nomCommune");
    CommuneDAO dao = new CommuneDAO();
    List<String> communes = dao.getAllCommunesNames(nomCommune);

    // Limiter le nombre de suggestions à 10
    int maxSuggestions = 10;
    for (int i = 0; i < Math.min(communes.size(), maxSuggestions); i++) {
        out.println("<p>" + communes.get(i) + "</p>");
    }
%>
