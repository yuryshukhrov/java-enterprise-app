
package beans;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import entities.Student;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import resources.StudentRestClient;
import utilities.GenericSorter;


public class StudentDataModel extends LazyDataModel<Student> implements Serializable{

    private StudentRestClient datasource;
    // Data Source for binding data to the DataTable
    private List<Student> studentList;
    // Selected Page size in the DataTable
    private int pageSize;
    // Current row index number
    private int rowIndex;
    // Total row number
    private int rowCount;
   
    public StudentDataModel(StudentRestClient datasource) {
        this.datasource = datasource;
    }

    @Override
    public List<Student> load(int first, int pageSize, String sortField, 
    SortOrder sortOrder, Map<String, String> filters) {

        ClientResponse response = datasource.findAll_XML(ClientResponse.class);
        GenericType<List<Student>> gType = new GenericType<List<Student>>() {
        };

        studentList = (List<Student>) response.getEntity(gType);
        // if sort field is not null then we sort the field according to sortfield and sortOrder parameter
        if (sortField != null) {
            Collections.sort(studentList, new GenericSorter(sortField, sortOrder));
        }
        setRowCount(Integer.parseInt(datasource.countREST()));
        return studentList;
    }

    /**
     * Checks if the row is available
     * @return boolean
     */
    @Override
    public boolean isRowAvailable() {
        if(studentList == null) 
            return false;
        int index = rowIndex % pageSize ; 
        return index >= 0 && index < studentList.size();
    }
    
    /**
     * Gets the student object's primary key
     * @param user
     * @return Object
     */
    @Override
    public Object getRowKey(Student student) {
        return student.getStudentId().toString();
    }

    /**
     * Returns the student object at the specified position in customerList.
     * @return 
     */
    @Override
    public Student getRowData() {
        if(studentList == null)
            return null;
        int index =  rowIndex % pageSize;
        if(index > studentList.size()){
            return null;
        }
        return studentList.get(index);
    }
    
    /**
     * Returns the student object that has the row key.
     * @param rowKey
     * @return 
     */
    @Override
    public Student getRowData(String rowKey) {
        if(studentList == null)
            return null;
       for(Student student : studentList) {  
           if(student.getStudentId().toString().equals(rowKey))  
           return student;  
       }  
       return null;  
    }
    
    
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns page size
     * @return int
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Returns current row index
     * @return int
     */
    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }
    
    /**
     * Sets row index
     * @param rowIndex
     */
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Sets row count
     * @param rowCount
     */
    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    /**
     * Returns row count
     * @return int
     */
    @Override
    public int getRowCount() {
        return this.rowCount;
    }
     
    /**
     * Sets wrapped data
     * @param list
     */
    @Override
    public void setWrappedData(Object list) {
        this.studentList = (List<Student>) list;
    }
    
    /**
     * Returns wrapped data
     * @return
     */
    @Override
    public Object getWrappedData() {
        return studentList;
    }
}
