package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/updelivery?user=root";
	private String user = "root";
	private String password = "";
	
	public Connection conectar(){
		Connection con = null;	
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public void fecharBanco(Connection con) {
	    try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
	}
	
	
	
	
	
}
