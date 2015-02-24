package controller;

import com.auth0.Auth0User;
import com.auth0.RequestNonceStorage;
import com.auth0.NonceGenerator;
import com.auth0.NonceStorage;

import db.DbException;
import domain.Facade;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class LoginController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4829652472162837959L;
	private final NonceGenerator nonceGenerator = new NonceGenerator();
	private Facade service;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			service = new Facade();
		} catch (DbException e) {
			throw new ServletException(e.getMessage());
		}
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NonceStorage nonceStorage = new RequestNonceStorage(request);
        if (!"/favicon.ico".equals(request.getServletPath())) {
            String nonce = nonceGenerator.generateNonce();
            nonceStorage.setState(nonce);
            request.setAttribute("state", nonce);
            
            HttpSession session = request.getSession();
            
            if(session.getAttribute("user") != null){
            	Auth0User user = (Auth0User) session.getAttribute("user");
            	request.setAttribute("userName", user.getEmail());
            	try {
					service.addMember(user.getName(), user.getProperty("user_id"), "test", user.getEmail(), null);
				} catch (DbException e) {
					throw new ServletException(e.getMessage());
				}
            }
            

            nonceStorage.setState(nonce);
            request.setAttribute("state", nonce);
            
    			
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
