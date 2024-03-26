package servlet;

import dao.PrelevementDAO;
import dao.CommuneDAO;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Commune;
import model.Prelevement;
import model.Resultat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author PIERRE-JEAN
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
		// get the selected commune full name
		String selectedCommune = request.getParameter("selectedCommune");
		// create DAO and list of Commune
		CommuneDAO communeDAO = new CommuneDAO();
		ArrayList<Commune> communes = new ArrayList<>();
		String val_Teau;
		String unite_Teau;
		String val_PH;
		String unite_PH;
		// get the list of Commune corresponding to the selected commune
		try {
			communes = communeDAO.getAllCommunesByName(selectedCommune);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		int index = 0;
		out.println("<h4>Recherche effectuée : " + StringEscapeUtils.escapeHtml4(selectedCommune) +"</h4>");
		boolean displayNoData = true;
		// for each COmmune
		for (Commune commune : communes) {
			// for each Prelevement n the current commune
			for (Prelevement prelevement : commune.getListPrelevements()) {
				// to don't display the NoData h5
				displayNoData = false;
				// reset the value
				val_Teau = "Pas de valeur enregistrée";
				unite_Teau = "";
				val_PH = "Pas de valeur enregistrée";
				unite_PH = "";
				// for each Resultat in the current Prelevement
				for (Resultat resultat : prelevement.getListResultats()) {
					// get the water temperature value and unit
					if ("TEAU".equals(resultat.getCd_sise())) {
						val_Teau = resultat.getValeur_string();
						unite_Teau = resultat.getUnite();
					}
					// get the PH value and unit
					if ("PH".equals(resultat.getCd_sise())) {
						val_PH = resultat.getValeur_string();
						unite_PH = resultat.getUnite();
					}
				}
				// display one item of the accordion 
				out.println("<div class='accordion-item'>");
				// display the title 
				out.println("<h2 class='accordion-header' id='heading" + index + "'>");
				out.println("<button class='accordion-button' type='button' data-bs-toggle='collapse' data-bs-target='#collapse" + index + "' aria-expanded='true' aria-controls='collapse" + index + "'>");
				out.println("<strong>Ville :</strong> " +  StringEscapeUtils.escapeHtml4(selectedCommune) + ", <strong>Date de prelevement :</strong> " + prelevement.getDate());
				out.println("</button></h2>");
				out.println("<div id='collapse" + index + "' class='accordion-collapse collapse " + (index == 0 ? "show" : "") + "' aria-labelledby='heading" + index + "' data-bs-parent='#prelevementAccordion'>");
				// display the body with the selected value
				out.println("<div class='accordion-body'>");
				out.println("  <div style='display: flex;'>");
				out.println("    <!-- Colonne 1 -->");
				out.println("    <div style='flex: 1; padding-right: 10px;'>");
				out.println("      <strong>Code INSEE :</strong> " + StringEscapeUtils.escapeHtml4(prelevement.getInsee_commune())  + "<br>");
				out.println("      <strong>Code Réseau :</strong> " + StringEscapeUtils.escapeHtml4(prelevement.getCd_reseau())  + "<br>");
				out.println("      <strong>Nom du quartier :</strong> " + StringEscapeUtils.escapeHtml4(commune.getQuartier())  + "<br><br>");
				out.println("  	   <strong>Température de l'eau :</strong> " + StringEscapeUtils.escapeHtml4(val_Teau)  + " "+ StringEscapeUtils.escapeHtml4(unite_Teau)  +"<br>");
				out.println("    </div>");
				out.println("    ");
				out.println("    <!-- Colonne 2 -->");
				out.println("    <div style='flex: 1; padding-left: 10px;'>");
				out.println("      <strong>Nom de la commune :</strong> " + StringEscapeUtils.escapeHtml4(commune.getNom_commune())  + "<br>");
				out.println("      <strong>Nom du réseau :</strong> " + StringEscapeUtils.escapeHtml4(commune.getNom_reseau()) + "<br>");
				out.println("      <strong>Date de début d'alimentation :</strong> " + commune.getDebut_alim()  + "<br><br>");
				out.println("  	   <strong>Valeur du PH :</strong> " + StringEscapeUtils.escapeHtml4(val_PH) + " "+ StringEscapeUtils.escapeHtml4(unite_PH) + "<br>");
				out.println("    </div>");
				out.println("  </div>");
				out.println("  <!-- Elements en dessous des deux colonnes -->");

				out.println("  <br><strong>Détail de l'eau :</strong> " + StringEscapeUtils.escapeHtml4(prelevement.getConclusion()) + "<br>");
				out.println("</div>");
				out.println("</div></div></div>");
				index++;
			}
		}
		// If no data displayed, display this message
		if(displayNoData) {
			out.println("<h5>Pas de prélèvements pour cette commune<h5>");
			displayNoData = false;
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
