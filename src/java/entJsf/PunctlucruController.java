package entJsf;

import ent.Punctlucru;
import ent.Societate;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.PunctlucruFacade;

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

@Named("punctlucruController")
@SessionScoped
public class PunctlucruController implements Serializable {

    @EJB
    private entBean.PunctlucruFacade ejbFacade;
    private List<Punctlucru> items = null;
    private List<Punctlucru> itemsPL = null;
    private List<Punctlucru> itemsPL1 = null;
    private Punctlucru selected;
    private Punctlucru selectPL;
    private Punctlucru selectPL1;
    private Punctlucru selectPL11;
    private Punctlucru selectPL2;
    private Punctlucru selectPL22;
    private Punctlucru selectPL3;
    private Punctlucru selectPL33;
    private Punctlucru selectPL4;
    private Punctlucru selectPL44;

    public PunctlucruController() {
    }

    public List<Punctlucru> getItemsPL() {
        itemsPL = getFacade().getPuncteLucru(getSocietate());
        return itemsPL;
    }

    public void setItemsPL(List<Punctlucru> itemsPL) {
        
        this.itemsPL = itemsPL;
    }

    public List<Punctlucru> getItemsPL1() {
        return itemsPL1;
    }

    public void setItemsPL1(List<Punctlucru> itemsPL1) {
        this.itemsPL1 = itemsPL1;
    }

    public Punctlucru getSelectPL() {
        return selectPL;
    }

    public void setSelectPL(Punctlucru selectPL) {
        this.selectPL = selectPL;
    }

    public Punctlucru getSelectPL1() {
        return selectPL1;
    }

    public void setSelectPL1(Punctlucru selectPL1) {
        this.selectPL1 = selectPL1;
    }

    public Punctlucru getSelectPL11() {
        return selectPL11;
    }

    public void setSelectPL11(Punctlucru selectPL11) {
        this.selectPL11 = selectPL11;
    }

    public Punctlucru getSelectPL2() {
        return selectPL2;
    }

    public void setSelectPL2(Punctlucru selectPL2) {
        this.selectPL2 = selectPL2;
    }

    public Punctlucru getSelectPL22() {
        return selectPL22;
    }

    public void setSelectPL22(Punctlucru selectPL22) {
        this.selectPL22 = selectPL22;
    }

    public Punctlucru getSelectPL3() {
        return selectPL3;
    }

    public void setSelectPL3(Punctlucru selectPL3) {
        this.selectPL3 = selectPL3;
    }

    public Punctlucru getSelectPL33() {
        return selectPL33;
    }

    public void setSelectPL33(Punctlucru selectPL33) {
        this.selectPL33 = selectPL33;
    }

    public Punctlucru getSelectPL4() {
        return selectPL4;
    }

    public void setSelectPL4(Punctlucru selectPL4) {
        this.selectPL4 = selectPL4;
    }

    public Punctlucru getSelectPL44() {
        return selectPL44;
    }

    public void setSelectPL44(Punctlucru selectPL44) {
        this.selectPL44 = selectPL44;
    }

    public Punctlucru getSelected() {
        return selected;
    }

    public void setSelected(Punctlucru selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PunctlucruFacade getFacade() {
        return ejbFacade;
    }

    public Punctlucru prepareCreate() {
        selected = new Punctlucru();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PunctlucruCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PunctlucruUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PunctlucruDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Punctlucru> getItems() {
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

    public Punctlucru getPunctlucru(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Punctlucru> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Punctlucru> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter("punctlucruConverter")
    public static class PunctlucruControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PunctlucruController controller = (PunctlucruController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "punctlucruController");
            return controller.getPunctlucru(getKey(value));
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
            if (object instanceof Punctlucru) {
                Punctlucru o = (Punctlucru) object;
                return getStringKey(o.getIdPunctLucru());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Punctlucru.class.getName()});
                return null;
            }
        }

    }

    public String linkPunctLucru() {
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/faces/jsf/punctlucru/ListPunctLucru.xhtml");
        return "output";
    }

    public Societate getSocietate() {
        FacesContext context = FacesContext.getCurrentInstance();
        UtilizatoriController to = (UtilizatoriController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "utilizatoriController");
        return to.getSocietateSelectata();

    }
}
