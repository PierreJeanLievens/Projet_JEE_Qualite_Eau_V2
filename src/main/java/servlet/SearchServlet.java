package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.CommuneDAO;


public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomCommune = request.getParameter("nomCommune");
        CommuneDAO communeDao = new CommuneDAO();
        ArrayList<String> listCommunes = new ArrayList<>();
        try {
			listCommunes = communeDao.getAllCommunesNames(nomCommune);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(String commune : listCommunes) {
        	System.out.println("Commune : "+commune);
        }
        System.out.println("Nom de la commune dans la servlet : " + nomCommune); // Sortie de débogage
        request.setAttribute("nomCommune", nomCommune);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}


