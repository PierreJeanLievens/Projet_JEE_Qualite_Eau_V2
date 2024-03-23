package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Prelevement;

public class PrelevementDAO {
    
    private Connection connection;
    
    public PrelevementDAO() {
    }
    
    public PrelevementDAO(Connection connection) {
        this.connection = connection;
    }
    
    private void dbConnect() throws SQLException {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	
        String url = "jdbc:mysql://localhost:3306/qualite_eau";
        String user = "root";
        String password = "root";
        connection = DriverManager.getConnection(url, user, password);
    }

    private void dbClose() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    
    public ArrayList<Prelevement> getAllResultats(String insee_commune, String cd_reseau) throws SQLException {
        ArrayList<Prelevement> prelevements = new ArrayList<>();
        ResultatDAO resultatDao = new ResultatDAO(this.connection);
        String query = "SELECT * FROM prelevement WHERE insee_commune=? AND cd_reseau=?;";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, insee_commune);
            statement.setString(2, cd_reseau);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Prelevement prelevement = new Prelevement(
                    resultSet.getString("insee_commune"),
                    resultSet.getString("reference_prelevement"),
                    resultSet.getString("cd_dept"),
                    resultSet.getString("cd_reseau"),
                    resultSet.getDate("date"),
                    resultSet.getString("conclusion"),
                    resultatDao.getAllResultats(resultSet.getString("reference_prelevement"))
                );
                prelevements.add(prelevement);
            }
        } catch (SQLException e) {
        	throw new RuntimeException("uncaught", e) ;
        } 
        
        return prelevements;
    }

    
    public ArrayList<Prelevement> getPrelevements(String nom_commune) throws SQLException {
    	
    	dbConnect();
        ArrayList<Prelevement> prelevements = new ArrayList<>();
        String query = "SELECT DISTINCT prelevement.*\r\n"
        		+ "FROM prelevement\r\n"
        		+ "INNER JOIN commune ON prelevement.insee_commune = commune.insee_commune\r\n"
        		+ "WHERE commune.nom_commune = ?;";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, nom_commune);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Prelevement prelevement = new Prelevement(
                	resultSet.getString("insee_commune"),
                    resultSet.getString("reference_prelevement"),
                    resultSet.getString("cd_dept"),
                    resultSet.getString("cd_reseau"),
                    resultSet.getDate("date"),
                    resultSet.getString("conclusion")
                );
                prelevements.add(prelevement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbClose();
        
        return prelevements;
        
    }
    
    // Vous pouvez ajouter d'autres méthodes (addResultat, updateResultat, deleteResultat) ici
}
