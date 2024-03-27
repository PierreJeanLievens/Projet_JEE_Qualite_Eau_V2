package fr.esigelec;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ResultatDAO;
import model.Commune;
import model.Prelevement;
import model.Resultat;
import dao.PrelevementDAO;
import dao.CommuneDAO;
public class Main {

	public static void main(String[] args) throws SQLException {
		long startTime = System.currentTimeMillis();
		PrelevementDAO prelevementDao = new PrelevementDAO();
		/*
		ArrayList<Resultat> resultats;
		ArrayList<Prelevement> prelevements;
		CommuneDAO communeDao = new CommuneDAO();
		ArrayList<Commune> communes = communeDao.getAllCommunesByDept("085");
		for (Commune commune : communes) {
		    System.out.println("\nNom : " + commune.getNom_commune());
		    prelevements = commune.getListPrelevements();
		    for (Prelevement prelevement : prelevements) {
		        System.out.println("\nPrelevement : ");
		        resultats = prelevement.getListResultats();
		    }
		}/*
		
		/*ArrayList<String> listCommunes = communeDao.getAllCommunesNames("Berville");
		for(String commune : listCommunes) {
			System.out.println(commune);
		}*/
		ArrayList<Resultat> resultats;
		ArrayList<Prelevement> prelevements;
		prelevements = prelevementDao.getPrelevementsByDept("085");
		
			for (Prelevement prelevement : prelevements) {
				
					System.out.println("\nPrelevement : ");
			
			
		}
	       
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println("Temps d'exécution de la requête : " + executionTime + " millisecondes");

		
	}

}
