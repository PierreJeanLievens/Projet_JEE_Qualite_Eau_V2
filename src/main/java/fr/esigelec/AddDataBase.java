package fr.esigelec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


// Il faut importer le fichier jar commons-csv-1.10.0.jar

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
/**
 * Class to add all data from CSV to SQL files
 * @author PIERRE-JEAN
 *
 */
public class AddDataBase {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\PIERRE-JEAN\\Downloads\\dis-2024-dept";
        String fileSqlCommune =  "C:\\Users\\PIERRE-JEAN\\Downloads\\dis-2024-dept\\importCommunes.sql";
        String filePrelevement =  "C:\\Users\\PIERRE-JEAN\\Downloads\\dis-2024-dept\\importPrelevements.sql";
        String fileSqlResultat =  "C:\\Users\\PIERRE-JEAN\\Downloads\\dis-2024-dept\\importResultats.sql";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith("DIS_COM")) {
                    AddCommunes(file.getAbsolutePath(), fileSqlCommune);
                } else if (file.isFile() && file.getName().startsWith("DIS_PLV")) {
                    //AddPrelevements(file.getAbsolutePath(), filePrelevement);
                } else if (file.isFile() && file.getName().startsWith("DIS_RESULT")) {
                    //AddResultats(file.getAbsolutePath(), fileSqlResultat);
                }
            }
        }
    }
    /**
     * Method to read a commune file and get the selected data
     * @param fileInput
     * @param fileOutput
     */
    public static void AddCommunes(String fileInput, String fileOutput) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput, true));
             Reader reader = new FileReader(fileInput);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord record : csvParser) {
                if (record.size() == 0) {
                    continue; // Ignore les lignes vides
                }

                String insee_commune = record.get(0).replace("\"", "").trim();
                
                String nom_commune = record.get(1).replace("\"", "").trim();
                String contenuEntreParentheses = extractContentBetweenParentheses(nom_commune);
                nom_commune = nom_commune.replaceAll("\\(.*?\\)", "").trim();
                nom_commune = contenuEntreParentheses + nom_commune; 
                nom_commune = nom_commune.replace("'", "''").trim();
                
                String quartier = record.get(2).replace("\"", "").replace("'", "''").trim();
                String cd_reseau = record.get(3).replace("\"", "").trim();
                String nom_reseau = record.get(4).replace("\"", "").replace("'", "''").trim();
                String debut_alim = record.get(5).replace("\"", "").trim();
                
                String sql = String.format("INSERT INTO commune (insee_commune, nom_commune, quartier, cd_reseau, nom_reseau, debut_alim) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');\n", insee_commune, nom_commune, quartier, cd_reseau, nom_reseau, debut_alim);
                writer.write(sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to read a prelevement file and get the selected data
     * @param fileInput
     * @param fileOutput
     */
    public static void AddPrelevements(String fileInput, String fileOutput) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput, true));
             Reader reader = new FileReader(fileInput);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord record : csvParser) {
                if (record.size() == 0) {
                    continue; // Ignore les lignes vides
                }

                String cd_dept = record.get(0).replace("\"", "").trim();
                String cd_reseau = record.get(1).replace("\"", "").trim();
                String insee_commune_princ = record.get(2).replace("\"", "").trim();
                String reference_prel = record.get(7).replace("\"", "").trim();
                String date_prel = record.get(8).replace("\"", "").trim();

                String heure_prel_original = record.get(9).replace("\"", "").trim();
                String[] heureMinutes = heure_prel_original.split("h");
                String heure = "00";
                String minutes = "00";
                if (heureMinutes.length > 0 && !heureMinutes[0].isEmpty()) {
                    heure = heureMinutes[0];
                }
                if (heureMinutes.length > 1 && !heureMinutes[1].isEmpty()) {
                    minutes = heureMinutes[1];
                }
                String heure_prel_formatted = String.format("%02d:%02d", Integer.parseInt(heure), Integer.parseInt(minutes));

                String conclusion_prel = record.get(10).replace("\"", "").replace("'", "''").trim();

                String sql = String.format("INSERT INTO prelevement (cd_dept, cd_reseau, insee_commune, reference_prelevement, date, heure, conclusion) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');\n", cd_dept, cd_reseau, insee_commune_princ, reference_prel, date_prel, heure_prel_formatted, conclusion_prel);
                writer.write(sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to read a resultat file and get the selected data
     * @param fileInput
     * @param fileOutput
     */
    public static void AddResultats(String fileInput, String fileOutput) {
        boolean notFirstLineRequest = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput, true));
             Reader reader = new FileReader(fileInput);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord record : csvParser) {
                if (record.size() == 0) {
                    continue; // Ignore les lignes vides
                }

                String reference_prelevement = record.get(1).replace("\"", "").trim();
                String cd_cise = record.get(2).replace("\"", "").trim();
                String cd_parametre = record.get(3).replace("\"", "").trim();
                String parametre = record.get(4).replace("\"", "").replace("'", "''").trim();
                String qual_parametre = record.get(7).replace("\"", "").trim();
                int qual_parametre_bool = "N".equals(qual_parametre) ? 0 : "O".equals(qual_parametre) ? 1 : 0;
                String valeur_string = record.get(9).replace("\"", "").replace("'", "''").replace(",", ".").trim();
                String unite = record.get(10).replace("\"", "").replace("'", "''").trim();
                String limite_qual = record.get(12).replace("\"", "").replace("'", "''").trim();
                String valeur_nombre = record.get(14).replace("\"", "").trim();
                if (valeur_nombre.isEmpty()) {
                    valeur_nombre = "0.000000";
                }

                if (notFirstLineRequest) {
                    writer.write(",");
                } else {
                    writer.write("INSERT INTO resultat (reference_prelevement, cd_sise, cd_parametre, parametre, qual_parametre, valeur_string, unite, limite_qual, valeur_nombre) VALUES ");
                    notFirstLineRequest = true;
                }

                writer.write(String.format("('%s', '%s', '%s', '%s', %d, '%s', '%s', '%s', %s)", reference_prelevement, cd_cise, cd_parametre, parametre, qual_parametre_bool, valeur_string, unite, limite_qual, valeur_nombre));

            }
            if (notFirstLineRequest) {
                writer.write(";\n"); // Fin de la commande SQL
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to extract content between '()' char
     * @param input
     * @return
     */
    private static String extractContentBetweenParentheses(String input) {
        int startIndex = input.indexOf("(");
        int endIndex = input.indexOf(")");
        if (startIndex != -1 && endIndex != -1) {
            String contenuEntreParentheses = input.substring(startIndex + 1, endIndex).trim();
            if (!contenuEntreParentheses.endsWith("'")) {
                contenuEntreParentheses += " "; // Ajouter un espace à la fin si le contenu ne se termine pas par le caractère '
            }
            return contenuEntreParentheses;
        }
        return ""; // Retourner une chaîne vide si aucune parenthèse n'est trouvée
    }

}
