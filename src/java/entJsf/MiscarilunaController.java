package entJsf;

import ent.Clasificaredin;
import ent.Deseuripunctlucru;
import ent.Luna;
import ent.Miscarieliminare;
import ent.Miscarigenerare;
import ent.Miscariluna;
import ent.Miscaristocare;
import ent.Miscarivalorificare;
import ent.Punctlucru;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.MiscarilunaFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.SelectEvent;

@Named("miscarilunaController")
@SessionScoped
public class MiscarilunaController implements Serializable {

    @EJB
    private entBean.MiscarilunaFacade ejbFacade;
    private List<Miscariluna> items = null;
    private Miscariluna selected;

    private Punctlucru pctLucru;

    private Clasificaredin clasificareDin = null;
    private List<Deseuripunctlucru> itemsPL = null;
    private Deseuripunctlucru selectDeseuripunctlucru;
    private Luna luna;
    private Luna selectLuna;
    boolean adauga;
    boolean adauga1;
    private List<Luna> itemsLuna;
    private List<Miscariluna> itemsMiscariLuna;
    private List<Deseuripunctlucru> itemsdpl;
    private Miscariluna selectMiscLuna;
    private List<Miscarigenerare> itemsMiscGenerare;
    private List<Miscarieliminare> itemsMiscEliminare;
    private List<Miscarivalorificare> itemsMiscValorificare;
    private List<Miscaristocare> itemsMiscStocare;

    public MiscarilunaController() {
    }

    public List<Luna> getItemsLuna() {
        if (pctLucru != null) {
            itemsLuna = getFacade().getLuna(pctLucru);
        }
        return itemsLuna;
    }

    public void setItemsLuna(List<Luna> itemsLuna) {
        this.itemsLuna = itemsLuna;
    }

    public Punctlucru getPctLucru() {
        return pctLucru;
    }

    public void setPctLucru(Punctlucru pctLucru) {
        this.pctLucru = pctLucru;
    }

    public Clasificaredin getClasificareDin() {
        return clasificareDin;
    }

    public void setClasificareDin(Clasificaredin clasificareDin) {
        this.clasificareDin = clasificareDin;
    }

    public List<Deseuripunctlucru> getItemsPL() {
        return itemsPL;
    }

    public void setItemsPL(List<Deseuripunctlucru> itemsPL) {
        this.itemsPL = itemsPL;
    }

    public Deseuripunctlucru getSelectDeseuripunctlucru() {
        return selectDeseuripunctlucru;
    }

    public void setSelectDeseuripunctlucru(Deseuripunctlucru selectDeseuripunctlucru) {
        this.selectDeseuripunctlucru = selectDeseuripunctlucru;
    }

    public Luna getLuna() {
        return luna;
    }

    public void setLuna(Luna luna) {
        this.luna = luna;
    }

    public Luna getSelectLuna() {
        return selectLuna;
    }

    public void setSelectLuna(Luna selectLuna) {
        this.selectLuna = selectLuna;
    }

    public boolean isAdauga() {
        return adauga;
    }

    public void setAdauga(boolean adauga) {
        this.adauga = adauga;
    }

    public boolean isAdauga1() {
        return adauga1;
    }

    public void setAdauga1(boolean adauga1) {
        this.adauga1 = adauga1;
    }

    public Miscariluna getSelected() {
        return selected;
    }

    public void setSelected(Miscariluna selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MiscarilunaFacade getFacade() {
        return ejbFacade;
    }

    public Miscariluna prepareCreate() {
        selected = new Miscariluna();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MiscarilunaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MiscarilunaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MiscarilunaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Miscariluna> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Miscariluna getMiscariluna(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Miscariluna> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Miscariluna> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Miscariluna.class)
    public static class MiscarilunaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MiscarilunaController controller = (MiscarilunaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "miscarilunaController");
            return controller.getMiscariluna(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Miscariluna) {
                Miscariluna o = (Miscariluna) object;
                return getStringKey(o.getIdMiscariLuna());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Miscariluna.class.getName()});
                return null;
            }
        }

    }

    public String linkMG1() {
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/faces/jsf/miscariluna/ListMiscariLuna.xhtml");
        return "output";
    }

    public void changePunctLucru(ValueChangeEvent evt) {
        pctLucru = ((Punctlucru) evt.getNewValue());
        itemsLuna = getFacade().getLuna(pctLucru);
        itemsMiscGenerare=null;
        itemsMiscEliminare=null;
        itemsMiscValorificare=null;
        itemsMiscStocare=null;
        itemsMiscariLuna=null;
        
        
        
        if (clasificareDin != null) {
            itemsPL = getFacade().getCodDeseu(pctLucru, clasificareDin);

        } else {
            itemsPL = null;
        }

        if (pctLucru != null && clasificareDin != null && selectLuna != null && selectDeseuripunctlucru != null) {
            adauga = false;
        } else {
            adauga = true;
        }

        //System.out.println(((Tipdocumentintrare)evt.getNewValue()).getIdTipObiecte()+"-"+((Tipobiecte)evt.getNewValue()).getNumeTipObiect());
    }

    public void changeClasificaredin(ValueChangeEvent evt) {
        clasificareDin = ((Clasificaredin) evt.getNewValue());
        itemsMiscGenerare=null;
        itemsMiscEliminare=null;
        itemsMiscValorificare=null;
        itemsMiscStocare=null;
         itemsMiscariLuna=null;
        
        
        if (pctLucru != null) {
            itemsPL = getFacade().getCodDeseu(pctLucru, clasificareDin);

        } else {
            itemsPL = null;
        }
        if (pctLucru != null && clasificareDin != null && selectLuna != null && selectDeseuripunctlucru != null) {
            adauga = false;
        } else {
            adauga = true;
        }
        //System.out.println(((Tipdocumentintrare)evt.getNewValue()).getIdTipObiecte()+"-"+((Tipobiecte)evt.getNewValue()).getNumeTipObiect());

    }

    public void onRowSelectLuna(SelectEvent evt) {
        selectLuna = (Luna) evt.getObject();
        itemsMiscariLuna=null;
        itemsMiscGenerare=null;
        itemsMiscEliminare=null;
        itemsMiscValorificare=null;
        itemsMiscStocare=null;
        
        
        //selected.setLunaidLuna(selectLuna);
        itemsMiscariLuna = getFacade().getMiscariLuna(selectLuna);
        if (itemsMiscariLuna.size() != 0) {
            
        } else {
            itemsdpl = getFacade().getDeseuriPl(pctLucru, clasificareDin);

            Iterator it = itemsdpl.iterator();
            while (it.hasNext()) {
                Miscariluna ml = new Miscariluna();
                Deseuripunctlucru dpl = (Deseuripunctlucru) it.next();
                ml.setIdMiscariLuna(0);
                ml.setDeseuriPunctLucruidDeseuri(dpl);
                ml.setCantEliminate(0.0);
                ml.setAgentElconomic("");
                ml.setCantReciclate(0.0);
                ml.setCantRezultata(0.0);
                ml.setCantValorificate(0.0);
                ml.setComponenteComercializate(0.0);
                ml.setStocInceput(0.0);
                ml.setStocSfirsit(0.0);
                ml.setLunaidLuna(selectLuna);
                ml.setMiscEliminare(0.0);
                ml.setMiscGenerare(0.0);
                ml.setMiscStocare(0.0);
                ml.setMiscValorificare(0.0);
                getFacade().create(ml);

//itemsMiscariLuna.add(ml);
            }

        }

    }

    public void onRowSelectMiscGenLuna(SelectEvent evt) {
        selectMiscLuna = (Miscariluna) evt.getObject();
        //selected.setLunaidLuna(selectLuna);
        itemsMiscGenerare = getFacade().getMiscGenerare(selectMiscLuna);
        itemsMiscEliminare = getFacade().getMiscEliminare(selectMiscLuna);
        itemsMiscValorificare = getFacade().getMiscValorificare(selectMiscLuna);
        itemsMiscStocare = getFacade().getMiscStocare(selectMiscLuna);

    }

    public Miscariluna getSelectMiscLuna() {
        return selectMiscLuna;
    }

    public void setSelectMiscLuna(Miscariluna selectMiscLuna) {
        this.selectMiscLuna = selectMiscLuna;
    }
    
    public List<Miscaristocare> getItemsMiscStocare() {
//        if (selectMiscLuna != null && pctLucru != null && clasificareDin != null) {
//            itemsMiscStocare = getFacade().getMiscStocare(selectMiscLuna);
//            itemsdpl = getFacade().getDeseuriPl(pctLucru, clasificareDin);
//        }
        return itemsMiscStocare;
    }

    public void setItemsMiscStocare(List<Miscaristocare> itemsMiscStocare) {
        this.itemsMiscStocare = itemsMiscStocare;
    }
    

    public List<Miscarivalorificare> getItemsMiscValorificare() {
//        if (selectMiscLuna != null && pctLucru != null && clasificareDin != null) {
//            itemsMiscValorificare = getFacade().getMiscValorificare(selectMiscLuna);
//            itemsdpl = getFacade().getDeseuriPl(pctLucru, clasificareDin);
//        }
        return itemsMiscValorificare;
    }

    public void setItemsMiscValorificare(List<Miscarivalorificare> itemsMiscValorificare) {
        this.itemsMiscValorificare = itemsMiscValorificare;
    }
    

    public List<Miscarieliminare> getItemsMiscEliminare() {
//        if (selectMiscLuna != null && pctLucru != null && clasificareDin != null) {
//            itemsMiscEliminare = getFacade().getMiscEliminare(selectMiscLuna);
//            itemsdpl = getFacade().getDeseuriPl(pctLucru, clasificareDin);
//        }
        return itemsMiscEliminare;
    }

    public void setItemsMiscEliminare(List<Miscarieliminare> itemsMiscEliminare) {
        this.itemsMiscEliminare = itemsMiscEliminare;
    }

    public List<Miscariluna> getItemsMiscariLuna() {
//        if (selectMiscLuna != null) {
//            itemsMiscariLuna = getFacade().getMiscariLuna(selectLuna);
//        }
      return itemsMiscariLuna;
    }

    public void setItemsMiscariLuna(List<Miscariluna> itemsMiscariLuna) {
        this.itemsMiscariLuna = itemsMiscariLuna;
    }

    public List<Deseuripunctlucru> getItemsdpl() {
        if (pctLucru != null && clasificareDin != null) {
            itemsdpl = getFacade().getDeseuriPl(pctLucru, clasificareDin);
        }
        return itemsdpl;

    }

    public void setItemsdpl(List<Deseuripunctlucru> itemsdpl) {
        this.itemsdpl = itemsdpl;
    }

    public List<Miscarigenerare> getItemsMiscGenerare() {
//        if (selectMiscLuna != null && pctLucru != null && clasificareDin != null) {
//            itemsMiscGenerare = getFacade().getMiscGenerare(selectMiscLuna);
//            itemsdpl = getFacade().getDeseuriPl(pctLucru, clasificareDin);
//        }
        return itemsMiscGenerare;
    }

    public void setItemsMiscGenerare(List<Miscarigenerare> itemsMiscGenerare) {
        this.itemsMiscGenerare = itemsMiscGenerare;
    }

}
