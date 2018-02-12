package pe.edu.utp.bands.models.DAO;

import pe.edu.utp.bands.models.DTO.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersTypesEntity extends BaseEntity {
    private static String bd    = "band";
    private static String table = "users_types";
    private static String DEFAULT_SQL = "SELECT * FROM " + bd + "." + table + " ";

    //    General Method to executeQuery
    private List<UserType> findByCriteria(String sql) {
        //Creamos elemento de Lista
        List<UserType> userTypes;
        if(getConnection() != null) {
//            Inicializamos la lista
            userTypes = new ArrayList<>();
            try {
//                Habilitamos el resultado en variable
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
//                Para cada resultado
                while(resultSet.next()) {
//                    Creamos objeto
                    UserType userType = new UserType();
//                    Seteamos los valores en el objeto
                    userType.setId(resultSet.getInt("id"));
                    userType.setDescription(resultSet.getString("description"));
//                    Agregamos a la lista de Objetos
                    userTypes.add(userType);
                }
                return userTypes;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //    Find All
    public List<UserType> findAll(){
        return findByCriteria(DEFAULT_SQL);
    }
    //    Find By ID
    public UserType findById(int id){
        List<UserType> userTypes = findByCriteria(
                DEFAULT_SQL +
                        "WHERE id = " +
                        String.valueOf(id)
        );
        return ((userTypes.isEmpty()) ? null : userTypes.get(0));
    }
}
