package beans;

import entities.Student;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import resources.StudentRestClient;

public class StudentManagedBean {

    private StudentRestClient studentClient;
    private LazyDataModel<Student> lazyModel;
    private Student newStudent = new Student();
    private Student selectedStudent = new Student();
    private Student[] selectedStudents;

    public StudentManagedBean() {
        
    }

    @PostConstruct
    public void init() {
        studentClient = new StudentRestClient();
        lazyModel = new StudentDataModel(studentClient);
    }

    public LazyDataModel<Student> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Student> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }


    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public void createStudent() {

        studentClient.create_XML(newStudent);
        newStudent = new Student();
        String msg = "You have successfully added new student!";
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Info Message", msg));

    }

    public void updateStudent(ActionEvent actionEvent) {
        studentClient.edit_XML(selectedStudent);
        selectedStudent = new Student();
        
        String msg = "You have successfully updated the student!";
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Info Message", msg));
    }

    public void deleteStudents(ActionEvent actionEvent) {
        studentClient.deleteItems(getSelectedStudents());
    }

    public Student[] getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(Student[] selectedStudents) {
        this.selectedStudents = selectedStudents;
    }
    
    public void resetNewStudentForm() {
        RequestContext.getCurrentInstance().reset("newStudentForm");
    }
}
