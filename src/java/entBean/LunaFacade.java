/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.Anul;
import ent.Luna;
import ent.Punctlucru;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lungu
 */
@Stateless
public class LunaFacade extends AbstractFacade<Luna> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LunaFacade() {
        super(Luna.class);
    }
    public Anul  getAnul(Integer pl){
        List<Anul> listDPL= em.createQuery("select u From Anul u WHERE u.anul=:wpl  ").setParameter("wpl", pl).getResultList();
        Iterator it= listDPL.iterator();
        Anul an=null;
        if(listDPL.size()==1){
            an= (Anul)it.next();
        }
        return an;
    }
    public boolean  getLuna(Anul an,Integer ll,Punctlucru pl ){
        List<Luna> listDPL= em.createQuery("select u From Luna u WHERE u.anulidAnul=:wan AND u.punctLucruidPunctLucru=:wpl  ").setParameter("wpl", pl).setParameter("wan", an).getResultList();
        Iterator it= listDPL.iterator();
        boolean ww= false;
        while(it.hasNext()){
            Luna lll=(Luna) it.next();
            if(lll.getLuna()==ll){
                ww=true;
            }
            
        }
        
        return ww;
    }
}
