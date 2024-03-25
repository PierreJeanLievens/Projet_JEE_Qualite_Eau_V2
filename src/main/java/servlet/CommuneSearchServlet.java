package servlet;

import java.util.ArrayList;
import dao.CommuneDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.http.Cookie;
/**
 * @author PIERRE-JEAN
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
		  response.setContentType("text/html;charset=UTF-8");
		  // get the value of the input 
		  String nomCommune = request.getParameter("nomCommune");
		  // create CommuneDAO and list of String
		  CommuneDAO communeDao = new CommuneDAO();
		  ArrayList<String> listCommunes = new ArrayList<>();
		  // get the list of all commune names
		  try {
		    listCommunes = communeDao.getAllCommunesNames(nomCommune);
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  PrintWriter out = response.getWriter();
		  // add a select
		  out.println("<select id='communeDropdown' class='form-select' onchange='selectCommune()'>");
		  // this is the default choice
		  out.println("<option selected disabled>Choisissez votre commune</option>");
		  // for each commune name, add an option
		  for (String commune : listCommunes) {
		      out.println("<option value='" + commune + "'>" + commune + "</option>");
		  }
		  out.println("</select>");

		}




}
