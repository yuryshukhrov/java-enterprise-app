package resources;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import entities.Student;
import java.text.MessageFormat;
import javax.ws.rs.core.MediaType;

public class StudentRestClient {

    private WebResource webResource;
    private Client client;
    private static final String BASE_URI =
            "http://localhost:8080/EnterpriseApp-war/resources";

    public StudentRestClient() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("student");
    }

    public void remove(String id) throws UniformInterfaceException {
        webResource.path(MessageFormat.format("{0}", new Object[]{id})).delete();
    }

    public boolean deleteItems(Student[] students) {
        for (Student student : students) {
            if (student.getStudentId() == 1) {
                continue;
            }
            webResource.path(MessageFormat.format("{0}",
                    new Object[]{student.getStudentId().toString()})).delete();
        }
        return true;
    }

    public String countREST() throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("count");
        return resource.accept(MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T findAll_XML(Class<T> responseType)
            throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findAll_JSON(Class<T> responseType)
            throws UniformInterfaceException {
        WebResource resource = webResource;
        return resource.accept(MediaType.APPLICATION_JSON).get(responseType);
    }

    public void edit_XML(Object requestEntity)
            throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_XML).put(requestEntity);
    }

    public void edit_JSON(Object requestEntity)
            throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_JSON).put(requestEntity);
    }

    public void create_XML(Object requestEntity)
            throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_XML).post(requestEntity);
    }

    public void create_JSON(Object requestEntity)
            throws UniformInterfaceException {
        webResource.type(MediaType.APPLICATION_JSON).post(requestEntity);
    }

    public <T> T findRange_XML(Class<T> responseType, String from, String to)
            throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}/{1}",
                new Object[]{from, to}));
        return resource.accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findRange_JSON(Class<T> responseType, String from, String to)
            throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}/{1}",
                new Object[]{from, to}));
        return resource.accept(MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T find_XML(Class<T> responseType, String id)
            throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}", new Object[]{id}));
        return resource.accept(MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T find_JSON(Class<T> responseType, String id)
            throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(MessageFormat.format("{0}", new Object[]{id}));
        return resource.accept(MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.destroy();
    }
}
