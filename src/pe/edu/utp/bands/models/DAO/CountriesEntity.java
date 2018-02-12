package pe.edu.utp.bands.models.DAO;

import pe.edu.utp.bands.models.DTO.Country;
import pe.edu.utp.bands.models.DTO.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesEntity extends BaseEntity{
    private static String bd    = "bands";
    private static String table = "countries";
    private static String DEFAULT_SQL = "SELECT * FROM " + bd + "." + table;

    //    General Method to executeQuery
    private List<Country> findByCriteria(String sql) {
        //Creamos elemento de Lista
        List<Country> countries;
        if(getConnection() != null) {
//            Inicializamos la lista
            countries = new ArrayList<>();
            try {
//                Habilitamos el resultado en variable
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
//                Para cada resultado
                while(resultSet.next()) {
//                    Creamos objeto
                    Country country = new Country();
//                    Seteamos los valores en el objeto
                    country.setId(resultSet.getInt("id"));
                    country.setCountry(resultSet.getString("country"));
//                    Agregamos a la lista de Objetos
                    countries.add(country);
                }
                return countries;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //    Find All
    public List<Country> findAll(){
        return findByCriteria(DEFAULT_SQL);
    }
    //    Find By ID
    public Country findById(int id){
        List<Country> countries = findByCriteria(
                DEFAULT_SQL +
                        "WHERE id = " +
                        String.valueOf(id)
        );
        return ((countries.isEmpty()) ? null : countries.get(0));
    }
    //    Find by Name Method
    public Country findByName(String name){
        List<Country> countries = findByCriteria(
                DEFAULT_SQL +
                        "WHERE country = " +
                        name
        );
        return (countries.isEmpty()) ? null : countries.get(0);
    }


}
