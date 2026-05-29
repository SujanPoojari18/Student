package sss;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Demo
 */
@WebServlet("/Demo")
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw=response.getWriter();
		try
		{
			String name=request.getParameter("t1");
			String rollno=request.getParameter("t2");
			String course=request.getParameter("t3");
			String phone=request.getParameter("t4");
			String address=request.getParameter("t5");
			Connection con;
			PreparedStatement ps;
			Statement stm;
			ResultSet res;
			String s;
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con=DriverManager.getConnection("jdbc:ucanaccess://C:/Users/HP/eclipse-workspace/Student/Database1.accdb");
			s="select *from stud where rollno='"+rollno+"'";
			stm=con.createStatement();
			res=stm.executeQuery(s);
			if(res.next())
			{
				pw.println("<h1>The rollno is already exists</h1>");
			}
			else
			{
				ps=con.prepareStatement("insert into stud(name,rollno,course,phone,address) values('"+name+"','"+rollno+"','"+course+"','"+phone+"','"+address+"')");
				ps.executeUpdate();
				pw.println("<h1>Record successfully submitted</h1>");
				
			}
			con.close();
			
		}
		catch(Exception e)
		{
			//pw.println("<h1>Invalid data</h1>");
			e.printStackTrace();
			pw.println(e);
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
