package entJsf;

import ent.Miscarieliminare;
import ent.Miscariluna;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.MiscarieliminareFacade;
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

@Named("miscarieliminareController")
@SessionScoped
public class MiscarieliminareController implements Serializable {

    @EJB
    private entBean.MiscarilunaFacade ejbFacadeLuna;

    @EJB
    private entBean.MiscarieliminareFacade ejbFacade;
    private List<Miscarieliminare> items = null;
    private Miscarieliminare selected;

    public MiscarieliminareController() {
    }

    private MiscarilunaFacade getFacadeLuna() {
        return ejbFacadeLuna;
    }

    public Miscarieliminare getSelected() {
        return selected;
    }

    public void setSelected(Miscarieliminare selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MiscarieliminareFacade getFacade() {
        return ejbFacade;
    }

    public Miscarieliminare prepareCreate() {
        selected = new Miscarieliminare();
        selected.setIdMiscariEliminare(0);
        selected.setMiscariLunaidMiscariLuna(getSelectMiscLuna());
        selected.setAgentEc("");
        selected.setTipDoc("");
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MiscarieliminareCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        Miscariluna ml = getSelectMiscLuna();
        ml.setMiscEliminare(ml.getMiscEliminare() + selected.getCantitatea());
        ml.setStocSfirsit(ml.getStocSfirsit() - selected.getCantitatea());
        if (selected.getAgentEc().trim() != null) {
            if (selected.getAgentEc().trim().length() > 0 ) {
                ml.setAgentElconomic(ml.getAgentElconomic() + "Elimin.=" + selected.getAgentEc());
            }
        }
        getFacadeLuna().edit(ml);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MiscarieliminareUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MiscarieliminareDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Miscarieliminare> getItems() {
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

    public Miscarieliminare getMiscarieliminare(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Miscarieliminare> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Miscarieliminare> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Miscarieliminare.class)
    public static class MiscarieliminareControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MiscarieliminareController controller = (MiscarieliminareController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "miscarieliminareController");
            return controller.getMiscarieliminare(getKey(value));
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
            if (object instanceof Miscarieliminare) {
                Miscarieliminare o = (Miscarieliminare) object;
                return getStringKey(o.getIdMiscariEliminare());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Miscarieliminare.class.getName()});
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
}
