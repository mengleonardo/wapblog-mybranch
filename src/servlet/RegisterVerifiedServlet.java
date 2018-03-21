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
@WebServlet("/RegisterVerifiedServlet")
public class RegisterVerifiedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterVerifiedServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("Register.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");

		DataService ds = new DataService();

		try {
			boolean rs = ds.usernameExisted(username);
			Map<String, Integer> map = new HashMap<>();
			int result = -1;
			if (rs == false)
				result = 0;
			else
				result = 1;
			map.put("check", result);
			Gson gson = new Gson();
			out.write(gson.toJson(map));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}