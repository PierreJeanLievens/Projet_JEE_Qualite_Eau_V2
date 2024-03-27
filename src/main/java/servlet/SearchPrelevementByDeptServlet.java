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
public class SearchPrelevementByDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPrelevementByDeptServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the selected departement code
		String selectedDept = request.getParameter("selectedDept");
		// create DAO and list of Commune
		PrelevementDAO prelevementDao = new PrelevementDAO();
		ArrayList<Prelevement> prelevements = new ArrayList<>();

		// get the list of Commune corresponding to the selected commune
		try {
			prelevements = prelevementDao.getPrelevementsByDept(selectedDept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		int index = 0;
		out.println("<h4>Recherche effectuée pour le departement : " + StringEscapeUtils.escapeHtml4(selectedDept) +"</h4>");
		boolean displayNoData = true;
		boolean conformBact;
		boolean conformChim;
		// for each COmmune
		
		    for (Prelevement prelevement : prelevements) {
		    	displayNoData= false;
		    	conformBact = true;
				conformChim = true;
		        // Échapper les données sensibles
		        String escapedCommune = StringEscapeUtils.escapeHtml4(prelevement.getNom_commune());
		        String escapedInseeCommune = StringEscapeUtils.escapeHtml4(prelevement.getInsee_commune());
		        String escapedReseau = StringEscapeUtils.escapeHtml4(prelevement.getCd_reseau());
		        String escapedConclusion = StringEscapeUtils.escapeHtml4(prelevement.getConclusion());
		        String escapedConformBact = "Sans valeur";
		        String escapedConformChim = "Sans valeur";
		        
		        // Échapper les valeurs de conformité
		        if ("C".equals(prelevement.getConform_bact())) {
		            escapedConformBact = "Conforme";
		        } else if ("N".equals(prelevement.getConform_bact())) {
		            escapedConformBact = "Non conforme";
		            conformBact = false;
		        }
		        
		        if ("C".equals(prelevement.getConform_chim())) {
		            escapedConformChim = "Conforme";
		        } else if ("N".equals(prelevement.getConform_chim())) {
		            escapedConformChim = "Non conforme";
		            conformChim = false;	
		        }
		        
		        // Afficher les données échappées dans la réponse HTML
		        out.println("<div class='accordion-item'>");
		        out.println("<h2 class='accordion-header' id='heading" + index + "'>");
		        out.println("<button class='accordion-button' type='button' data-bs-toggle='collapse' data-bs-target='#collapse" + index + "' aria-expanded='true' aria-controls='collapse" + index + "'>");
		        out.println("<strong>Ville :</strong> " + escapedCommune + ", <strong>Date de prélèvement :</strong> " + prelevement.getDate());
		        // Display a red message for catch the user
		        if (!conformBact || !conformChim) {
		            out.print("<strong style='color: red;'> ------- Relevé non conforme</strong> ");
		        }
		        out.println("</button></h2>");
		        out.println("<div id='collapse" + index + "' class='accordion-collapse collapse " + (index == 0 ? "show" : "") + "' aria-labelledby='heading" + index + "' data-bs-parent='#prelevementAccordion'>");
		        out.println("<div class='accordion-body'>");
		        out.println("<div style='display: flex;'>");
		        out.println("<!-- Colonne 1 -->");
		        out.println("<div style='flex: 1; padding-right: 10px;'>");
		        out.println("<strong>Code INSEE :</strong> " + escapedInseeCommune + "<br>");
		        out.println("<strong>Code Réseau :</strong> " + escapedReseau + "<br>");
		        out.println("<strong>Conformité bactériologique :</strong> " + escapedConformBact);
		        out.println("</div>");
		        out.println("<!-- Colonne 2 -->");
		        out.println("<div style='flex: 1; padding-left: 10px;'>");
		        out.println("<strong>Nom de la commune :</strong> " + escapedCommune + "<br><br>");
		        out.println("<strong>Conformité chimique :</strong> " + escapedConformChim);
		        out.println("</div>");
		        out.println("</div>");
		        out.println("<br><strong>Détail de l'eau :</strong> " + escapedConclusion + "<br>");
		        out.println("</div>");
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
