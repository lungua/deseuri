/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.Luna;
import ent.Miscarigenerare;
import ent.Miscariluna;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lungu
 */
@Stateless
public class MiscarigenerareFacade extends AbstractFacade<Miscarigenerare> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MiscarigenerareFacade() {
        super(Miscarigenerare.class);
    }
    public List<Miscarigenerare> getMiscGenerare(Miscariluna ll) {
        List<Miscarigenerare> listDPL = em.createQuery("select u From Miscarigenerare u WHERE u.miscariLunaidMiscariLuna=:wpl  ").setParameter("wpl", ll).getResultList();
        return listDPL;
    }
}
