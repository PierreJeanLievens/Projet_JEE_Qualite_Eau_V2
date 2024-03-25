package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Commune;
/**
 * Class CommuneDAO
 * @author PIERRE-JEAN
 *
 */
public class CommuneDAO {
    
    private Connection connection;
    
    public CommuneDAO() {
   
    }
    /**
     * Method to connect to the DB
     * @throws SQLException
     */
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
    /**
     * Method to close the connection to the DB
     * @throws SQLException
     */
    private void dbClose() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    /**
     * Method to get all communes with the name
     * @param nom_commune
     * @return a list of Communes
     * @throws SQLException
     */
    public ArrayList<Commune> getAllCommunesByName(String nom_commune) throws SQLException {
        
    	dbConnect();
    	// Creation empty list
    	ArrayList<Commune> communes = new ArrayList<>();
    	// initialise PrelevementDAO with the current connection
        PrelevementDAO prelevementDao = new PrelevementDAO(this.connection);
        // Prepare the query
        String query = "SELECT * FROM commune WHERE nom_commune = ?";

        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1,nom_commune );
            ResultSet resultSet = statement.executeQuery();
            // while there is a result
            while (resultSet.next()) {
            	// creation of a Commune object
                Commune commune = new Commune(
                	resultSet.getString("insee_commune"),
                    resultSet.getString("nom_commune"),
                    resultSet.getString("quartier"),
                    resultSet.getString("cd_reseau"),
                    resultSet.getString("nom_reseau"),
                    resultSet.getDate("debut_alim"),
                    prelevementDao.getAllResultats(resultSet.getString("insee_commune"), resultSet.getString("cd_reseau"))
                );
                // add the current commune to the list
                communes.add(commune);
            }
        } catch (SQLException e) {
        	throw new RuntimeException("uncaught", e) ;
        }
        // close the connection
        dbClose();
        return communes;
        
    }
    /**
     * Method to get only a list of names of communes who contains the parameter nom_commune
     * @param nom_commune
     * @return a list of String
     * @throws SQLException
     */
    public ArrayList<String> getAllCommunesNames(String nom_commune)throws SQLException{
    	dbConnect();
    	// creation a empty list of String
    	ArrayList<String> listCommunes= new ArrayList<>();
    	// prepare the query
        String query = "SELECT DISTINCT nom_commune FROM commune WHERE nom_commune LIKE ?";

        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	// put the String to search if the query contains which is different than just equal
        	statement.setString(1, "%" + nom_commune + "%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
            	// get the String of the commune
                listCommunes.add(resultSet.getString("nom_commune"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        // close connection
        dbClose();
        return listCommunes;
        
    }
    
    // Vous pouvez ajouter d'autres méthodes (addResultat, updateResultat, deleteResultat) ici
}
