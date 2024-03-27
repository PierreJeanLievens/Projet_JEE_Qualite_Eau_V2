package model;

import java.sql.Date;
import java.util.ArrayList;
/**
 *  Class Prelevement
 * @author PIERRE-JEAN
 *
 */
public class Prelevement {

	private int id;
	private String insee_commune;
	private String reference_prelevement;
	private String cd_dept;
	private String cd_reseau;
	private Date date;
	private String conclusion;
	private String conform_bact;
	private String conform_chim;
	private ArrayList<Resultat> ListResultats;

	/**
	 * Constructor for the Prelevement class
	 * 
	 * @param insee_commune
	 * @param reference_prelevement
	 * @param cd_dept
	 * @param cd_reseau
	 * @param date
	 * @param conclusion
	 * @param conform_bact
	 * @param conform_chim
	 * @param ListResultat
	 */
	public Prelevement(String insee_commune, String reference_prelevement, String cd_dept, String cd_reseau, Date date, String conclusion, String conform_bact, String conform_chim, ArrayList<Resultat> ListResultat) {
		this.insee_commune = insee_commune;
		this.reference_prelevement = reference_prelevement;
		this.cd_dept = cd_dept;
		this.cd_reseau = cd_reseau;
		this.date = date;
		this.conclusion = conclusion;
		this.conform_bact = conform_bact;
		this.conform_chim = conform_chim;
		this.ListResultats = ListResultat;
	}

	/**
	 * Constructor for the Prelevement class without the list
	 * 
	 * @param insee_commune
	 * @param reference_prelevement
	 * @param cd_dept
	 * @param cd_reseau
	 * @param date
	 * @param conclusion
	 * @param conform_bact
	 * @param conform_chim
	 */
	public Prelevement(String insee_commune, String reference_prelevement, String cd_dept, String cd_reseau, Date date, String conclusion, String conform_bact, String conform_chim) {
		this.insee_commune = insee_commune;
		this.reference_prelevement = reference_prelevement;
		this.cd_dept = cd_dept;
		this.cd_reseau = cd_reseau;
		this.date = date;
		this.conclusion = conclusion;
		this.conform_bact = conform_bact;
		this.conform_chim = conform_chim;
		
	}

	public String getInsee_commune() {
		return insee_commune;
	}

	public void setInsee_commune(String insee_commune) {
		this.insee_commune = insee_commune;
	}

	public String getReference_prelevement() {
		return reference_prelevement;
	}

	public void setReference_prelevement(String reference_prelevement) {
		this.reference_prelevement = reference_prelevement;
	}

	public String getCd_dept() {
		return cd_dept;
	}

	public void setCd_dept(String cd_dept) {
		this.cd_dept = cd_dept;
	}

	public String getCd_reseau() {
		return cd_reseau;
	}

	public void setCd_reseau(String cd_reseau) {
		this.cd_reseau = cd_reseau;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getConform_bact() {
		return conform_bact;
	}

	public void setConform_bact(String conform_bact) {
		this.conform_bact = conform_bact;
	}

	public String getConform_chim() {
		return conform_chim;
	}

	public void setConform_chim(String conform_chim) {
		this.conform_chim = conform_chim;
	}

	
	public ArrayList<Resultat> getListResultats() {
		return ListResultats;
	}

	public void setListResultats(ArrayList<Resultat> listeResultats) {
		ListResultats = listeResultats;
	}
}
