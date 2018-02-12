package pe.edu.utp.bands.controllers;

import pe.edu.utp.bands.models.DTO.User;
import pe.edu.utp.bands.models.Services.UserService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "UsersController", urlPatterns = "/users")
public class UsersController extends HttpServlet {
    //    Variable Connection
    private Connection connection;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("Post", request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest("Get", request, response);
    }

    private Connection getConnection() {
        if (connection == null){
            try {
                InitialContext ctx = new InitialContext();
                DataSource dataSource = (DataSource) ctx
                        .lookup("jdbc/MySQLDataSource1");
                connection = dataSource.getConnection();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private void processRequest(String method,
                                HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "index.jsp";
        if(method.equals("Post") && action == null) { action = "index"; }
//        if(method.equals("Post") && action.equalsIgnoreCase("index")) { return; }
        if(method.equals("Get") && action.equalsIgnoreCase("create")) { return; }
        if(method.equals("Get") && action.equalsIgnoreCase("update")) { return; }

        UserService service = new UserService();
        service.setConnection(getConnection());
        //        action = index, method = Get
        if (action.equalsIgnoreCase("index")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = service.logIn(username, password);
            if (user != null){
                request.setAttribute("users", user);
                url = "principal.jsp";
            }

            //Enviamos los registros
            request.getRequestDispatcher(url).forward(request,response);
        }
    }


}
