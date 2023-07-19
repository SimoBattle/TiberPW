package beans;

import java.sql.*;

public class Booking {

    private Integer prenotati;
    private String evento;
    private Date data;

    public Booking (Integer prenotati, String evento, Date data){

        this.prenotati = prenotati;
        this.evento = evento;
        this.data = data;
    }

    public Integer getPrenotati () {return prenotati;}

    public String getEvento () {return evento;}

    public Date getData () {return data;}
    
}
