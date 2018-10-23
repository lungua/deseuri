package entJsf;

import ent.Miscariluna;
import ent.Miscaristocare;
import entBean.MiscarilunaFacade;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.MiscaristocareFacade;

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

@Named("miscaristocareController")
@SessionScoped
public class MiscaristocareController implements Serializable {
    
    @EJB
    private entBean.MiscarilunaFacade ejbFacadeLuna;

    @EJB
    private entBean.MiscaristocareFacade ejbFacade;
    private List<Miscaristocare> items = null;
    private Miscaristocare selected;

    public MiscaristocareController() {
    }
    private MiscarilunaFacade getFacadeLuna() {
        return ejbFacadeLuna;
    }
    public Miscaristocare getSelected() {
        return selected;
    }

    public void setSelected(Miscaristocare selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MiscaristocareFacade getFacade() {
        return ejbFacade;
    }

    public Miscaristocare prepareCreate() {
        selected = new Miscaristocare();
        selected.setIdMiscariStocare(0);
        selected.setMiscariLunaidMiscariLuna(getSelectMiscLuna());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MiscaristocareCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        Miscariluna ml = getSelectMiscLuna();
        ml.setMiscStocare(ml.getMiscStocare() + selected.getCantitate());
        ml.setStocSfirsit(ml.getStocSfirsit() - selected.getCantitate());
        getFacadeLuna().edit(ml);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MiscaristocareUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MiscaristocareDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Miscaristocare> getItems() {
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

    public Miscaristocare getMiscaristocare(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Miscaristocare> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Miscaristocare> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Miscaristocare.class)
    public static class MiscaristocareControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MiscaristocareController controller = (MiscaristocareController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "miscaristocareController");
            return controller.getMiscaristocare(getKey(value));
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
            if (object instanceof Miscaristocare) {
                Miscaristocare o = (Miscaristocare) object;
                return getStringKey(o.getIdMiscariStocare());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Miscaristocare.class.getName()});
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
