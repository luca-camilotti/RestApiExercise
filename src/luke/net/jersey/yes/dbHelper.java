package luke.net.jersey.yes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class dbHelper {
	
	public static Connection dbcon = null;
	private static  String DRIVER = "com.mysql.jdbc.Driver";
	private static  String URL_myDB = "jdbc:mysql://localhost:3306/azienda"; // azienda è il nome del DB
	public static  String queryAllDipendente = "SELECT * FROM dipendente";
	private static  String user = "root";
	private static  String pwd = "root";
	// Database Connection
	public static void connect() {		
		try 
		{
			Class.forName(DRIVER);
			System.out.println("Driver Connector/J trovato!");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("WARNING: driver Connector/J NON trovato!");
			//System.exit(1);  //quit
		}		

		// Connessione al DB	    
		try
		{
			dbcon = DriverManager.getConnection(URL_myDB, user, pwd);
			System.out.println("OK! Connesso a "+ URL_myDB+"!");
		}
		catch (Exception e)
		{
			dbcon = null;
			System.out.println("Errore di connessione a "+ URL_myDB);
			//System.exit(1);  //quit
		}
	}
	public static void close() {
		if(dbcon != null)
			try {
				dbcon.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static int getSize(ResultSet resultSet) {
		int size = 0;
		try {
		    resultSet.last();
		    size = resultSet.getRow();
		    resultSet.beforeFirst();
		}
		catch(Exception ex) {
		    return 0;
		}
		return size;
	}
	
	public static void execute(String what) {
		try {
	    	Statement sql = dbcon.createStatement();
	    	sql.execute(what);
	    	System.out.println("Ho eseguito il comando SQL: "+ what);
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di esecuzione comando SQL: "+ what);
	    	//System.exit(1);  //quit
	    }
	}
	
	public static employee[] query(String what) {
		// Query
	    ResultSet res = null;
	    /*
	     * ResultSetMetaData rsmd=rs.getMetaData();

			System.out.println("columns: "+rsmd.getColumnCount());  
			System.out.println("Column Name of 1st column: "+rsmd.getColumnName(2));  
			System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(2)); 
	     * */
	     
	    
	    try
	    {
	    	Statement sql = dbcon.createStatement();
	    	res = sql.executeQuery(what);
	    	System.out.println("Ho eseguito la query SQL: "+ what);
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Errore di esecuzione della query SQL: "+ what);
	    	//System.exit(1);  //quit
	    }
	    
	 // Show results
	    employee[] em = null;
	    try {
	    	int column = res.getMetaData().getColumnCount(), j=0;
	    	em = new employee[getSize(res)];
			while(res.next())
			{
				em[j] = new employee();
				em[j].setId(res.getString(1));
				em[j].setFirstName(res.getString(2));
				em[j].setLastName(res.getString(3));
				em[j].setStipendio(res.getString(4));
				em[j].setFunzione(res.getString(5));
				em[j].setFiliale(res.getString(6));
				em[j].setLivello(res.getString(7));
				String toPrint = "";
				for(int i=1; i<=column; i++)
					toPrint += ", "+ res.getString(i);				
				System.out.println(toPrint);
				j++;
			}
		} catch (SQLException e) {
			System.out.println("Errore di lettura risultato query ");
			e.printStackTrace();
		}
		return em;	
	}

}
