package entJsf;

import ent.Subcategoriideseuri;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.SubcategoriideseuriFacade;

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

@Named("subcategoriideseuriController")
@SessionScoped
public class SubcategoriideseuriController implements Serializable {

    @EJB
    private entBean.SubcategoriideseuriFacade ejbFacade;
    private List<Subcategoriideseuri> items = null;
    private Subcategoriideseuri selected;

    public SubcategoriideseuriController() {
    }

    public Subcategoriideseuri getSelected() {
        return selected;
    }

    public void setSelected(Subcategoriideseuri selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubcategoriideseuriFacade getFacade() {
        return ejbFacade;
    }

    public Subcategoriideseuri prepareCreate() {
        selected = new Subcategoriideseuri();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SubcategoriideseuriCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SubcategoriideseuriUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SubcategoriideseuriDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Subcategoriideseuri> getItems() {
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

    public Subcategoriideseuri getSubcategoriideseuri(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Subcategoriideseuri> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Subcategoriideseuri> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Subcategoriideseuri.class)
    public static class SubcategoriideseuriControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubcategoriideseuriController controller = (SubcategoriideseuriController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subcategoriideseuriController");
            return controller.getSubcategoriideseuri(getKey(value));
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
            if (object instanceof Subcategoriideseuri) {
                Subcategoriideseuri o = (Subcategoriideseuri) object;
                return getStringKey(o.getIdSubcategoriiDeseuri());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Subcategoriideseuri.class.getName()});
                return null;
            }
        }

    }

}
