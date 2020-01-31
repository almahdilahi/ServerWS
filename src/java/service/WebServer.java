/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import method.IUser;
import model.UserDao;
import entities.User;

/**
 *
 * @author HP
 */
@WebService(serviceName = "WebServer")
public class WebServer {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    public int add(@WebParam(name = "name") String name,@WebParam(name = "login") String login,@WebParam(name = "password") String password) {
        IUser iu = new UserDao();
        User u = new User();
        u.setName(name);
        u.setLogin(login);
        u.setPassword(password);
        int ok = iu.add(u);
        return ok;
        
    }
     
    @WebMethod(operationName = "liste")
    public List<User> liste(){
        IUser iu = new UserDao();
        return iu.liste();
    }
    
     @WebMethod(operationName = "delete")
    public String delete(@WebParam(name = "id") int id) throws Exception {
        IUser iu = new UserDao();
        iu.delete(id);
        if(iu.delete(id)){
            System.out.println("ok");
        }
         System.out.println("ko");
        return "User deleted";
    }
    
    @WebMethod(operationName = "update")
    public String update(@WebParam(name = "id") int id,@WebParam(name = "name") String name,@WebParam(name = "login") String login,@WebParam(name = "password") String password){
        IUser iu = new UserDao();
        User u = new User();
        u.setId(id);
        u.setName(name);
        u.setLogin(login);
        u.setPassword(password);
        iu.update(u);
        if(iu.update(u)){
            System.out.println("ok");
        }
         System.out.println("ko");
        return "User updated";
    }
    
    @WebMethod(operationName = "getLogin")
    public User getLogin(@WebParam(name = "login") String login,@WebParam(name = "password") String password){
        IUser iu = new UserDao();
        User u = new User();
        u.setLogin(login);
        u.setPassword(password);
        iu.getLogin(login,password);
        return u;
    }
}
