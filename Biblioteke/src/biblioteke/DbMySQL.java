package biblioteke;

import java.sql.*;

public class DbMySQL {
	private String username;
	private String password;
	private String url;
	
	//konstruktor obezbedjuje podatke za konekciju i vrsi konekciju
	public DbMySQL(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		// konekcije idu u metodima jer se odmah zatvaraju
	}

	// Insert, Delete & Update
	public void updateQuery(String query) {
		try(Connection conn = DriverManager.getConnection(url, username,password)) {
			System.out.println("Uspesna konekcija ...");
			Statement ps = conn.createStatement(); // objekat za komunikciju sa bazom
			ps.executeUpdate(query);
			System.out.println("SQL Insert | Update | Delete upit je izvršen ... \n-----------------------");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Greška! \n" + e);
			}
	}

	// Select upit
	public String[][] selectQuery(String query) {
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			System.out.println("Uspešna konekcija ...");
			Statement stmt = conn.createStatement(); // objekat za komunikciju sa bazom
			ResultSet result = stmt.executeQuery(query);
			ResultSetMetaData resultColumns = result.getMetaData(); // informacije o zaglavlju i slično
			int numCol = resultColumns.getColumnCount();
			int numRow = 0;
			while (result.next()) numRow++; //broj redova sa podacima
			String[][] resultArr = new String[numRow+1][numCol];
			//zaglavlje, I red
			for(int i=0;i<numCol;i++) {
				resultArr[0][i] = resultColumns.getColumnLabel(i+1);
			}
			result = stmt.executeQuery(query); // reset
			int i = 1;
			// podaci, sve osim I reda
			while(result.next()) {
				for(int j=0; j< numCol; j++) {
					resultArr[i][j] = result.getString(j+1);
				}
				System.out.println("");
				i++;
			}
			System.out.println("SQL Select upit je izvršen ... \n-----------------------");
			return resultArr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
