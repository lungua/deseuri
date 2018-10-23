package entJsf;

import ent.Clasificaredin;
import ent.Coddeseu;
import ent.Deseuripunctlucru;
import ent.Punctlucru;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.DeseuripunctlucruFacade;

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
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.SelectEvent;

@Named("deseuripunctlucruController")
@SessionScoped
public class DeseuripunctlucruController implements Serializable {

    @EJB
    private entBean.DeseuripunctlucruFacade ejbFacade;
    private List<Deseuripunctlucru> items = null;
    private List<Deseuripunctlucru> itemsPL = null;

    private Deseuripunctlucru selected;
    private Punctlucru pctLucru;
    private Clasificaredin clasificareDin;
    private Coddeseu selectCodDeseu;

    public DeseuripunctlucruController() {
    }

    public Deseuripunctlucru getSelected() {
        return selected;
    }

    public void setSelected(Deseuripunctlucru selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DeseuripunctlucruFacade getFacade() {

        return ejbFacade;
    }

    public Deseuripunctlucru prepareCreate() {
        selected = new Deseuripunctlucru();
        selected.setCodDeseuidCodDeseu(selectCodDeseu);
        selected.setClasificareDinidClasificareDin(clasificareDin);
        selected.setPunctLucruidPunctLucru(pctLucru);
        selected.setIdDeseuri(0);
        selected.setPericulozitate(false);

        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        boolean var1 = true;
        if (selectCodDeseu == null) {
            JsfUtil.addErrorMessage("nu ati selectat COD DESEU... selectati apoi reveniti..");
            var1 = false;
        }
        if (clasificareDin == null) {
            JsfUtil.addErrorMessage("nu ati selectat Cladificare... selectati apoi reveniti..");
            var1 = false;
        }
        if (pctLucru == null) {
            JsfUtil.addErrorMessage("nu ati selectat PUNCT LUCRU... selectati apoi reveniti..");
            var1 = false;
        }
        if (var1) {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DeseuripunctlucruCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.

            }
            var1 = true;
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DeseuripunctlucruUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DeseuripunctlucruDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Deseuripunctlucru> getItemsPL() {
        if (pctLucru != null && clasificareDin != null) {
            itemsPL = getFacade().getCodDeseu(pctLucru, clasificareDin);

        } else {
            itemsPL = null;
        }

        return itemsPL;
    }

    public void setItemsPL(List<Deseuripunctlucru> itemsPL) {
        this.itemsPL = itemsPL;
    }

    public List<Deseuripunctlucru> getItems() {
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

    public Deseuripunctlucru getDeseuripunctlucru(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Deseuripunctlucru> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Deseuripunctlucru> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Deseuripunctlucru.class)
    public static class DeseuripunctlucruControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DeseuripunctlucruController controller = (DeseuripunctlucruController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "deseuripunctlucruController");
            return controller.getDeseuripunctlucru(getKey(value));
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
            if (object instanceof Deseuripunctlucru) {
                Deseuripunctlucru o = (Deseuripunctlucru) object;
                return getStringKey(o.getIdDeseuri());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Deseuripunctlucru.class.getName()});
                return null;
            }
        }

    }

    public String linkDeses() {
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/faces/jsf/deseuripunctlucru/ListDeseuriPunctLucru.xhtml");
        return "output";
    }

    public void changePunctLucru(ValueChangeEvent evt) {
        pctLucru = ((Punctlucru) evt.getNewValue());
        if ( clasificareDin != null) {
            itemsPL = getFacade().getCodDeseu(pctLucru, clasificareDin);

        } else {
            itemsPL = null;
        }
        //System.out.println(((Tipdocumentintrare)evt.getNewValue()).getIdTipObiecte()+"-"+((Tipobiecte)evt.getNewValue()).getNumeTipObiect());

    }

    public void changeClasificaredin(ValueChangeEvent evt) {
        clasificareDin = ((Clasificaredin) evt.getNewValue());
        //System.out.println(((Tipdocumentintrare)evt.getNewValue()).getIdTipObiecte()+"-"+((Tipobiecte)evt.getNewValue()).getNumeTipObiect());
        if (pctLucru != null ) {
            itemsPL = getFacade().getCodDeseu(pctLucru, clasificareDin);

        } else {
            itemsPL = null;
        }
    }

    public void onRowSelect(SelectEvent evt) {
        selectCodDeseu = (Coddeseu) evt.getObject();

    }

    public Punctlucru getPctLucru() {
        if ( clasificareDin != null) {
            itemsPL = getFacade().getCodDeseu(pctLucru, clasificareDin);

        } else {
            itemsPL = null;
        }
        
        return pctLucru;
    }

    public void setPctLucru(Punctlucru pctLucru) {
        this.pctLucru = pctLucru;
    }

    public Clasificaredin getClasificareDin() {
        if (pctLucru != null ) {
            itemsPL = getFacade().getCodDeseu(pctLucru, clasificareDin);

        } else {
            itemsPL = null;
        }
        return clasificareDin;
    }

    public void setClasificareDin(Clasificaredin clasificareDin) {
        this.clasificareDin = clasificareDin;
    }
    
    
}
