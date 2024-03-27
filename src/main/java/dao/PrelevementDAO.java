package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Prelevement;
/**
 * Class PrelevementDAO
 * @author PIERRE-JEAN
 *
 */
public class PrelevementDAO {
    
    private Connection connection;
    
    public PrelevementDAO() {
    }
    /**
     * Constructor with a connection
     * @param connection
     */
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
    /**
     * Method to get a list of Prelevement linked whit the insee_commune and cd_reseau
     * @param insee_commune
     * @param cd_reseau
     * @return a list of Prelevement
     * @throws SQLException
     */
    public ArrayList<Prelevement> getAllResultats(String insee_commune, String cd_reseau) throws SQLException {
    	// create an empty list
        ArrayList<Prelevement> prelevements = new ArrayList<>();
        // create resultatDAO with the current connection
        ResultatDAO resultatDao = new ResultatDAO(this.connection);
        // prepare the query to the DB
        String query = "SELECT *\r\n"
        		+ "FROM prelevement\r\n"
        		+ "WHERE insee_commune = ? AND cd_reseau = ?\r\n"
        		+ "ORDER BY date DESC, heure DESC;";
        // to prevent SQL injection
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, insee_commune);
            statement.setString(2, cd_reseau);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	// create a Prelevement for the current result
                Prelevement prelevement = new Prelevement(
                    resultSet.getString("insee_commune"),
                    resultSet.getString("reference_prelevement"),
                    resultSet.getString("cd_dept"),
                    resultSet.getString("cd_reseau"),
                    resultSet.getDate("date"),
                    resultSet.getString("conclusion"),
                    resultSet.getString("conform_bact"),
                    resultSet.getString("conform_chim"),
                    resultatDao.getAllResultats(resultSet.getString("reference_prelevement"))
                );
                // add the current Prelevement to the list
                prelevements.add(prelevement);
            }
        } catch (SQLException e) {
        	throw new RuntimeException("uncaught", e) ;
        } 
        // return the list of Prelevement
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
                    resultSet.getString("conclusion"),
                    resultSet.getString("conform_bact"),
                    resultSet.getString("conform_chim")
                );
                prelevements.add(prelevement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbClose();
        
        return prelevements;
        
    }
    
  public ArrayList<Prelevement> getPrelevementsByDept(String code_dept) throws SQLException {
    	
    	dbConnect();
    	ResultatDAO resultatDao = new ResultatDAO(this.connection);
        ArrayList<Prelevement> prelevements = new ArrayList<>();
        String query = "SELECT DISTINCT prelevement.reference_prelevement, prelevement.*, commune.nom_commune \r\n"
        		+ "FROM prelevement \r\n"
        		+ "INNER JOIN commune ON commune.insee_commune = prelevement.insee_commune AND commune.cd_reseau = prelevement.cd_reseau\r\n"
        		+ "WHERE prelevement.cd_dept = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, code_dept);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Prelevement prelevement = new Prelevement(
                	resultSet.getString("nom_commune"),
                	resultSet.getString("insee_commune"),
                    resultSet.getString("reference_prelevement"),
                    resultSet.getString("cd_dept"),
                    resultSet.getString("cd_reseau"),
                    resultSet.getDate("date"),
                    resultSet.getString("conclusion"),
                    resultSet.getString("conform_bact"),
                    resultSet.getString("conform_chim")
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
