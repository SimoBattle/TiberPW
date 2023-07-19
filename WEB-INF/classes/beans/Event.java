package beans;

import java.sql.*;


public class Event {

    private String nome, descrizione;
    private Date data;
    private Integer posti;

    public Event(String nome, String descrizione, Date data, Integer posti){

        this.nome = nome;
        this.descrizione = descrizione;
        this.data = data;
        this.posti = posti;
    }

    public String getNome(){ return nome; } 
    
    public String getDescrizione(){ return descrizione; }

    public Date getData(){ return data; }

    public Integer getPosti() {return posti; }
   
}
