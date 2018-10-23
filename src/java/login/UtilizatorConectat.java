/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import ent.UtilizatorLogat;
import ent.Utilizatori;
import entBean.UtilizatoriFacade;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author lungu
 */
@Named("utilizatorConectat")
@SessionScoped

public class UtilizatorConectat implements Serializable {
    @EJB
    static private entBean.UtilizatoriFacade ejbFacade;
    static private List<Utilizatori> items = null;
    static private String utilizator = "";
    private static UtilizatorLogat userLogat=null;

    public UtilizatorConectat() {

    }

    static private UtilizatoriFacade getFacade() {
        return ejbFacade;
    }
//    public List<Utilizatori> getItems() {
//        
//        return items;
//    }

    public void setItems(List<Utilizatori> items) {
        this.items = items;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
    }

    public  UtilizatorLogat getUserLogat() {
        return userLogat;
    }

    public  void setUserLogat(UtilizatorLogat userLogat) {
        UtilizatorConectat.userLogat = userLogat;
    }
    
    

    public static boolean login(String user, String password) {

        userLogat=getFacade().GetUtilizator(user, password);

        
        return userLogat.isLogat();

        
    }

}
