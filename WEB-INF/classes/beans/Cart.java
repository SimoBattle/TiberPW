package beans;

public class Cart {

    private String prodotto;
    private Integer quantita, id;
    private Float prezzo, totale;

    public Cart(String prodotto, Integer quantita, Integer id, Float prezzo, Float totale){

        this.prodotto = prodotto;
        this.quantita = quantita;
        this.id = id;
        this.prezzo = prezzo;
        this.totale = totale;

    }

    public String getProdotto() {return prodotto; }

    public Integer getQuantita() { return quantita; }

    public Integer getId() { return id; }

    public Float getPrezzo() { return prezzo; }

    public Float getTotale() { return totale; }
    
}
