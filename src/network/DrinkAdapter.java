package network;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import model.DBConnect;
import model.Guest;
import model.User;

import org.apache.log4j.Logger;

public  class DrinkAdapter{
	
	private static Logger log = Logger.getLogger(DrinkAdapter.class);
	
	/**
	 * For manager/staff.
	 * @return DefaultTableModel
	 */
	public static DefaultTableModel getTableModel(){
		
		Vector<Object> drinks = new Vector<Object>(); // to return
		Vector<Object> drinkRow = new Vector<Object>(); // a row
		Vector<Object> columns = new Vector<Object>(); // columns
		
		int i = 0; // reusable iterator
		
		try {
			Connection conn = DBConnect.getConnection();
			
			ResultSet driSet = conn.prepareStatement("SELECT * from drinks").executeQuery(), typeSet;
			ResultSetMetaData meta = driSet.getMetaData();
	        int columnCount = meta.getColumnCount();
	        
	        //store column names  
	        for (i = 2; i <= columnCount; i++) {
	            columns.add((meta.getColumnName(i).substring(0, 1).toUpperCase() + meta.getColumnName(i).substring(1)));
	            
	        }
	        columns.add("Selected");
	        log.info("Getting drink table model");
			while(driSet.next()){
				// get row content (drink)
				drinkRow = new Vector<Object>();
				{
					i = 0;
					// add name and price
					drinkRow.addElement( (String) driSet.getObject(i+2).toString() );
					drinkRow.addElement( (Double) Double.parseDouble(driSet.getObject(i+3).toString()) );
					
					
					// fetch type string from drink_types table
					typeSet = conn.prepareStatement("SELECT * from drink_types WHERE id = " + Integer.parseInt(driSet.getObject(i+4).toString())).executeQuery();
					while(typeSet.next())
						drinkRow.addElement( (String) typeSet.getObject(i+2).toString() );
					drinkRow.addElement(new Boolean(null));
					typeSet.close();
				}
				
				drinks.addElement(drinkRow);
			}
			
			// close connection
			conn.close();
		}catch(SQLException e){
			log.error("SQLException: "+e.getCause());
		}catch(NumberFormatException e){
			log.error("NumberFormatException: "+e.getCause());
		}finally{
			// keep going...
		}
		
		DefaultTableModel tm = new DefaultTableModel(drinks, columns);
		return (tm);
	}
	
	/**
	 * For guest.
	 * @param guest
	 * @return
	 */
	public static DefaultTableModel getTableModelForGuest(Guest guest){
		
		Vector<Object> drinks = new Vector<Object>(); // to return
		Vector<Object> drinkRow = new Vector<Object>(); // a row
		Vector<Object> columns = new Vector<Object>(); // columns
		
		int i = 0; // reusable iterator
		
		try {
			Connection conn = DBConnect.getConnection();
			
			ResultSet driSet, typeSet;
			if(guest.getBand().getColour() == 2){ 	// orange: everything
				driSet = conn.prepareStatement("SELECT * from drinks").executeQuery();
			}else{	// other: non-alcoholic
				driSet = conn.prepareStatement("SELECT * from drinks where type = " + 2).executeQuery();
			}
			ResultSetMetaData meta = driSet.getMetaData();
	        int columnCount = meta.getColumnCount();
	        
	        //store column names  
	        for (i = 2; i <= columnCount; i++) {
	            columns.add((meta.getColumnName(i).substring(0, 1).toUpperCase() + meta.getColumnName(i).substring(1)));
	            
	        }
	        columns.add("Selected");
	        log.info("Getting drink table model");
			while(driSet.next()){
				// get row content (drink)
				drinkRow = new Vector<Object>();
				{
					i = 0;
					// add name and price
					drinkRow.addElement( (String) driSet.getObject(i+2).toString() );
					drinkRow.addElement( (Double) Double.parseDouble(driSet.getObject(i+3).toString()) );
					
					
					// fetch type string from drink_types table
					typeSet = conn.prepareStatement("SELECT * from drink_types WHERE id = " + Integer.parseInt(driSet.getObject(i+4).toString())).executeQuery();
					while(typeSet.next())
						drinkRow.addElement( (String) typeSet.getObject(i+2).toString() );
					drinkRow.addElement(new Boolean(null));
					typeSet.close();
				}
				
				drinks.addElement(drinkRow);
			}
			
			// close connection
			conn.close();
		}catch(SQLException e){
			log.error("SQLException: "+e.getCause());
		}catch(NumberFormatException e){
			log.error("NumberFormatException: "+e.getCause());
		}finally{
			// keep going...
		}
		
		DefaultTableModel tm = new DefaultTableModel(drinks, columns);
		return (tm);
	}
}