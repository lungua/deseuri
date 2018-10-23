/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.Punctlucru;
import ent.Societate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lungu
 */
@Stateless
public class PunctlucruFacade extends AbstractFacade<Punctlucru> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PunctlucruFacade() {
        super(Punctlucru.class);
    }
    public List<Punctlucru>  getPuncteLucru(Societate soc){
        List<Punctlucru> listUt= em.createQuery("select u From Punctlucru u WHERE u.societateidSocietate =:wsoc").
                setParameter("wsoc", soc).
                getResultList();


    return listUt;

    }
}
