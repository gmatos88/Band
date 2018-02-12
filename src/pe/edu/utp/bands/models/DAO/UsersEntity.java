package pe.edu.utp.bands.models.DAO;

import pe.edu.utp.bands.models.DTO.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersEntity extends BaseEntity {
    private static String bd    = "band";
    private static String table = "users";
    private static String DEFAULT_SQL = "SELECT * FROM " + bd + "." + table + " ";
    //    General Method to executeQuery
    private List<User> findByCriteria(String sql,
                                      UsersTypesEntity usersTypesEntity,
                                      CountriesEntity countriesEntity) {
        List<User> users;
        if(getConnection() != null) {
            users = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                while(resultSet.next()) {
                    User user = new User();

                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));

                    user.setFirstname(resultSet.getString("first_name"));
                    user.setLastname(resultSet.getString("last_name"));

                    user.setType(usersTypesEntity.findById(resultSet.getInt("type")));
                    user.setCountry(countriesEntity.findById(resultSet.getInt("country")));

                    users.add(user);
                }
                return users;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //    General Method to executeUpdate
    private int updateByCriteria(String sql){
        if (getConnection() != null){
            try {
                return getConnection()
                        .createStatement()
                        .executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    //    Find All Method
    public List<User> findAll(UsersTypesEntity usersTypesEntity, CountriesEntity countriesEntity){
        return findByCriteria(DEFAULT_SQL, usersTypesEntity, countriesEntity);
    }
    //    Find by Id Method
    public User findById(int id, UsersTypesEntity usersTypesEntity, CountriesEntity countriesEntity){
        List<User> users = findByCriteria(
                DEFAULT_SQL +
                        "WHERE id = " +
                        String.valueOf(id)
                ,usersTypesEntity, countriesEntity);
        return (users != null ? users.get(0) : null);
    }
    //    Find by Name Method
    public User findByName(String username, UsersTypesEntity usersTypesEntity, CountriesEntity countriesEntity){
        List<User> users = findByCriteria(
                DEFAULT_SQL +
                        " WHERE user_name = '" + username + "'"
                , usersTypesEntity, countriesEntity);
        return  (users.isEmpty()) ? null : users.get(0);
    }
    //    Create user
    public User create(String username, String email, String password, String firstName, String lastName,
                       UsersTypesEntity usersTypesEntity, CountriesEntity countriesEntity){
        if (findByName(username,usersTypesEntity, countriesEntity) == null){
            if (getConnection() != null){
                String sql = "INSERT INTO "+table+"(user_name, email, password , type,first_name,last_name,) " +
                        "VALUES('" +
                        username + "' , '"+email+"','"+password+"','1','"+firstName+"','"+lastName+"'";
                int results = updateByCriteria(sql);
                if (results > 0){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setFirstname(firstName);
                    user.setLastname(lastName);
                    user.setType(usersTypesEntity.findById(1));
                    return user;
                }
            }
        }
        return null;
    }
    // LogIn Method
    public User logIn(String username, String password, UsersTypesEntity usersTypesEntity, CountriesEntity countriesEntity){
        String sql = DEFAULT_SQL + "WHERE " +
                "user_name = '"+username+"' AND " +
                "password = "+password;
        List<User> users = findByCriteria(sql
                , usersTypesEntity, countriesEntity);
        return (users.isEmpty()) ? null : users.get(0);
    }
    //    Delete by Id
    public boolean delete(int id){
        return updateByCriteria("DELETE FROM "+table+" WHERE id = " +
                String.valueOf(id)) > 0;
    }
    //    Delete by Name
    public boolean delete(String username){
        return updateByCriteria("DELETE FROM "+table+" WHERE username = '" +
                username + "'") > 0;
    }
    //    Update by User Object
    public boolean update(User user){
        return updateByCriteria("UPDATE "+table +
                " SET name = '" + user.getUsername() + "' " +
                " WHERE id= "+ String.valueOf(user.getId())) > 0;
    }

}
