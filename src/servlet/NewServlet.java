package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Blog;
import service.DataService;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/NewServlet")
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("new-blog.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		DataService ds = new DataService();
		Blog blog = new Blog();

		Integer user_id = Integer.valueOf(String.valueOf(request.getSession().getAttribute("user_id")));
		blog.setUserId(user_id);
		blog.setTitle(title);
		blog.setContent(content);
		try {
			int newBlogId = ds.add(blog);
			System.out.println(newBlogId);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Map<String, Integer> map = new HashMap<>();
			map.put("newBlogId", newBlogId);
			System.out.println(map);
			Gson gson = new Gson();
			out.write(gson.toJson(map));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
