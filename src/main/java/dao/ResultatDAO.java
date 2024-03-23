package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Resultat;

public class ResultatDAO {
    
	 private Connection connection;

	    public ResultatDAO(Connection connection) {
	        this.connection = connection;
	    }
 
    public ArrayList<Resultat> getAllResultats(String reference_prelevement) throws SQLException {
        ArrayList<Resultat> resultats = new ArrayList<>();
        String query = "SELECT *\r\n"
        		+ "FROM resultat\r\n"
        		+ "WHERE reference_prelevement = ? AND (cd_sise = 'TEAU' OR cd_sise = 'PH');\r\n"
        		+ "";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reference_prelevement);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Resultat resultat = new Resultat(
                    resultSet.getString("reference_prelevement"),
                    resultSet.getString("cd_sise"),
                    resultSet.getString("cd_parametre"),
                    resultSet.getString("parametre"),
                    resultSet.getBoolean("qual_parametre"),
                    resultSet.getString("valeur_string"),
                    resultSet.getString("unite"),
                    resultSet.getString("limite_qual"),
                    resultSet.getDouble("valeur_nombre")
                );
                resultats.add(resultat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultats;
    }

    
    // Vous pouvez ajouter d'autres méthodes (addResultat, updateResultat, deleteResultat) ici
}
