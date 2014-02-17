package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Guest extends User {
	
	private String code;
	private ArmBand band;
	
	public Guest(String name, String code, ArmBand band) {
		super();
		this.name = name;
		this.code = code;
		this.band = band;
	}	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArmBand getBand() {
		return band;
	}
	public void setBand(ArmBand band) {
		this.band = band;
	}
	
	
	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public static boolean login(String username, String password) throws SQLException{

		// Connect to DB
		Connection con = DBConnect.getConnection();
		// Query guest table
		PreparedStatement checkGuests = con.prepareStatement("SELECT * FROM guests WHERE name = '" + username + "' AND password = '" + password + "'");
		// Variable to execute query
		ResultSet guestsResult = checkGuests.executeQuery();
			
		/*  
		 * Check for user occurrence in guest table:
		 */
		int count = 0;
		while(guestsResult.next())
			++count;
		if(count == 1)
			return true;
		
		
		return false;
	}
	
}
