package mateusz.klasy;

/**
 * Created by Mateusz on 15.12.2016.
 */

public class Produkt {

    private Long _id;
    private String Nazwa;
    private String Skladniki;
    private Integer Producent_id;

    public Produkt() {
    }

    public Produkt(Long _id, String nazwa, String skladniki) {
        this._id = _id;
        Nazwa = nazwa;
        Skladniki = skladniki;
    }

    public Produkt(Long _id, String nazwa, String skladniki, Integer producent_id) {
        Nazwa = nazwa;
        Skladniki = skladniki;
        Producent_id = producent_id;
        this._id = _id;
    }

    public Produkt(String nazwa,String skladniki) {
        Skladniki = skladniki;
        Nazwa = nazwa;
    }

    public Produkt(String nazwa, String skladniki, Integer producent_id) {
        Nazwa = nazwa;
        Skladniki = skladniki;
        Producent_id = producent_id;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getSkladniki() {
        return Skladniki;
    }

    public void setSkladniki(String skladniki) {
        Skladniki = skladniki;
    }

    public Integer getProducent_id() {
        return Producent_id;
    }

    public void setProducent_id(Integer producent_id) {
        Producent_id = producent_id;
    }
}
