package biblioteke;

import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		String user;
		String pass;
		String url = "jdbc:mysql://localhost:3306/biblioteke";
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Korisnik: ");
		user = sc.nextLine();
		System.out.print("Lozinka: ");
		pass = sc.nextLine();
		sc.close();
		
		DbMySQL con = new DbMySQL(url,user,pass);
		
		// primer prikaza svih bibliotekara (poziv procedure)
		String[][] bibl = con.selectQuery("CALL show_tabela('bibliotekari');");
		
		for(String[] red : bibl) {
			for(String vred : red)
				System.out.print(vred + "\t");
			System.out.println("\n");
		}
		
		// prikaz knjiga sa autorima (poziv procedure)
		// ovde ima jedna "greška" jer knjige pisane od strane više autora prikazuje više puta
		String[][] knjige = con.selectQuery("CALL show_knjige");
		
		for(String[] red : knjige) {
			for(String vred : red)
				System.out.print(vred + "\t");
			System.out.println("\n");
		}
	
	}

}

/* nedostatak vremena je razlog što nisam uspela detaljnije da uradim projekat */
