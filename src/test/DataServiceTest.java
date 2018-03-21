package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.*;

import org.junit.jupiter.api.Test;

import model.Blog;
import service.DataService;;

class DataServiceTest {
	
	DataService ds = new DataService();
	@Test
	void getAllTest() {
		try {
			List<Blog> blogs = ds.getAll();
			System.out.println(blogs.get(0).getTitle());
			assertEquals(1, blogs.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void getOneTest() {
		try {
			Blog blog = ds.getOne(2001);
			assertEquals(blog.getTitle(), "This is my First Blog");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void modifyBlogTest() {
		Blog blog;
		try {
			blog = ds.getOne(2001);
			blog.setContent("HI");
			ds.modify(blog);
			blog = ds.getOne(2001);
			assertEquals(blog.getContent(), "HI");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void deleteBlogTest() {
		try {
			boolean success = ds.delete(2001);
			assertEquals(success, true);
			assertEquals(ds.getAll().size(), 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void addBlogTest() {
		try {
			int size = ds.getAll().size();
			Blog blog = new Blog();
			blog.setTitle("This is my Second Blog");
			blog.setContent("Type here to search");
			blog.setUserId(1001);
			int blogId = ds.add(blog);
			assertEquals(blogId > 0, true);
			assertEquals(ds.getAll().size(), size + 1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
