package model;
/**
 *  Class Resultat
 * @author PIERRE-JEAN
 *
 */
public class Resultat {

	private int id;
	private String reference_prelevement;
	private String cd_sise;
	private String cd_parametre;
	private String parametre;
	private Boolean qual_parametre;
	private String valeur_string;
	private String unite;
	private String limite_qual;
	private Double valeur_nombre;

	/**
	 * CConstructor for the Resultat class
	 * 
	 * @param reference_prelevement
	 * @param cd_sise
	 * @param cd_parametre
	 * @param parametre
	 * @param qual_parametre
	 * @param valeur_string
	 * @param unite
	 * @param limite_qual
	 * @param valeur_nombre
	 */
	public Resultat(String reference_prelevement, String cd_sise, String cd_parametre, String parametre, Boolean qual_parametre, String valeur_string, String unite, String limite_qual, Double valeur_nombre ) {


		this.reference_prelevement = reference_prelevement;
		this.cd_sise = cd_sise;
		this.cd_parametre = cd_parametre;
		this.parametre = parametre;
		this.qual_parametre = qual_parametre;
		this.valeur_string = valeur_string;
		this.unite = unite;
		this.limite_qual = limite_qual;
		this.valeur_nombre = valeur_nombre;

	}

	public String getReference_prelevement() {
		return reference_prelevement;
	}

	public void setReference_prelevement(String reference_prelevement) {
		this.reference_prelevement = reference_prelevement;
	}

	public String getCd_sise() {
		return cd_sise;
	}

	public void setCd_sise(String cd_sise) {
		this.cd_sise = cd_sise;
	}

	public String getCd_parametre() {
		return cd_parametre;
	}

	public void setCd_parametre(String cd_parametre) {
		this.cd_parametre = cd_parametre;
	}

	public String getParametre() {
		return parametre;
	}

	public void setParametre(String parametre) {
		this.parametre = parametre;
	}

	public Boolean getQual_parametre() {
		return qual_parametre;
	}

	public void setQual_parametre(Boolean qual_parametre) {
		this.qual_parametre = qual_parametre;
	}

	public String getValeur_string() {
		return valeur_string;
	}

	public void setValeur_string(String valeur_string) {
		this.valeur_string = valeur_string;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getLimite_qual() {
		return limite_qual;
	}

	public void setLimite_qual(String limite_qual) {
		this.limite_qual = limite_qual;
	}

	public Double getValeur_nombre() {
		return valeur_nombre;
	}

	public void setValeur_nombre(Double valeur_nombre) {
		this.valeur_nombre = valeur_nombre;
	}
}
