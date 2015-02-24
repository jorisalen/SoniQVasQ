package controller;

	import java.io.IOException;
import java.io.PrintWriter;

	import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	import domain.Facade;
import domain.Member;

public class ErrorController extends HttpServlet{
	

		
		public ErrorController() {
			super();
		}

		
		@Override
		public void init() throws ServletException {
			super.init();
		}

		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			processRequest(request, response);
		}

		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			processRequest(request, response);
		}

		private void processRequest(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String destination = "error.jsp";
		
			RequestDispatcher view = request.getRequestDispatcher(destination);
			
			Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
			Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
				      
				     
			request.setAttribute("status", statusCode);	  
			request.setAttribute("name", throwable.getClass( ).getName());	        
			request.setAttribute("message", throwable.getMessage( ));
				              
				          
				             
			
			view.forward(request, response);
		}

	
}
