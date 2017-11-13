package com.my.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;



//http://localhost:8080/rest/message/hello
@Path(value="message")
public class BookResteasy {
	private TestRestEasy  testRestEasy; 
	
	@GET
	@Path(value="/{message}")
	//Response

	 public String printRestEasy(@PathParam("message") String message)  
	     {  
		
		return message+testRestEasy.print();
		    
	     }

	public TestRestEasy getTestRestEasy() {
		return testRestEasy;
	}

	public void setTestRestEasy(TestRestEasy testRestEasy) {
		this.testRestEasy = testRestEasy;
	} 


}
