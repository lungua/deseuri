/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entBean;

import ent.Clasificaredin;
import ent.Deseuripunctlucru;
import ent.Luna;
import ent.Miscarieliminare;
import ent.Miscarigenerare;
import ent.Miscariluna;
import ent.Miscaristocare;
import ent.Miscarivalorificare;
import ent.Punctlucru;
import java.util.ArrayList;
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
public class MiscarilunaFacade extends AbstractFacade<Miscariluna> {

    @PersistenceContext(unitName = "deseuriWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MiscarilunaFacade() {
        super(Miscariluna.class);
    }

    public List<Deseuripunctlucru> getCodDeseu(Punctlucru pl, Clasificaredin cat) {
        List<Deseuripunctlucru> listDPL = em.createQuery("select u From Deseuripunctlucru u WHERE u.punctLucruidPunctLucru=:wpl AND u.clasificareDinidClasificareDin=:wcat ORDER BY u.idDeseuri desc").setParameter("wcat", cat).setParameter("wpl", pl).getResultList();
        return listDPL;

    }

    public List<Luna> getLuna(Punctlucru pl) {
        List<Luna> listDPL = em.createQuery("select u From Luna u WHERE u.punctLucruidPunctLucru=:wpl  ORDER BY u.idLuna desc").setParameter("wpl", pl).getResultList();
        return listDPL;

    }

    public List<Miscariluna> getMiscariLuna(Luna ll) {
        List<Miscariluna> listDPL = em.createQuery("select u From Miscariluna u WHERE u.lunaidLuna=:wpl  ORDER BY u.idMiscariLuna desc").setParameter("wpl", ll).getResultList();
        return listDPL;
    }

    public List<Miscarigenerare> getMiscGenerare(Miscariluna ll) {
        List<Miscarigenerare> listDPL = em.createQuery("select u From Miscarigenerare u WHERE u.miscariLunaidMiscariLuna=:wpl  ").setParameter("wpl", ll).getResultList();
        return listDPL;
    }

    public List<Miscarieliminare> getMiscEliminare(Miscariluna ll) {
        List<Miscarieliminare> listDPL = em.createQuery("select u From Miscarieliminare u WHERE u.miscariLunaidMiscariLuna=:wpl  ORDER BY u.idMiscariEliminare desc").setParameter("wpl", ll).getResultList();
        return listDPL;
    }
    public List<Miscarivalorificare> getMiscValorificare(Miscariluna ll) {
        List<Miscarivalorificare> listDPL = em.createQuery("select u From Miscarivalorificare u WHERE u.miscariLunaidMiscariLuna=:wpl  ORDER BY u.idMiscariValorificare desc").setParameter("wpl", ll).getResultList();
        return listDPL;
    }
    public List<Miscaristocare> getMiscStocare(Miscariluna ll) {
        List<Miscaristocare> listDPL = em.createQuery("select u From Miscaristocare u WHERE u.miscariLunaidMiscariLuna=:wpl  ORDER BY u.idMiscariStocare desc").setParameter("wpl", ll).getResultList();
        return listDPL;
    }
    public List<Deseuripunctlucru> getDeseuriPl(Punctlucru pl, Clasificaredin cat) {
        List<Deseuripunctlucru> listDPL1 = em.createQuery("select u From Deseuripunctlucru u WHERE u.clasificareDinidClasificareDin=:wcat  AND u.punctLucruidPunctLucru=:wpl ").setParameter("wcat", cat).setParameter("wpl", pl).getResultList();
        return listDPL1;

    }
//    
//    public List<Miscarigenerare>  getGenerareDeseu(Deseuripunctlucru dpl ,Punctlucru pl,Clasificaredin cat){
//        List<Miscarigenerare> listDPL= new ArrayList<>();
//        List<Miscarigenerare> listDPL1= em.createQuery("select u From Miscarigenerare u WHERE u.deseuriPunctLucruidDeseuri=:wdpl  ORDER BY u.idMiscariGenerare desc").setParameter("wdpl", dpl).getResultList();
//        Iterator it= listDPL1.iterator();
//        while(it.hasNext()){
//            Miscarigenerare mg= (Miscarigenerare) it.next();
//            if(mg.getDeseuriPunctLucruidDeseuri().getPunctLucruidPunctLucru().equals(pl) && mg.getDeseuriPunctLucruidDeseuri().getClasificareDinidClasificareDin().equals(cat)){
//                listDPL.add(mg);
//            }
//            
//        }
}
