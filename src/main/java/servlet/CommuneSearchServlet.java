package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.Cookie;
/**
 * Servlet implementation class CommuneSearchServlet
 */
public class CommuneSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommuneSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupérer la valeur du champ nomCommune du formulaire
        String nomCommune = request.getParameter("nomCommune");
        
        // Créer un cookie avec la valeur de nomCommune
        Cookie cookie = new Cookie("NameCommune", nomCommune);
        
        // Définir la durée de vie du cookie (en secondes)
        cookie.setMaxAge(3600); // Par exemple, 1 heure
        
        // Ajouter le cookie à la réponse
        response.addCookie(cookie);
        
        // Rediriger vers la page index.jsp
        response.sendRedirect("index.jsp");
    }

}
