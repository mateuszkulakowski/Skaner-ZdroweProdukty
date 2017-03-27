package mateusz.klasy;


/**
 * Created by Mateusz on 16.12.2016.
 */

public class MyContext {

    private static Long kod_kreskowy;

    public static Long getKod_kreskowy() {
        return kod_kreskowy;
    }

    public static void setKod_kreskowy(Long kod_kreskowy) {
        MyContext.kod_kreskowy = kod_kreskowy;
    }
}
