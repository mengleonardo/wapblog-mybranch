package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Blog;
import service.DataService;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/LoginVerifiedServlet")
public class LoginVerifiedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginVerifiedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		 System.out.println("input"+username + password);
		DataService ds = new DataService();
		int id;
		Map<String, Integer> map = new HashMap<>();
	   try{id =ds.login(username, password);
          if( id != -888)
          {
				HttpSession session = request.getSession();
				session.setAttribute("user_id", id);
        	  
        	  List<Blog> blogs = ds.getAll();
        	  request.setAttribute("blogData", blogs);
        	  
		      		int result=1;
		      		map.put("check", result);
		      		Gson gson = new Gson();
		      		out.write(gson.toJson(map));
        	  
          }
          else
          {
        	  //response.getWriter().write("login failed");
        	  int result=0;
	      		map.put("check", result);
	      		Gson gson = new Gson();
	      		out.write(gson.toJson(map));
        	  /*RequestDispatcher rd = request.getRequestDispatcher("login.html");
      		  rd.forward(request, response);*/
          }
		System.out.println("login succeeded"+id );
	   }
	   catch(SQLException e)
	   {
		   e.printStackTrace();
	   }
	   
	   
	}

}