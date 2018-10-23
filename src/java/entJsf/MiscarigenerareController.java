package entJsf;

import ent.Miscarigenerare;
import ent.Miscariluna;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.MiscarigenerareFacade;
import entBean.MiscarilunaFacade;

import java.io.Serializable;
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
import org.primefaces.event.SelectEvent;

@Named("miscarigenerareController")
@SessionScoped
public class MiscarigenerareController implements Serializable {

    @EJB
    private entBean.MiscarigenerareFacade ejbFacade;

    @EJB
    private entBean.MiscarilunaFacade ejbFacadeLuna;

    private List<Miscarigenerare> items = null;
    private Miscarigenerare selected;
    private Miscarigenerare selectMiscGen=null;
    private Double cantUpdate=0.0;

    public MiscarigenerareController() {
    }

    public Miscarigenerare getSelectMiscGen() {
        return selectMiscGen;
    }

    public void setSelectMiscGen(Miscarigenerare selectMiscGen) {
        this.selectMiscGen = selectMiscGen;
    }
    
    public Miscarigenerare getSelected() {
        return selected;
    }

    public void setSelected(Miscarigenerare selected) {
        //selectMiscGen=selected;
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MiscarigenerareFacade getFacade() {
        return ejbFacade;
    }

    private MiscarilunaFacade getFacadeLuna() {
        return ejbFacadeLuna;
    }

    public Miscarigenerare prepareCreate() {
        selected = new Miscarigenerare();
        selected.setIdMiscariGenerare(0);
        selected.setMiscariLunaidMiscariLuna(getSelectMiscLuna());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MiscarigenerareCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            Miscariluna ml = getSelectMiscLuna();
            ml.setMiscGenerare(ml.getMiscGenerare() + selected.getCantitate());
            ml.setStocSfirsit(ml.getStocSfirsit() + selected.getCantitate());
            getFacadeLuna().edit(ml);
            List<Miscarigenerare> listMiscGen = getFacade().getMiscGenerare(ml);
            getMiscGen(listMiscGen);
        } else {

        }

    }

    public void prepareUpdate() {
        

    }

    public void update() {
        //cantUpdate = selectMiscGen.getCantitate();
        int ii = 0;
        //selected = selectMiscGen;
        if (selectMiscGen != null) {
            persistUpdate(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MiscarigenerareUpdated"));
            if (!JsfUtil.isValidationFailed()) {
                Miscariluna ml = getSelectMiscLuna();
                ml.setMiscGenerare(ml.getMiscGenerare() + selectMiscGen.getCantitate() - cantUpdate);
                ml.setStocSfirsit(ml.getStocSfirsit() + selectMiscGen.getCantitate() - cantUpdate);

                getFacadeLuna().edit(ml);
                List<Miscarigenerare> listMiscGen = getFacade().getMiscGenerare(ml);
                getMiscGen(listMiscGen);
            } else {

            }
        } else {
            JsfUtil.addErrorMessage("Atentie.. nu ati selectat inregistrarea");
        }

    }

    public void destroy() {
        //selected = selectMiscGen;
        if (selected != null) {
            persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MiscarigenerareDeleted"));
            if (!JsfUtil.isValidationFailed()) {
                selected = null; // Remove selection
                items = null;    // Invalidate list of items to trigger re-query.
                Miscariluna ml = getSelectMiscLuna();
                ml.setMiscGenerare(ml.getMiscGenerare() - selectMiscGen.getCantitate());
                ml.setStocSfirsit(ml.getStocSfirsit() - selectMiscGen.getCantitate());

                getFacadeLuna().edit(ml);
                List<Miscarigenerare> listMiscGen = getFacade().getMiscGenerare(ml);
                getMiscGen(listMiscGen);
            } else {

            }
        }else{
             JsfUtil.addErrorMessage("Atentie.. nu ati selectat inregistrarea");
        }
    }

    public List<Miscarigenerare> getItems() {
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

    private void persistUpdate(PersistAction persistAction, String successMessage) {
        if (selectMiscGen != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selectMiscGen);
                } else {
                    getFacade().remove(selectMiscGen);
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
    
    public Miscarigenerare getMiscarigenerare(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Miscarigenerare> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Miscarigenerare> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Miscarigenerare.class)
    public static class MiscarigenerareControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MiscarigenerareController controller = (MiscarigenerareController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "miscarigenerareController");
            return controller.getMiscarigenerare(getKey(value));
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
            if (object instanceof Miscarigenerare) {
                Miscarigenerare o = (Miscarigenerare) object;
                return getStringKey(o.getIdMiscariGenerare());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Miscarigenerare.class.getName()});
                return null;
            }
        }

    }

    public Miscariluna getSelectMiscLuna() {
        FacesContext context = FacesContext.getCurrentInstance();
        MiscarilunaController to = (MiscarilunaController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "miscarilunaController");
        return to.getSelectMiscLuna();

    }

    public void getMiscGen(List<Miscarigenerare> lll) {
        FacesContext context = FacesContext.getCurrentInstance();
        MiscarilunaController to = (MiscarilunaController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "miscarilunaController");
        to.setItemsMiscGenerare(lll);

    }
//    public Miscariluna getMiscLuna() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        MiscarilunaController to = (MiscarilunaController) context.getApplication().getELResolver().
//                getValue(context.getELContext(), null, "iscarilunaController");
//        return to.getSelectMiscLuna();
//
//    }

    public void onRowSelectGenerate(SelectEvent evt) {
        selectMiscGen = (Miscarigenerare) evt.getObject();
        cantUpdate = selectMiscGen.getCantitate();
        //selected = selectMiscGen;
        //selected.setLunaidLuna(selectLuna);

    }

}
