package com.services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.catalina.filters.CorsFilter;

import com.model.facade.EmployeeFacade;
import com.model.vo.EmployeeVO;

@Path("/res")
public class EmployeeService {
	
	
	@POST
	@Path("/add")
	@Consumes( MediaType.APPLICATION_JSON)
	public String addEmployee(EmployeeVO emVo) throws SQLException {
		EmployeeFacade employeeFacade = new EmployeeFacade();
	//EmployeeVO employee = new EmployeeVO();
	
/*	employee.setFirstName(firstName);
	employee.setLastName(lastName);
	employee.setEmail(email);
	employee.setAddress(address);
	employee.setId(-1);
	
*/
		System.out.println(emVo);
	boolean result = employeeFacade.save(emVo);
	String responce = "Error";
		if(result) {
			responce =  " Employee successfully Added !!! ";	
		}
		Response.ok(responce).header("Access-Control-Allow-Origin", CorsFilter.DEFAULT_ALLOWED_ORIGINS).build();
		return responce;
	}
	
	
	@GET
	@Path("/employeelist")
	@Produces(MediaType.APPLICATION_JSON)
	public Response employees() {
		EmployeeFacade employeeFacade = new EmployeeFacade();		
		List<EmployeeVO> list =  employeeFacade.findAll();
		

	GenericEntity<List<EmployeeVO>> generic = new GenericEntity<List<EmployeeVO>>(list){};
		
		return Response.ok(generic).header("Access-Control-Allow-Origin", CorsFilter.DEFAULT_ALLOWED_ORIGINS).build();
		
	}
	
	
	@GET
	@Path("/employeelist/{empID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmployeeVO> employees(@PathParam("empID") int id) {
		EmployeeFacade employeeFacade = new EmployeeFacade();
		
		return employeeFacade.findById(id);
	}
	
	
	@GET
	@Path("/employeelist/name/{empName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmployeeVO> employees(@PathParam("empName") String name) {
		EmployeeFacade employeeFacade = new EmployeeFacade();
		return employeeFacade.findByName(name);
	}

	
	@DELETE
	@Path("/employeelist/{empID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeEmployees(@PathParam("empID") int id) {
		EmployeeFacade employeeFacade = new EmployeeFacade();
		boolean result = employeeFacade.removeById(id);
		String responce = "Error";
		if(result) {
			responce =  " Employee successfully Delete !!! ";	
		}
		return responce;
	}

}
