package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Blog;
import com.google.gson.*;

// The Service for Blog
public class DataService {

	private Connection conn;

	public DataService() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection("jdbc:mysql://45.77.144.61:3306/blogdb?useSSL=false", "cun",
					"123456");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// select id from user where username =? and pwd=?
	// Fetch all the blogs at once.
	public int login(String username, String password) throws SQLException {
		String SQL = " SELECT id from user where username = ? and pwd=?";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		int id = -888;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		return id;
	}

	public int register(String username, String email, String password) throws SQLException {
		String SQL = "INSERT INTO user(email, username, pwd) VALUES(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, email);
		pstmt.setString(2, username);
		pstmt.setString(3, password);
		System.out.println(pstmt);
		pstmt.executeUpdate();
		ResultSet keys = pstmt.getGeneratedKeys();
		keys.next();
		return keys.getInt(1);
	}

	public boolean usernameExisted(String username) throws SQLException {
		String SQL = " SELECT id from user where username =?";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == false)
			return false;
		else
			return true;

	}

	public List<Blog> getAll() throws SQLException {
		List<Blog> blogs = new ArrayList<>();
		Statement stmt = conn.createStatement();
		String strSelect = "select * from blog";
		ResultSet rset = stmt.executeQuery(strSelect);
		while (rset.next()) {
			Blog temp = new Blog();
			int blogId = rset.getInt("id");
			String title = rset.getString("title");
			String content = rset.getString("content");
			Date createdDate = rset.getDate("create_time");
			temp.setId(blogId);
			temp.setTitle(title);
			temp.setContent(content);
			temp.setCreatedDate(createdDate);
			blogs.add(temp);
		}
		return blogs;
	}

	public List<Blog> getThisUser(int user_id) throws SQLException {
		List<Blog> blogs = new ArrayList<>();
		String SQL = " SELECT * from blog where user_id =" + user_id;
		PreparedStatement ps = conn.prepareStatement(SQL);
		// int user_id =
		// Integer.parseInt(request.getSession().getAttribute("user_id").toString());
		// ps.setInt(1, user_id);
		ResultSet rset = ps.executeQuery();
		while (rset.next()) {
			Blog temp = new Blog();
			int blogId = rset.getInt("id");
			String title = rset.getString("title");
			String content = rset.getString("content");
			Date createdDate = rset.getDate("create_time");
			temp.setId(blogId);
			temp.setTitle(title);
			temp.setContent(content);
			temp.setCreatedDate(createdDate);
			blogs.add(temp);
		}
		return blogs;
	}

	public String getAllJson() throws SQLException {
		return new Gson().toJson(this.getAll());
	}

	// Fetch one blog by id.
	public Blog getOne(int id) throws SQLException {
		Blog blog = new Blog();
		Statement stmt = conn.createStatement();
		String strSelect = "select * from blog where id = " + id;
		ResultSet rset = stmt.executeQuery(strSelect);
		if (rset.next()) {
			int blogId = rset.getInt("id");
			int user_id = rset.getInt("user_id");
			String title = rset.getString("title");
			String content = rset.getString("content");
			Date createdDate = rset.getDate("create_time");
			blog.setId(blogId);
			blog.setTitle(title);
			blog.setContent(content);
			blog.setCreatedDate(createdDate);
			blog.setUserId(user_id);
		}
		return blog;
	}

	public String getOneJson(int id) throws SQLException {
		return new Gson().toJson(this.getOne(id));
	}

	// Add a new blog.
	public int add(Blog blog) throws SQLException {
		String SQL = "INSERT INTO blog(title, content, user_id) VALUES(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, blog.getTitle());
		pstmt.setString(2, blog.getContent());
		pstmt.setInt(3, blog.getUserId());
		System.out.println(pstmt);
		pstmt.executeUpdate();
		ResultSet keys = pstmt.getGeneratedKeys();
		keys.next();
		return keys.getInt(1);
	}

	// Delete a blog by id.
	public boolean delete(int id) throws SQLException {
		String SQL = "DELETE FROM blog WHERE id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, id);
		System.out.println(pstmt);
		pstmt.executeUpdate();
		return true;
	}

	// Modify a blog.
	public boolean modify(Blog blog) throws SQLException {
		String SQL = "UPDATE blog SET title = ?, content = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, blog.getTitle());
		pstmt.setString(2, blog.getContent());
		pstmt.setInt(3, blog.getId());
		System.out.println(pstmt);
		pstmt.executeUpdate();
		return true;
	}

	// TODO login varify 1.String username ,2.password return count(*) 1

}
