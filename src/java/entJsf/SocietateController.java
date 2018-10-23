package entJsf;

import ent.Societate;
import ent.Utilizatori;
import ent.Utilizatorisocietate;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.SocietateFacade;
import entBean.UtilizatorisocietateFacade;

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

@Named("societateController")
@SessionScoped
public class SocietateController implements Serializable {

    @EJB
    private entBean.SocietateFacade ejbFacade;

    @EJB
    private entBean.UtilizatorisocietateFacade ejbFacadeUtilizSoc;

    
    
    private List<Societate> items = null;
    private List<Societate> itemsSocietati = null;
    private Societate selected;
    private Societate selectSocietate;
    private List<Societate> itemSoc = null;
    private Societate selectedSoc;

    public SocietateController() {
    }

    public List<Societate> getItemsSocietati() {
        itemsSocietati = getFacadeUtilizSoc().getSocietatiUtilizator();
        return itemsSocietati;
    }

    public void setItemsSocietati(List<Societate> itemsSocietati) {
        this.itemsSocietati = itemsSocietati;
    }

    public List<Societate> getItemSoc() {
        itemSoc = getFacade().findAll();
        return itemSoc;
    }

    public void setItemSoc(List<Societate> itemSoc) {
        this.itemSoc = itemSoc;
    }

    public Societate getSelectSocietate() {
        return selectSocietate;
    }

    public void setSelectSocietate(Societate selectSocietate) {
        this.selectSocietate = selectSocietate;
    }

    public Societate getSelected() {
        return selected;
    }

    public void setSelected(Societate selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UtilizatorisocietateFacade getFacadeUtilizSoc() {
        return ejbFacadeUtilizSoc;
    }

    private SocietateFacade getFacade() {
        return ejbFacade;
    }

    public Societate prepareCreate() {
        selected = new Societate();
        initializeEmbeddableKey();
        return selected;
    }

    public Societate getSelectedSoc() {
        return selectedSoc;
    }

    public void setSelectedSoc(Societate selectedSoc) {
        this.selectedSoc = selectedSoc;
    }

    public Societate prepareCreateSoc() {
        selectedSoc = new Societate();
        initializeEmbeddableKey();
        return selectedSoc;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SocietateCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void createSoc() {
//        persistSoc(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SocietateCreated"));
//        if (!JsfUtil.isValidationFailed()) {
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
        getFacade().create(selectedSoc);
        Utilizatori utiliz = getUtilizator();
        Utilizatorisocietate us= new Utilizatorisocietate();
        us.setIdUtilizatoriSocietate(0);
        us.setSocietateidSocietate(selectedSoc);
        us.setUtilizatoriidUtilizatori(utiliz);
        getFacadeUtilizSoc().create(us);
        
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SocietateUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SocietateDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Societate> getItems() {
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

    private void persistSoc(PersistAction persistAction, String successMessage) {
        if (selectedSoc != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selectedSoc);
                } else {
                    getFacade().remove(selectedSoc);
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

    public Societate getSocietate(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Societate> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Societate> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter("societateConverter")
    public static class SocietateControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SocietateController controller = (SocietateController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "societateController");
            return controller.getSocietate(getKey(value));
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
            if (object instanceof Societate) {
                Societate o = (Societate) object;
                return getStringKey(o.getIdSocietate());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Societate.class.getName()});
                return null;
            }
        }

    }

    public String linkSelectSocietate() {
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/faces/jsf/societate/ListSocietate.xhtml");
        return "output";
    }

    public void onRowSelect(SelectEvent evt) {
        selectSocietate = (Societate) evt.getObject();
        setSocietate(selectSocietate);
        setSocietate(true);
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/left.xhtml");

    }

    public void setSocietate(Societate soc) {
        FacesContext context = FacesContext.getCurrentInstance();
        UtilizatoriController to = (UtilizatoriController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "utilizatoriController");
        to.setSocietateSelectata(soc);

    }

    public void setSocietate(boolean b) {
        FacesContext context = FacesContext.getCurrentInstance();
        UtilizatoriController to = (UtilizatoriController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "utilizatoriController");
        to.setSelectatSocietate(b);

    }
    public Utilizatori getUtilizator() {
        FacesContext context = FacesContext.getCurrentInstance();
        UtilizatoriController to = (UtilizatoriController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "utilizatoriController");
        return to.getUtilizatorLogat();

    }

}
