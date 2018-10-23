/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.UtilizatorLogat;
import ent.Utilizatori;
import entJsf.UtilizatoriController;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lungu
 */
@Stateless
public class UtilizatoriFacade extends AbstractFacade<Utilizatori> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilizatoriFacade() {
        super(Utilizatori.class);
    }
    public UtilizatorLogat  GetUtilizator(String usr,String psw){
        List<Utilizatori> listUt= em.createQuery("select u From Utilizatori u WHERE u.utilizator =:wusr AND u.parola=:wpsw").
                setParameter("wpsw", psw).
                setParameter("wusr", usr).
                getResultList();
        UtilizatorLogat ul=null;
        if(listUt.size()==1){
            Iterator it=listUt.iterator();
            Utilizatori utt= (Utilizatori) it.next();
            ul= new UtilizatorLogat();
            ul.setLogat(true);
            ul.setUsr(utt.getUtilizator());
            setUserLogat(utt);
            
            
        }else{
            ul= new UtilizatorLogat();
            ul.setLogat(false);
            ul.setUsr("");
            
        }
            return ul;

    }
    public  void setUserLogat(Utilizatori usr) {
        FacesContext context = FacesContext.getCurrentInstance();
        UtilizatoriController to = (UtilizatoriController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "utilizatoriController");

        to.setUtilizatorLogat(usr);
    }    
}
