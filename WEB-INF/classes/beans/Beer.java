package beans;

public class Beer {
    
    private String nome, descrizione;
	private Float gradazione, prezzo;
	private Integer id_birra, id_tipologia, quantita;

	public Beer(String nome, String descrizione, Float gradazione, Float prezzo, Integer id_birra, Integer id_tipologia, Integer quantita){

		this.nome = nome;
        this.descrizione = descrizione;
        this.gradazione = gradazione;
        this.prezzo = prezzo;
		this.id_birra = id_birra;
		this.id_tipologia = id_tipologia;
		this.quantita = quantita;
	}

	public String getNome() { return nome; } 
    
	public String getDescrizione() { return descrizione; }

	public Float getGradazione() { return gradazione; }

	public Float getPrezzo() { return prezzo; }

	public Integer getId_birra() { return id_birra; }

	public Integer getId_tipologia() { return id_tipologia; }

	public Integer getQuantita() { return quantita; }
}
