package entJsf;

import ent.Societate;
import ent.UtilizatorLogat;
import ent.Utilizatori;
import entJsf.util.JsfUtil;
import entJsf.util.JsfUtil.PersistAction;
import entBean.UtilizatoriFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import util.Util;
import javax.servlet.http.HttpSession;
import login.UtilizatorConectat;

@Named("utilizatoriController")
@SessionScoped
public class UtilizatoriController implements Serializable {

    @EJB
    private entBean.UtilizatoriFacade ejbFacade;
    private List<Utilizatori> items = null;
    private Utilizatori selected;
    private Utilizatori utilizatorLogat;
    String usser = "";
    String passw = "";
    private static UtilizatorLogat uuu = null;
    private Utilizatori selectedCreate;
    String parola2 = "";
    private Societate societateSelectata = null;
    private boolean selectatUser = true;
    private boolean selectatSocietate = false;
    private boolean selectatLeft = false;

    public UtilizatoriController() {
    }

    public boolean isSelectatUser() {
        return selectatUser;
    }

    public void setSelectatUser(boolean selectatUser) {
        this.selectatUser = selectatUser;
    }

    public boolean isSelectatSocietate() {
        return selectatSocietate;
    }

    public void setSelectatSocietate(boolean selectatSocietate) {
        this.selectatSocietate = selectatSocietate;
    }

    public boolean isSelectatLeft() {
        selectatLeft = selectatUser && selectatSocietate;
        return selectatLeft;
    }

    public void setSelectatLeft(boolean selectatLeft) {
        this.selectatLeft = selectatLeft;
    }

    public Utilizatori getUtilizatorLogat() {
        return utilizatorLogat;
    }

    public void setUtilizatorLogat(Utilizatori utilizatorLogat) {
        this.utilizatorLogat = utilizatorLogat;
    }

    public String getParola2() {
        return parola2;
    }

    public void setParola2(String parola2) {
        this.parola2 = parola2;
    }

    public String getUsser() {
        return usser;
    }

    public UtilizatorLogat getUuu() {
        return uuu;
    }

    public void setUuu(UtilizatorLogat uuu) {
        this.uuu = uuu;
    }

    public void setUsser(String usser) {
        this.usser = usser;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public Utilizatori getSelected() {
        return selected;
    }

    public void setSelected(Utilizatori selected) {
        this.selected = selected;
    }

    public Utilizatori getSelectedCreate() {
        return selectedCreate;
    }

    public void setSelectedCreate(Utilizatori selectedCreate) {
        this.selectedCreate = selectedCreate;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UtilizatoriFacade getFacade() {
        return ejbFacade;
    }

    public Utilizatori prepareCreate() {
        selected = new Utilizatori();
        initializeEmbeddableKey();
        return selected;
    }

    public Utilizatori prepareCreateUser() {

        selectedCreate = new Utilizatori();
        selectedCreate.setIdUtilizatori(0);
        parola2 = "";
        initializeEmbeddableKey();
        return selectedCreate;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UtilizatoriCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void createUser() {
        persistUser(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UtilizatoriCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UtilizatoriUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UtilizatoriDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Utilizatori> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persistUser(PersistAction persistAction, String successMessage) {
        if (selectedCreate != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selectedCreate);
                } else {
                    getFacade().remove(selectedCreate);
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

    public Utilizatori getUtilizatori(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Utilizatori> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Utilizatori> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Utilizatori.class)
    public static class UtilizatoriControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UtilizatoriController controller = (UtilizatoriController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "utilizatoriController");
            return controller.getUtilizatori(getKey(value));
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
            if (object instanceof Utilizatori) {
                Utilizatori o = (Utilizatori) object;
                return getStringKey(o.getIdUtilizatori());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Utilizatori.class.getName()});
                return null;
            }
        }

    }

    public void cancelLogin() {
        usser = "";
    }

    public void loginProject() {
        uuu = getFacade().GetUtilizator(usser, passw);
        //boolean result = UtilizatorConectat.login(usser, passw);
        if (uuu.isLogat()) {

            selectatUser = false;
            usser = uuu.getUsr();
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", usser);
            //linkkSignIn();
            FacesContext
                    .getCurrentInstance()
                    .getApplication()
                    .getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(),
                            null, "/left.xhtml");

            //  return "home";
        } else {
            usser = "";
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Logare Esuata..!",
                            "User,Parola gresita..Incercati din nou..sau Creati User nou.."));
            selectatUser = true;

        }
    }

    public String logout() {
        usser = "";
        societateSelectata = null;
        HttpSession session = Util.getSession();
        session.invalidate();

        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/login.xhtml");

        //return "/login.xhtml"; 
        return "output";
    }

    public String logare() {
        HttpSession session = Util.getSession();
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/logareUser.xhtml");
        return "output";
    }

    public String linkkSignIn() {
        prepareCreateUser();
        //       String utilizat=System.getProperty("user.name");
        FacesContext
                .getCurrentInstance()
                .getApplication()
                .getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(),
                        null, "/faces/jsf/utilizatori/CreateUser.xhtml");
        return "output";
    }

    public Societate getSocietateSelectata() {
        return societateSelectata;
    }

    public void setSocietateSelectata(Societate societateSelectata) {
        this.societateSelectata = societateSelectata;
    }

//    public void changeSocietate(ValueChangeEvent evt) {
//        societateSelectata = ((Societate) evt.getNewValue());
//        
//        FacesContext
//                    .getCurrentInstance()
//                    .getApplication()
//                    .getNavigationHandler()
//                    .handleNavigation(FacesContext.getCurrentInstance(),
//                            null, "/left.xhtml");
//        //selectatSocietate=true;
//        
//        
////        Structura qq=ejbFacade.findStructuriID(struct);
////        selected.setStructura(qq.getNumestructura());
//        int ii = 0;
//    }
}
