package entJsf;

import ent.Miscariluna;
import ent.Miscarivalorificare;
import entBean.MiscarilunaFacade;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.MiscarivalorificareFacade;

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

@Named("miscarivalorificareController")
@SessionScoped
public class MiscarivalorificareController implements Serializable {

    @EJB
    private entBean.MiscarilunaFacade ejbFacadeLuna;

    @EJB
    private entBean.MiscarivalorificareFacade ejbFacade;
    private List<Miscarivalorificare> items = null;
    private Miscarivalorificare selected;

    public MiscarivalorificareController() {
    }

    private MiscarilunaFacade getFacadeLuna() {
        return ejbFacadeLuna;
    }

    public Miscarivalorificare getSelected() {
        return selected;
    }

    public void setSelected(Miscarivalorificare selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MiscarivalorificareFacade getFacade() {
        return ejbFacade;
    }

    public Miscarivalorificare prepareCreate() {
        selected = new Miscarivalorificare();
        selected.setIdMiscariValorificare(0);
        selected.setMiscariLunaidMiscariLuna(getSelectMiscLuna());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MiscarivalorificareCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        Miscariluna ml = getSelectMiscLuna();
        ml.setMiscValorificare(ml.getMiscEliminare() + selected.getCantitate());
        ml.setStocSfirsit(ml.getStocSfirsit() - selected.getCantitate());
        getFacadeLuna().edit(ml);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MiscarivalorificareUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MiscarivalorificareDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Miscarivalorificare> getItems() {
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

    public Miscarivalorificare getMiscarivalorificare(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Miscarivalorificare> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Miscarivalorificare> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Miscarivalorificare.class)
    public static class MiscarivalorificareControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MiscarivalorificareController controller = (MiscarivalorificareController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "miscarivalorificareController");
            return controller.getMiscarivalorificare(getKey(value));
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
            if (object instanceof Miscarivalorificare) {
                Miscarivalorificare o = (Miscarivalorificare) object;
                return getStringKey(o.getIdMiscariValorificare());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Miscarivalorificare.class.getName()});
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
