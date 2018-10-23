/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.Miscaristocare;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lungu
 */
@Stateless
public class MiscaristocareFacade extends AbstractFacade<Miscaristocare> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MiscaristocareFacade() {
        super(Miscaristocare.class);
    }
    
}
