package edu.upeu.ventas.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_HOME = "/pages/home.jsp";
	private static final String VIEW_ERROR_LOGIN = "/pages/error_login.jsp";

	Connection conn = null;

	public Connection getConexion() throws SQLException {

		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ventas", "root", "123456");
				System.out.println("Conexion exitosa");
			} catch (ClassNotFoundException e) {
				System.out.println("Conexion fallida");
				e.printStackTrace();
			}
		}

		return conn;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Connection c = getConexion();

			PreparedStatement ps = c
					.prepareStatement("select * from usuario where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				request.getRequestDispatcher(VIEW_HOME).forward(request,
						response);
			} else {
				request.getRequestDispatcher(VIEW_ERROR_LOGIN).forward(request,
						response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
