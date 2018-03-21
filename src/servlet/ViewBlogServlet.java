package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Blog;
import service.DataService;

/**
 * Servlet implementation class ViewBlogServlet
 */
@WebServlet("/ViewBlogServlet")
public class ViewBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String blogIdStr = request.getParameter("blogid");
		if (!blogIdStr.matches("\\d+")) {
			response.getWriter().append("Error blog id: ").append(request.getContextPath());
			return;
		}
		
		int blogId = Integer.parseInt(blogIdStr);
        HttpSession session = request.getSession();
   	    if (session.getAttribute("user_id") == null )
   	    {
   	    	request.getRequestDispatcher("login.html").forward(request, response);
   	    	return;
   	    }
		DataService ds = new DataService();
		try {
			Blog blog = ds.getOne(blogId);
			if (0==blog.getId()) {
				//didn't find blog related to this blogid
				request.getRequestDispatcher("list").forward(request, response);
				return;
			}
			else {
				int id;
				if (blog.getUserId() == Integer.parseInt((session.getAttribute("user_id").toString() )))
				{
					request.setAttribute("blog", blog);
					request.getRequestDispatcher("ViewBlog.jsp").forward(request, response);
					return;
				}
				else
				{
					request.getRequestDispatcher("list").forward(request, response);
				}
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
