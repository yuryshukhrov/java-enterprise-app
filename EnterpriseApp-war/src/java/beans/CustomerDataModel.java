
package beans;

import ejbs.CustomerFacade;
import entities.Customer;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import utilities.GenericSorter;

public class CustomerDataModel extends LazyDataModel<Customer> implements Serializable{

    private CustomerFacade customerFacade;
    // Data Source for binding data to the DataTable
    private List<Customer> customerList;
    // Selected Page size in the DataTable
    private int pageSize;
    // Current row index number
    private int rowIndex;
    // Total row number
    private int rowCount;
   
    public CustomerDataModel(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @Override
    public List<Customer> load(int first, int pageSize, String sortField, 
    SortOrder sortOrder, Map<String,String> filters) {
        customerList = customerFacade.findWithNamedQuery("Customer.findAll", 
                first, first + pageSize);
        // if sort field is not null then we sort the field according to sortfield and sortOrder parameter
        if(sortField != null) {  
            Collections.sort(customerList, 
                    new GenericSorter(sortField, sortOrder));  
        } 
        setRowCount(customerFacade.
                countTotalRecord("Customer.countCustomersTotal"));   
        return customerList;
    }
    
    /**
     * Checks if the row is available
     * @return boolean
     */
    @Override
    public boolean isRowAvailable() {
        if(customerList == null) 
            return false;
        int index = rowIndex % pageSize ; 
        return index >= 0 && index < customerList.size();
    }
    
    /**
     * Gets the customer object's primary key
     * @param user
     * @return Object
     */
    @Override
    public Object getRowKey(Customer customer) {
        return customer.getCustomerId().toString();
    }

    /**
     * Returns the customer object at the specified position in customerList.
     * @return 
     */
    @Override
    public Customer getRowData() {
        if(customerList == null)
            return null;
        int index =  rowIndex % pageSize;
        if(index > customerList.size()){
            return null;
        }
        return customerList.get(index);
    }
    
    /**
     * Returns the customer object that has the row key.
     * @param rowKey
     * @return 
     */
    @Override
    public Customer getRowData(String rowKey) {
        if(customerList == null)
            return null;
       for(Customer customer : customerList) {  
           if(customer.getCustomerId().toString().equals(rowKey))  
           return customer;  
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
        this.customerList = (List<Customer>) list;
    }
    
    /**
     * Returns wrapped data
     * @return
     */
    @Override
    public Object getWrappedData() {
        return customerList;
    }
}
