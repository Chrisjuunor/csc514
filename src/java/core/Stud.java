package core;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.json.JSONObject;

@Path("Stud")
public class Stud {
    JSONObject json;
    student student;

   public Stud() {
       student = new student();
    }

    @Path("test")
    @GET
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String test() {
       json = new JSONObject();
       json.put("status", "OK");
       return json.toString();
    }
    
    @Path("signup")
    @POST
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String create(@FormParam("Firstname") String Firstname, @FormParam("Lastname") String Lastname, 
            @FormParam("Gender") String Gender, @FormParam("Department") String Department, 
            @FormParam("Email") String Email, @FormParam("Password") String Password) {
       json = new JSONObject();
       json.put("status", "OK");
       json.put("student", student.create_account(Firstname, Lastname, Gender, Department, Email, Password));
       return json.toString();
    }
    
    @Path("signin")
    @POST
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public String access(@FormParam("Email") String Email, @FormParam("Password") String Password) {
       json = new JSONObject();
       json.put("status", "OK");
       json.put("login", student.login(Email, Password));
       return json.toString();
    }

}
