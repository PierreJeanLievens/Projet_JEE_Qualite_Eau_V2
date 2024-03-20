<%@page import="java.util.List"%>
<%@page import="dao.PrelevementDAO"%>
<%@page import="Model.Prelevement"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String nomCommune = request.getParameter("nomCommune");
    PrelevementDAO prelevementDao = new PrelevementDAO();
    List<Prelevement> prelevements = prelevementDao.getPrelevements(nomCommune);

    if (prelevements != null && !prelevements.isEmpty()) {
        out.println("<ul>");
        for (Prelevement prelevement : prelevements) {
            out.println("<li id='" + prelevement.getReference_prelevement() + "'>");
            out.println("<strong>Ville :</strong> " + nomCommune);
            out.println("<br><strong>Conclusion :</strong> " + prelevement.getConclusion());
            out.println("<br><strong>Date :</strong> " + prelevement.getDate());
            out.println("</li>");
        }
        out.println("</ul>");
    } else {
        out.println("<p>Aucun prélèvement trouvé pour cette commune.</p>");
    }
%>
