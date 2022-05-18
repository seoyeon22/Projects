package advisor;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseBean2 {
	private static DatabaseBean2 instance = new DatabaseBean2();
	
	private DatabaseBean2() {}
	
	public static DatabaseBean2 getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		String jdbcUrl ="jdbc:mysql://localhost:3306/empldb";
		String dbUser = "root";
		String dbPass = "seoyeon";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
		return conn;
	}
	
	public void insertEmployee(EmployeeBean employee) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String query = "insert into employee values(?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,employee.getEmplid());
			pstmt.setString(2,employee.getName());
			pstmt.setString(3,employee.getAddress());
			pstmt.setInt(4,employee.getSsn());
			pstmt.executeUpdate();
			
			query ="insert into assignment values(?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,employee.getEmplid());
			pstmt.setInt(2,employee.getJobid());
			pstmt.setString(3,employee.getStartdate());
			pstmt.setString(4, null);
			pstmt.executeUpdate();

			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int userCheck(int id, String passwd) {
		Connection conn=null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int x = -1;
		try {
			conn = getConnection();
			String query = "select ssn from employee where emplid = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			while(rs.next()){
				String dbpasswd = rs.getString(1); 
				if(passwd.equals(dbpasswd))
					x = 1;
				else
					x = 0;
			}
			
			if(rs !=null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
		return(x);
	}
	
	public ArrayList<EmployeeBean> getEmployees(String dept) {
		Connection conn=null;	
		ArrayList<EmployeeBean> employeeArray = new ArrayList<EmployeeBean>(10);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String query = "select employee.emplid, name";
			query += " from assignment, employee, job";
			query += " where assignment.jobid=job.jobid";
			query += " and assignment.emplid=employee.emplid";
			query += " and termdate is null";
			query += " and dept=?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dept);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				EmployeeBean employee = new EmployeeBean();
				employee.setEmplid(rs.getInt(1));
				employee.setName(rs.getString(2));
				employeeArray.add(employee);
				
			}
			if(rs !=null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
		return employeeArray;
	}
	
	public EmployeeBean getEmployee(int emplid) {
		Connection conn=null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		EmployeeBean employee = new EmployeeBean();

		try {
			conn = getConnection();
			String query = "select name, dept";
			query += " from assignment, employee, job";
			query += " where assignment.jobid = job.jobid";
			query += " and assignment.emplid = employee.emplid";
			query += " and termdate is null";
			query += " and employee.emplid =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emplid);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				employee.setName(rs.getString(1));
				employee.setDept(rs.getString(2));
			}
			
			if(rs !=null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
		return employee;
	}
}
