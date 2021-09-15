package luke.net.jersey.yes;

import javax.xml.bind.annotation.XmlRootElement;


/* Class for Json conversion */
@XmlRootElement()
//@JsonInclude(Include.NON_EMPTY)
public class employee {
	private String firstName;
	private String lastName;
	private String id;
	private String stipendio;
	private String funzione;
	private String filiale;
	private String livello;
	
	
	public String getFiliale() {
        return filiale;
    }
 
    public void setFiliale(String fl) {
        this.filiale = fl;
    }
	
	public String getFunzione() {
        return funzione;
    }
 
    public void setFunzione(String fn) {
        this.funzione = fn;
    }
	
	public String getStipendio() {
        return stipendio;
    }
 
    public void setStipendio(String st) {
        this.stipendio = st;
    }
	
	//_________________________________
	public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

}
