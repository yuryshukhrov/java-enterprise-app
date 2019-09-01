
package beans;

import java.text.MessageFormat;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.layout.LayoutUnit;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ResizeEvent;
import org.primefaces.event.ToggleEvent;

public class LayoutBean {

    public void handleClose(CloseEvent event) {
        FacesMessage message = 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Unit Closed", 
                MessageFormat.format("Position:'{0}'", 
                ((LayoutUnit) event.getComponent()).getPosition()));

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                MessageFormat.format("{0} toggled", 
                ((LayoutUnit) event.getComponent()).getPosition()), 
                MessageFormat.format("Status:{0}", 
                event.getVisibility().name()));

        addMessage(message);
    }

    public void handleResize(ResizeEvent event) {
        FacesMessage message = 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                MessageFormat.format("{0} resized", 
                ((LayoutUnit) event.getComponent()).getPosition()), 
                MessageFormat.format("Width:{0}, Height:{1}", 
                event.getWidth(), event.getHeight()));

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
