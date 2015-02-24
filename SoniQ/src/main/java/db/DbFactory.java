package db;

public class DbFactory {

	private Database db;
	
	public Database createDatabase(String type) throws DbException{
		
		if(type.equals("geheugen")){
			db = new GeheugenDb();
		} else if(type.equals("postgresql")){
			db = new PostgreSQL();
		}
		
		return db;
	}
	
}
