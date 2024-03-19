package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Commune;

public class CommuneDAO {
    
    private Connection connection;
    
    public CommuneDAO() {
    }
    
    private void dbConnect() throws SQLException {
    	try {
            // Charge explicitement le pilote JDBC pour MySQL
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
    
    public ArrayList<Commune> getAllResultats(String nom_commune) throws SQLException {
        
    	dbConnect();
    	ArrayList<Commune> communes = new ArrayList<>();
        PrelevementDAO prelevementDao = new PrelevementDAO();
        String query = "SELECT * FROM commune WHERE nom_commune LIKE ?";

        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, "%" + nom_commune + "%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Commune commune = new Commune(
                	resultSet.getString("insee_commune"),
                    resultSet.getString("nom_commune"),
                    resultSet.getString("quartier"),
                    resultSet.getString("cd_reseau"),
                    resultSet.getString("nom_reseau"),
                    resultSet.getDate("debut_alim"),
                    prelevementDao.getAllResultats(resultSet.getString("insee_commune"), resultSet.getString("cd_reseau"))
                );
                communes.add(commune);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbClose();
        return communes;
        
    }
    
    public ArrayList<String> getAllCommunesNames(String nom_commune)throws SQLException{
    	dbConnect();
    	ArrayList<String> listCommunes= new ArrayList<>();
        String query = "SELECT nom_commune FROM commune WHERE nom_commune LIKE ?";

        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, "%" + nom_commune + "%");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                listCommunes.add(resultSet.getString("nom_commune"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	dbClose();
        }
        
        return listCommunes;
        
    }
    
    // Vous pouvez ajouter d'autres méthodes (addResultat, updateResultat, deleteResultat) ici
}
