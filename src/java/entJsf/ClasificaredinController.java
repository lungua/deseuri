package entJsf;

import ent.Clasificaredin;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.ClasificaredinFacade;

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

@Named("clasificaredinController")
@SessionScoped
public class ClasificaredinController implements Serializable {

    @EJB
    private entBean.ClasificaredinFacade ejbFacade;
    private List<Clasificaredin> items = null;
    private Clasificaredin selected;
    
    private Clasificaredin selectedClasif;
    private Clasificaredin selectedClasif1;
    private Clasificaredin selectedClasif11;
    private Clasificaredin selectedClasif2;
     private Clasificaredin selectedClasif22;
     private Clasificaredin selectedClasif3;
     private Clasificaredin selectedClasif33;
     private Clasificaredin selectedClasif4;
     private Clasificaredin selectedClasif44;

    public ClasificaredinController() {
    }

    public Clasificaredin getSelectedClasif() {
        return selectedClasif;
    }

    public void setSelectedClasif(Clasificaredin selectedClasif) {
        this.selectedClasif = selectedClasif;
    }

    public Clasificaredin getSelectedClasif1() {
        return selectedClasif1;
    }

    public void setSelectedClasif1(Clasificaredin selectedClasif1) {
        this.selectedClasif1 = selectedClasif1;
    }

    public Clasificaredin getSelectedClasif11() {
        return selectedClasif11;
    }

    public void setSelectedClasif11(Clasificaredin selectedClasif11) {
        this.selectedClasif11 = selectedClasif11;
    }

    public Clasificaredin getSelectedClasif2() {
        return selectedClasif2;
    }

    public void setSelectedClasif2(Clasificaredin selectedClasif2) {
        this.selectedClasif2 = selectedClasif2;
    }

    public Clasificaredin getSelectedClasif22() {
        return selectedClasif22;
    }

    public void setSelectedClasif22(Clasificaredin selectedClasif22) {
        this.selectedClasif22 = selectedClasif22;
    }

    public Clasificaredin getSelectedClasif3() {
        return selectedClasif3;
    }

    public void setSelectedClasif3(Clasificaredin selectedClasif3) {
        this.selectedClasif3 = selectedClasif3;
    }

    public Clasificaredin getSelectedClasif33() {
        return selectedClasif33;
    }

    public void setSelectedClasif33(Clasificaredin selectedClasif33) {
        this.selectedClasif33 = selectedClasif33;
    }

    public Clasificaredin getSelectedClasif4() {
        return selectedClasif4;
    }

    public void setSelectedClasif4(Clasificaredin selectedClasif4) {
        this.selectedClasif4 = selectedClasif4;
    }

    public Clasificaredin getSelectedClasif44() {
        return selectedClasif44;
    }

    public void setSelectedClasif44(Clasificaredin selectedClasif44) {
        this.selectedClasif44 = selectedClasif44;
    }

    public Clasificaredin getSelected() {
        return selected;
    }

    public void setSelected(Clasificaredin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClasificaredinFacade getFacade() {
        return ejbFacade;
    }

    public Clasificaredin prepareCreate() {
        selected = new Clasificaredin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ClasificaredinCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ClasificaredinUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ClasificaredinDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Clasificaredin> getItems() {
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

    public Clasificaredin getClasificaredin(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Clasificaredin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Clasificaredin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter("clasificaredinConverter")
    public static class ClasificaredinControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClasificaredinController controller = (ClasificaredinController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clasificaredinController");
            return controller.getClasificaredin(getKey(value));
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
            if (object instanceof Clasificaredin) {
                Clasificaredin o = (Clasificaredin) object;
                return getStringKey(o.getIdClasificareDin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Clasificaredin.class.getName()});
                return null;
            }
        }

    }

}
