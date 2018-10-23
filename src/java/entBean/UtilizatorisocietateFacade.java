/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.Societate;
import ent.Utilizatori;
import ent.Utilizatorisocietate;
import entJsf.UtilizatoriController;
import java.util.ArrayList;
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
public class UtilizatorisocietateFacade extends AbstractFacade<Utilizatorisocietate> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilizatorisocietateFacade() {
        super(Utilizatorisocietate.class);
    }
    public List<Societate>  getSocietatiUtilizator(){
        List<Societate> listSoc= new ArrayList<>();
        List<Utilizatorisocietate> listUt= em.createQuery("select u From Utilizatorisocietate u ").getResultList();
        Iterator it= listUt.iterator();
        Utilizatori ulog = getUserLogat();
        while(it.hasNext()){
            Utilizatorisocietate ut= (Utilizatorisocietate) it.next();
            if(ut.getUtilizatoriidUtilizatori().equals(ulog)){
                listSoc.add(ut.getSocietateidSocietate());
            }
        }
        return listSoc;
        
    }
    public Utilizatori getUserLogat() {
        FacesContext context = FacesContext.getCurrentInstance();
        UtilizatoriController to = (UtilizatoriController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "utilizatoriController");
        return to.getUtilizatorLogat();
    }  
}
