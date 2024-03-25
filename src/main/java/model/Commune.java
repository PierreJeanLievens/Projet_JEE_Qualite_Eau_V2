package model;

import java.sql.Date;
import java.util.ArrayList;
/**
 * 
 * @author PIERRE-JEAN
 * Class Commune
 */
public class Commune {

	private int id;
	private String insee_commune;
	private String nom_commune;
	private String quartier;
	private String cd_reseau;
	private String nom_reseau;
	private Date debut_alim;
	private ArrayList<Prelevement> ListPrelevements;

	 /**
     * Constructor for the Commune class.
     *
     * @param insee_commune   The INSEE code of the commune.
     * @param nom_commune     The name of the commune.
     * @param quartier        The district of the commune.
     * @param cd_reseau       The code of the commune's network.
     * @param nom_reseau      The name of the commune's network.
     * @param debut_alim      The date of the commencement of water supply to the commune.
     * @param ListPrelevements    The list of samplings associated with the commune.
     */
	public Commune(String insee_commune, String nom_commune, String quartier, String cd_reseau, String nom_reseau, Date debut_alim, ArrayList<Prelevement> ListPrelevements) {
		this.insee_commune = insee_commune;
		this.nom_commune = nom_commune;
		this.quartier = quartier;
		this.cd_reseau = cd_reseau;
		this.nom_reseau = nom_reseau;
		this.debut_alim = debut_alim;
		this.ListPrelevements = ListPrelevements;
	}

	public ArrayList<Prelevement> getListPrelevements() {
		return ListPrelevements;
	}

	public void setListPrelevements(ArrayList<Prelevement> listePrelevements) {
		ListPrelevements = listePrelevements;
	}

	public String getInsee_commune() {
		return insee_commune;
	}

	public void setInsee_commune(String insee_commune) {
		this.insee_commune = insee_commune;
	}

	public String getNom_commune() {
		return nom_commune;
	}

	public void setNom_commune(String nom_commune) {
		this.nom_commune = nom_commune;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getCd_reseau() {
		return cd_reseau;
	}

	public void setCd_reseau(String cd_reseau) {
		this.cd_reseau = cd_reseau;
	}

	public String getNom_reseau() {
		return nom_reseau;
	}

	public void setNom_reseau(String nom_reseau) {
		this.nom_reseau = nom_reseau;
	}

	public Date getDebut_alim() {
		return debut_alim;
	}

	public void setDebut_alim(Date debut_alim) {
		this.debut_alim = debut_alim;
	}


}
