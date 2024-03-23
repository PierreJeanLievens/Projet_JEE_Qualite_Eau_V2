package servlet;

import dao.PrelevementDAO;
import dao.CommuneDAO;
import Model.Prelevement;
import Model.Resultat;
import Model.Commune;
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
    	  /*String selectedCommune = request.getParameter("selectedCommune");
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
    	    out.println("");
    	    out.println("</div></div></div>");
    	    index++;
    	  }
    	*/
    	String selectedCommune = request.getParameter("selectedCommune");
    	CommuneDAO communeDAO = new CommuneDAO();
    	ArrayList<Commune> communes = new ArrayList<>();
    	String val_Teau;
    	String val_PH;
    	try {
    		communes = communeDAO.getAllCommunesByName(selectedCommune);
    	  } catch (SQLException e) {
    	    e.printStackTrace();
    	  }
    	PrintWriter out = response.getWriter();
  	  int index = 0;
  	  for (Commune commune : communes) {
  	    for (Prelevement prelevement : commune.getListPrelevements()) {
  	        val_Teau = "Pas de valeur";
  	        val_PH = "Pas de valeur";
  	        for (Resultat resultat : prelevement.getListResultats()) {
  	            if ("TEAU".equals(resultat.getCd_sise())) {
  	                val_Teau = resultat.getValeur_string();
  	            }
  	            if ("PH".equals(resultat.getCd_sise())) {
  	                val_PH = resultat.getValeur_string();
  	            }
  	        }
  	        out.println("<div class='accordion-item'>");
  	        out.println("<h2 class='accordion-header' id='heading" + index + "'>");
  	        out.println("<button class='accordion-button' type='button' data-bs-toggle='collapse' data-bs-target='#collapse" + index + "' aria-expanded='true' aria-controls='collapse" + index + "'>");
  	        out.println("<strong>Ville :</strong> " + selectedCommune + ", <strong>Date de prelevement :</strong> " + prelevement.getDate());
  	        out.println("</button></h2>");
  	        out.println("<div id='collapse" + index + "' class='accordion-collapse collapse " + (index == 0 ? "show" : "") + "' aria-labelledby='heading" + index + "' data-bs-parent='#prelevementAccordion'>");
  	        out.println("<div class='accordion-body'>");
  	        out.println("<strong>Code INSEE :</strong> " + prelevement.getInsee_commune() + "<br><strong>Code Réseau :</strong> " + prelevement.getCd_reseau() + "<br><strong>Nom de la commune :</strong> " + commune.getNom_commune() + "<br><strong>Nom du quartier :</strong> " + commune.getQuartier() + "<br><strong>Code ID du réseau :</strong> " + commune.getCd_reseau() + "<br><strong>Nom du réseau :</strong> " + commune.getNom_reseau() + "<br><strong>Date de début d'alimentation :</strong> " + commune.getDebut_alim() + "<br><strong>Détail de l'eau :</strong> " + prelevement.getConclusion() + "<br><strong>Température de l'eau :</strong> " + val_Teau + "<br><strong>Valeur du PH :</strong> " + val_PH);
  	        out.println("</div></div></div>");
  	        index++;
  	    }
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
