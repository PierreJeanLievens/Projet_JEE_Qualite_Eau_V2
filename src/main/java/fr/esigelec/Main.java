package fr.esigelec;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Commune;
import Model.Prelevement;
import Model.Resultat;
import dao.ResultatDAO;
import dao.PrelevementDAO;
import dao.CommuneDAO;
public class Main {

	public static void main(String[] args) throws SQLException {
		long startTime = System.currentTimeMillis();
		PrelevementDAO prelevementDao = new PrelevementDAO();
		ArrayList<Resultat> resultats;
		ArrayList<Prelevement> prelevements;
		CommuneDAO communeDao = new CommuneDAO();
		ArrayList<Commune> communes = communeDao.getAllCommunesByName("LA ROCHELLE");
		for (Commune commune : communes) {
		    System.out.println("\nNom : " + commune.getNom_commune());
		    prelevements = commune.getListPrelevements();
		    for (Prelevement prelevement : prelevements) {
		        System.out.println("\nPrelevement : ");
		        resultats = prelevement.getListResultats();
		    }
		}
		
		/*ArrayList<String> listCommunes = communeDao.getAllCommunesNames("Berville");
		for(String commune : listCommunes) {
			System.out.println(commune);
		}*/

		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("Temps d'exécution de la requête : " + executionTime + " millisecondes");

		
	}

}
