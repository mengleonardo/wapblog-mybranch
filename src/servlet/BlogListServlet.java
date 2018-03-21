package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Blog;
import service.DataService;

/**
 * Servlet implementation class BlogListServlet
 */
@WebServlet("/BlogListServlet")
public class BlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Collect blogs. (Matthew 3/19)
		DataService ds = new DataService();
		// TODO request.getsesion("USER_NAME");

		List<Blog> blogs = null;
		try {

			if (null != request.getSession().getAttribute("user_id")) {

				Integer user_id = Integer.valueOf(String.valueOf(request.getSession().getAttribute("user_id")));
				blogs = ds.getThisUser(user_id);

				request.setAttribute("blogData", blogs);
				RequestDispatcher rd = request.getRequestDispatcher("BlogList.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
				// redirect...

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Send blogs to the blog list page. (Matthew 3/19)

	}

}
