package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.Auth0User;
import com.auth0.NonceGenerator;
import com.auth0.NonceStorage;
import com.auth0.RequestNonceStorage;

import db.DbException;
import domain.Facade;
import domain.Member;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Member currentUser;
	private String query = "";
    private final NonceGenerator nonceGenerator = new NonceGenerator();
	public Controller() {
		super();
	}
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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DbException e) {
			throw new ServletException(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DbException e) {
			throw new ServletException(e.getMessage());
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException , DbException{
        
		NonceStorage nonceStorage = new RequestNonceStorage(request);

		String destination;
		String action = request.getParameter("action");
		
		if(action.equals("login")){
			destination =  login(request,response);
		} else if (action.equals("search")){
			destination =  search(request,response);
		} else if( action.equals("upvote")){
			destination =  upvote(request,response);
		} else if( action.equals("downvote")){
			destination =  downvote(request,response);
		}else if( action.equals("comment")){
			destination =  comment(request,response);
		}else if(action.equals("addComment")){
			destination = addComment(request,response);
		}else if(action.equals("sharePlaylist")){
			destination = sharePlaylist(request,response);
		}else if(action.equals("addToPlaylist")){
			destination = addToPlaylist(request,response);
		}else if(action.equals("test")){
			destination = test(request,response);
		}else if(action.equals("logout")){
			destination = logout(request,response);
		}else {
			destination = "index.jsp";
		}
		
		String nonce = nonceGenerator.generateNonce();
        nonceStorage.setState(nonce);
        request.setAttribute("state", nonce);
        
			
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}

	private String test(HttpServletRequest request, HttpServletResponse response) throws DbException {
		
		Member m = service.getMember("1");
		request.setAttribute("email", m.getEmail());
		service.addMember("naam", "vnaam", "pw", "email", null);
		return "test.jsp";
	}

	private String logout(HttpServletRequest request,
			HttpServletResponse response) {
	
		 HttpSession session = request.getSession(false);
		 if (session != null) {
		     session.invalidate();
		 }
		 
		 return "index.jsp";
	}

	private String addToPlaylist(HttpServletRequest request,
			HttpServletResponse response) throws DbException {
		
		int playlistId= Integer.parseInt(request.getParameter("playlistSelect"));
		int recordId = Integer.parseInt(request.getParameter("recordId"));

		service.addToPlayList(recordId, playlistId);
		
		return "search.jsp";
	}

	private String sharePlaylist(HttpServletRequest request,
			HttpServletResponse response) throws DbException{
		
		String naam = request.getParameter("usernaam");
		int playListId = Integer.parseInt(request.getParameter("playListId"));
		
		service.sharePlayList(naam, playListId);
		
		request.setAttribute("query", query);
		request.setAttribute("records", service.search(query));
		
		//veranderen als er een db met userId achter komt te staan
		request.setAttribute("playlist", service.getPlayLists(0));
		return "search.jsp";
	}

	private String addComment(HttpServletRequest request,
			HttpServletResponse response) throws DbException{
		
		try{
			int recordId = Integer.parseInt(request.getParameter("id"));
			String comment = request.getParameter("comment");
			
			HttpSession session = request.getSession();
	    	Auth0User user = (Auth0User) session.getAttribute("user");
	
			if(user.getUserId() != null){
				service.addComment(comment, user.getUserId(), recordId);
			} else {
			}
			
			request.setAttribute("recordId", recordId);
			request.setAttribute("comments", service.getComments(recordId));
			
		} catch(Exception e){
			throw new DbException(e.getMessage());
		}
		return "comment.jsp";
	}

	private String comment(HttpServletRequest request,
			HttpServletResponse response)throws DbException {
		int recordId = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("recordId", recordId);
		request.setAttribute("comments", service.getComments(recordId));
		
		return "comment.jsp";
	}

	private String downvote(HttpServletRequest request,
			HttpServletResponse response) throws DbException{
		
		int recordId = Integer.parseInt(request.getParameter("id"));
		service.downvote(recordId);
		return search(request,response);
		
	}

	private String upvote(HttpServletRequest request,
			HttpServletResponse response) throws DbException{
		
		int recordId = Integer.parseInt(request.getParameter("id"));
		service.upvote(recordId);
		return search(request,response);
		
	}

	private String search(HttpServletRequest request,
			HttpServletResponse response)throws DbException {
		
		query = request.getParameter("query");
		
		request.setAttribute("query", query);
		

		request.setAttribute("records", service.search(query));
		
		//veranderen als er een db met userId achter komt te staan
		request.setAttribute("playlist", service.getPlayLists(0));
		return "search.jsp";
		
	}
	
	
	private String login(HttpServletRequest request,
			HttpServletResponse response) throws DbException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		currentUser = service.login(email, password);
		
		if(currentUser != null){
			request.setAttribute("userNaam", currentUser.getNaam());
			return "index.jsp";
		} else {
			return "login.jsp";
		}
	}
	
}
