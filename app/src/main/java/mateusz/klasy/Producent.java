package mateusz.klasy;

/**
 * Created by Mateusz on 16.12.2016.
 */

public class Producent {

    private int _id;
    private String Nazwa;
    private String Adres;

    public Producent() {
    }

    public Producent(int _id, String nazwa, String adres) {
        this._id = _id;
        Nazwa = nazwa;
        Adres = adres;
    }

    public Producent(String nazwa, String adres) {
        Nazwa = nazwa;
        Adres = adres;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }

}
