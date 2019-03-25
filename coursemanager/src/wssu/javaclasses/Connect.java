package wssu.javaclasses;

import java.sql.*;
/* 
 * You can donwload MS SQL Server JDBC dirver at 
 * http://www.microsoft.com/downloads/en/details.aspx?FamilyID=a737000d-68d0-4531-b65d-da0f2a735707&displaylang=en
 * 
 * Add sqljdbc4.jar to either
 * Eclipse External Archives, or
 * BlueJ Libraries from Preferences
 * 
 * Copy sqljdbc_auth.dll to the same folder with your java file
 * 
 * Refer to the following MSDN manual for more information
 * on connecting to SQL Server usin JDBC
 * http://msdn.microsoft.com/en-us/library/ms378672.aspx
 * 
 */
public class Connect
{
     // Declare the JDBC objects.
     private Connection connection = null;
     private Statement stmt = null;
     private ResultSet rs = null;

    /**
     * Constructor for objects of class Connect
     */
    public Connect()throws Exception {
        // Get connection
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
        connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=db_course_management_system;integratedSecurity=true");
        //connection = DriverManager.getConnection("jdbc:sqlserver://CSCSQL;user=cst6306;password=cst6306");
        //connection = DriverManager.getConnection("jdbc:sqlserver://152.12.24.165;databaseName=CST6306;user=cst6306;password=cst6306");
        if (connection != null) {
            System.out.println();
            System.out.println("Successfully connected to Database");
        }
    }
    Student student=null;
    Faculty faculty=null;
    public boolean validateUser(String username, String password, int type) throws SQLException {
    	if(type==0) {
	    	student=new Student(connection, username, password);
	        return (student.validate());
        }
    	else {
    		faculty=new Faculty(connection, username, password);
            if(faculty.validate())
            	return true;
    	}
    	return false;
    }
    public void InsertToStudent(String fname, String mname, String lname, String major, String level, int byear, String username, String password) throws SQLException{
    	String sql="insert into student(fname, mname, lname, major, level, byear)"
    			+ "values('"+fname+"',"+"'"+mname+"',"+"'"+lname+"',"+"'"+major+"'"+",'"+level+"',"+"'"+byear+"');";
	    	PreparedStatement ps = connection.prepareStatement(sql,
	    	        Statement.RETURN_GENERATED_KEYS);
	    	ps.execute();
	    	 
	    	ResultSet r = ps.getGeneratedKeys();
	    	int generatedKey = 0;
	    	if (r.next()) {
	    	    generatedKey = r.getInt(1);
	    	}
	    	InsertToStudentCredential(generatedKey, username, password);
		return;
    }
    public void InsertToStudentCredential(int fk, String username, String password) throws SQLException {
    	String sql="insert into StudentCredential(sid, username, password)"
    			+ "values("+fk+","+"'"+username+"',"+"'"+password+"');";
		stmt=connection.createStatement();
		stmt.execute(sql);
		return;
    }
    
    public void InsertToFaculty(String fname, String mname, String lname, String department, String username, String password) throws SQLException {
    	String sql="insert into Faculty(fname, mname, lname, department)"
    			+ "values('"+fname+"',"+"'"+mname+"',"+"'"+lname+"',"+"'"+department+"');";
    	PreparedStatement ps = connection.prepareStatement(sql,
    	        Statement.RETURN_GENERATED_KEYS);
    	ps.execute();
    	 
    	ResultSet r = ps.getGeneratedKeys();
    	int generatedKey = 0;
    	if (r.next()) {
    	    generatedKey = r.getInt(1);
    	}
    	InsertToFacultyCredential(generatedKey, username, password);
		return;
    }
    public void InsertToFacultyCredential(int fk, String username, String password) throws SQLException {
    	String sql="insert into FacultyCredential(fid, username, password)"
    			+ "values("+fk+","+"'"+username+"',"+"'"+password+"');";
    
		stmt=connection.createStatement();
		stmt.execute(sql);
		return;
    }

    public void closeCon() throws Exception
    {
        connection.close();
    }

    
} // End of Connect