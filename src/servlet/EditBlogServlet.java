package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class EditBlogServlet
 * Author: Cun Yang
 */
@WebServlet("/EditBlogServlet")
public class EditBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String blogId = request.getParameter("blogid");
    	DataService ds = new DataService();
    	try {
			Blog blog = ds.getOne(Integer.parseInt(blogId));
			if (blog != null) {
				HttpSession  session = request.getSession();
				session.setAttribute("blog", blog);
//				response.sendRedirect("EditBlog.jsp");
				request.getRequestDispatcher("EditBlog.jsp").forward(request, response);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("blogid"));
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("content"));
		
		
		int blogId = Integer.parseInt(request.getParameter("blogid"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Blog blog = new Blog();
		blog.setId(blogId);
		blog.setTitle(title);
		blog.setContent(content);
		
		DataService ds = new DataService();
		try {
			boolean success = ds.modify(blog);
			System.out.println(success);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Map<String, Boolean> map = new HashMap<>();
			map.put("result", success);
			System.out.println(map);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(map));
//			request.setAttribute("blog", blog);
//			request.getRequestDispatcher("EditBlogSuccess.jsp").forward(request, response);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
