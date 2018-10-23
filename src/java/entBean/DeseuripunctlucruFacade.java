/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.Clasificaredin;
import ent.Deseuripunctlucru;
import ent.Punctlucru;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lungu
 */
@Stateless
public class DeseuripunctlucruFacade extends AbstractFacade<Deseuripunctlucru> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeseuripunctlucruFacade() {
        super(Deseuripunctlucru.class);
    }
    public List<Deseuripunctlucru>  getCodDeseu(Punctlucru pl,Clasificaredin cat ){
        List<Deseuripunctlucru> listDPL= em.createQuery("select u From Deseuripunctlucru u WHERE u.punctLucruidPunctLucru=:wpl AND u.clasificareDinidClasificareDin=:wcat ORDER BY u.idDeseuri desc").setParameter("wcat", cat).setParameter("wpl", pl).getResultList();
        return listDPL;
        
    }
   
}
