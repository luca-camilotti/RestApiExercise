package luke.net.jersey.yes;

import javax.ws.rs.core.*;
import javax.ws.rs.*;

/*
 * Esempi di web api:
 * 
	http://dominio.mio:8080/NomeProgetto/url-pattern/dbazienda/risorsa
	http://localhost:8080/JerseyJson/dbazienda/dipendente
*/

@Path("dbazienda")  // path servizio
public class JerseyDatabase {
	
	/* Ottieni tutti i record presenti */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("dipendente")
	public employee[] getAll() {
	
		/* Scrivere il codice del metodo
		 * che restituisce tutti i record 
		 * della tabella "dipendente" sotto
		 * forma di array di oggetti json */
		employee[] array = null;
		dbHelper.connect();
		array = dbHelper.query(dbHelper.queryAllDipendente);
		dbHelper.close();
		return array;	
	}
	
	/* Ricerca per filiale o per ruolo */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("dipendente/{campo}/{valore}")
	public employee[] getDipendentiFiliale(@PathParam("campo") String campo, @PathParam("valore") String valore) {
		
		/* Scrivere il codice del metodo
		 * che restituisce tutti i record 
		 * della tabella "dipendente" che
		 * corrispondono alla clausola di ricerca */
		employee[] array = null;
		dbHelper.connect();
		try {
		array=dbHelper.query("SELECT * FROM dipendente where "+campo+" = '"+valore+"'");
		dbHelper.close();
		} catch (Exception e) {
			throw new WebApplicationException(400); // bad request
		}
		return array;	
		
	}
	
	/* Inserimento di un nuovo record */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("dipendente")
	public void insert(employee em) {
	
		/* Scrivere il codice del metodo
		 * che inserisce un nuovo record 
		 * della tabella "dipendente".
		 * Es:
		 * {"firstName":"Chuck",
			"lastName":"Norris",
			"stipendio":"1280", 
			"funzione": "fattorino",
			"filiale": "Miami" ,
			"livello": "15"
			}
		 *  */
		String cmdInsert = "insert into dipendente "+
		 " (nome, cognome, stipendio, funzione, filiale, livello) values "+
				"('"+em.getFirstName()+"', '"+em.getLastName()+"', "+em.getStipendio()+
				", '"+em.getFunzione()+"', '"+em.getFiliale()+"', "+em.getLivello()+")";
		System.out.println(cmdInsert);
		dbHelper.connect();
		dbHelper.execute(cmdInsert);
		dbHelper.close();
		
	}

}
