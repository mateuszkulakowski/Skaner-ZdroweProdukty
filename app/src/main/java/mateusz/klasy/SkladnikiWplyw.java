package mateusz.klasy;

/**
 * Created by Mateusz on 16.12.2016.
 */

public class SkladnikiWplyw {

    private int _id;
    private String Rodzaj;
    private String Nazwa;
    private String Opis;

    public SkladnikiWplyw() {
    }

    public SkladnikiWplyw(int _id, String rodzaj, String nazwa, String opis) {
        this._id = _id;
        Rodzaj = rodzaj;
        Nazwa = nazwa;
        Opis = opis;
    }

    public SkladnikiWplyw(String rodzaj, String nazwa, String opis) {
        Rodzaj = rodzaj;
        Nazwa = nazwa;
        Opis = opis;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getRodzaj() {
        return Rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        Rodzaj = rodzaj;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }
}
