package entJsf;

import ent.Anul;
import ent.Luna;
import ent.Punctlucru;
import entBean.AnulFacade;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.LunaFacade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
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



@Named("lunaController")
@SessionScoped
public class LunaController implements Serializable {

    @EJB
    private entBean.LunaFacade ejbFacade;

    @EJB
    private entBean.AnulFacade ejbFacadeAnul;

    private List<Luna> items = null;
    private Luna selected;
    private Date lunaSelectata;

    public LunaController() {
    }

    public Date getLunaSelectata() {
        return lunaSelectata;
    }

    public void setLunaSelectata(Date lunaSelectata) {
        this.lunaSelectata = lunaSelectata;
    }

    public Luna getSelected() {
        return selected;
    }

    public void setSelected(Luna selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LunaFacade getFacade() {
        return ejbFacade;
    }

    private AnulFacade getFacadeAnul() {
        return ejbFacadeAnul;
    }

    public Luna prepareCreate() {
        selected = new Luna();
        selected.setIdLuna(0);
        selected.setPunctLucruidPunctLucru(getPunctLucru());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));
        cal.setTime(lunaSelectata);
        Integer anInt = cal.get(Calendar.YEAR);
        Integer lluna = cal.get(Calendar.MONTH)+1;
        
        
        Anul an = getFacade().getAnul(anInt);
        if (!getFacade().getLuna(an, lluna, getPunctLucru())) {
            selected.setLuna(lluna);
            if (an == null) {
                Anul ann = new Anul();
                ann.setIdAnul(0);
                ann.setAnul(anInt);
                getFacadeAnul().create(ann);
                selected.setAnulidAnul(ann);
            } else {
                selected.setAnulidAnul(an);
            }
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LunaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } else {
            JsfUtil.addErrorMessage("aceasta luna este actualizata deja..");
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LunaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LunaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Luna> getItems() {
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

    public Luna getLuna(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Luna> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Luna> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Luna.class)
    public static class LunaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LunaController controller = (LunaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lunaController");
            return controller.getLuna(getKey(value));
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
            if (object instanceof Luna) {
                Luna o = (Luna) object;
                return getStringKey(o.getIdLuna());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Luna.class.getName()});
                return null;
            }
        }

    }

    public Punctlucru getPunctLucru() {
        FacesContext context = FacesContext.getCurrentInstance();
        MiscarilunaController to = (MiscarilunaController) context.getApplication().getELResolver().
                getValue(context.getELContext(), null, "miscarilunaController");
        return to.getPctLucru();

    }

    public String linkMG1() {
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/faces/jsf/luna/List.xhtml");
        return "output";
    }

}
