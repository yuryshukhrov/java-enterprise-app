package beans;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import ejbs.CustomerFacade;
import entities.Customer;
import entities.Student;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import resources.StudentRestClient;

public class CustomerManagedBean implements Serializable {

    @EJB
    private CustomerFacade customerFacade;
    private LazyDataModel<Customer> lazyModel; 
    private Customer newCustomer = new Customer();
    private Customer selectedCustomer = new Customer();
    private Customer[] selectedCustomers; 
    private ArrayList<JavaCourse> javaCourses;

    public CustomerManagedBean() { 
        javaCourses = new JavaCourseService().getJavaCourses();
    }
    
    @PostConstruct
    public void init() {
        lazyModel = new CustomerDataModel(customerFacade);
    }
    
    public Customer getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Customer newCustomer) {
        this.newCustomer = newCustomer;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public Customer[] getSelectedCustomers() {
        return selectedCustomers;
    }

    public void setSelectedCustomers(Customer[] selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
    }
    
    public void createCustomer() {

        Customer result =
                customerFacade.find(newCustomer.getCustomerId());

        if (result == null) {
            customerFacade.create(newCustomer);
            newCustomer = new Customer();
            String msg = "You have successfully added new customer!";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Info Message", msg));
        } else {
            newCustomer = new Customer();
            String msg = "Duplicate ID's are not allowed!";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Error Message", msg));

        }
    }
   
    public void updateCustomer(ActionEvent actionEvent) {
        customerFacade.update(selectedCustomer);
        selectedCustomer = new Customer();

        String msg = "You have successfully updated the customer!";
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Info Message", msg));
    }
        
    public void deleteCustomers(ActionEvent actionEvent) {
        customerFacade.deleteItems(selectedCustomers);
    }

    public LazyDataModel<Customer> getLazyModel() {
        return lazyModel;
    }
    
    public void resetNewCustomerForm() {
        RequestContext.getCurrentInstance().reset("newCustomerForm");
    }
     
    public void navigateRestAll() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigateString = "/resources/student";

        try {
            context.getExternalContext().
                    redirect(request.getContextPath() + navigateString);

        } catch (IOException ex) {
            context.addMessage(null,
                    new FacesMessage("Error!", ex.toString()));
        }
    }
    
    public void navigateRestId() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigateString = "/resources/student/";
        List<Student> studentList;
        StudentRestClient datasource = new StudentRestClient();

        ClientResponse response = datasource.findAll_XML(ClientResponse.class);
        GenericType<List<Student>> gType = new GenericType<List<Student>>() {
        };

        studentList = (List<Student>) response.getEntity(gType);
        navigateString += studentList.get(0).getStudentId();
        
         try {
            context.getExternalContext().
                    redirect(request.getContextPath() + navigateString);

        } catch (IOException ex) {
            context.addMessage(null,
                    new FacesMessage("Error!", ex.toString()));
        }
    }
    
    public void navigateRestCount() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigateString = "/resources/student/count";

        try {
            context.getExternalContext().
                    redirect(request.getContextPath() + navigateString);

        } catch (IOException ex) {
            context.addMessage(null,
                    new FacesMessage("Error!", ex.toString()));
        }
    }

    public ArrayList<JavaCourse> getJavaCourses() {
        return javaCourses;
    }
}
