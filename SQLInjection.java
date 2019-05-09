import java.sql.*;

public class SQLInjection {
	public static void main (String args[]) {
		Connection conn = null;
		//add
		try {
			String userName = args [0];
			String passwd   = args [1];
			//
			String name = validate(userName);
			
			String query = "select uname, passwd from users where uname = '"+name+"'";
			conn = DriverManager.getConnection ("jdbc:odbc:logistics", "admin", "letmein");
			Statement stmnt = conn.createStatement ();
			ResultSet rs = stmnt.executeQuery (query);
			while ( rs.next() ) {
			}
			rs.close ();
			stmnt.close ();
			conn.close ();
		} catch (SQLException err) {
			err.printStackTrace ();
		}
	}
	
	public static String validate(String name) {
		if ( name.matches("[a-z][0-9a-zA-Z]{0,32}") ) {
			return name;
		} else {
			throw IllegalArgumentException("Invalid name");
		}
	}
}
