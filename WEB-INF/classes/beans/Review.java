package beans;

import java.sql.*;

public class Review {

    private String prodotto, nome, cognome, recensione;
    private Integer valutazione;
    private Date data;
    


    public Review (String prodotto, String nome, String cognome, Integer valutazione, String recensione, Date data){
        
        this.prodotto = prodotto;
        this.nome = nome;
        this.cognome = cognome;
        this.valutazione = valutazione;
        this.recensione = recensione;
        this.data = data;
        
    }

    public String getProdotto() {return prodotto;}

    public String getNome() {return nome;}

    public String getCognome() {return cognome;}

    public Integer getValutazione() {return valutazione;}

    public String getRecensione() {return recensione;}

    public Date getData() {return data;}

    
    
}
