package beans;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginManagedBean {

    private String userName;
    private String passWord;

    public LoginManagedBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void login(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) 
                context.getExternalContext().getRequest();
        FacesMessage msg = null;
        String navigateString = "/admin.jsf";

        if (userName != null && userName.equals("admin")
                && passWord != null && passWord.equals("admin")) {
            context.getExternalContext().
                    getSessionMap().put("userName", userName);

            try {
                context.getExternalContext().
                        redirect(request.getContextPath() + navigateString);

            } catch (IOException ex) {
                context.addMessage(null,
                        new FacesMessage("Error!", ex.toString()));
            }

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Login Error", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void logout() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);

        if (session != null) {
            session.invalidate();
        }
        FacesContext.getCurrentInstance().getApplication().
                getNavigationHandler().handleNavigation(FacesContext.
                getCurrentInstance(), null, "/login.jsf?faces-redirect=true");
    }
}
