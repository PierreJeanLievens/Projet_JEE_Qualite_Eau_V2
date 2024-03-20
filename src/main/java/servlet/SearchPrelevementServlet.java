package servlet;

import dao.PrelevementDAO;
import Model.Prelevement;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class SearchPrelevementServlet
 */
public class SearchPrelevementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPrelevementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  String selectedCommune = request.getParameter("selectedCommune");
    	  PrelevementDAO prelevementDao = new PrelevementDAO();
    	  ArrayList<Prelevement> prelevements = new ArrayList<>();
    	  try {
    	    prelevements = prelevementDao.getPrelevements(selectedCommune);
    	  } catch (SQLException e) {
    	    e.printStackTrace();
    	  }

    	  PrintWriter out = response.getWriter();
    	  int index = 0;
    	  for (Prelevement prelevement : prelevements) {
    	    out.println("<div class='accordion-item'>");
    	    out.println("<h2 class='accordion-header' id='heading" + index + "'>");
    	    out.println("<button class='accordion-button' type='button' data-bs-toggle='collapse' data-bs-target='#collapse" + index + "' aria-expanded='true' aria-controls='collapse" + index + "'>");
    	    out.println("Ville : " + selectedCommune + " Prelevement " + prelevement.getReference_prelevement()+ " Date : " + prelevement.getDate());
    	    out.println("</button></h2>");
    	    out.println("<div id='collapse" + index + "' class='accordion-collapse collapse " + (index == 0 ? "show" : "") + "' aria-labelledby='heading" + index + "' data-bs-parent='#prelevementAccordion'>");
    	    out.println("<div class='accordion-body'>");
    	    // Ajoutez ici plus d'informations sur le prélèvement
    	    out.println("</div></div></div>");
    	    index++;
    	  }
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
