package mateusz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import mateusz.adapters.SkladnikiWplywListAdapter;
import mateusz.classDAO.SkladnikiWplywDAO;
import mateusz.klasy.SkladnikiWplyw;

public class WszystkieSkladnikiWplyw extends AppCompatActivity {

    static List<SkladnikiWplyw> skladnikiWplywList;
    ListView lista;
    Button dodajSkladnikWplyw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wszystkie_skladniki_wplyw);


        SkladnikiWplywDAO skladnikiWplywDAO = new SkladnikiWplywDAO(getApplicationContext());

       /* skladnikiWplywDAO.addSkladnikWplyw(new SkladnikiWplyw(getString(R.string.rodzaj_negatywny),"E102 tartrazyna","Występowanie: napoje w proszku, sztuczne miody, esencje owocowe, musztardy, napoje bezalkoholowe, zupy w proszku, galaretki, dżemy;\n" +
                "Działanie: u astmatyków może powodować  reakcje alergiczne, bezsenność, depresję, nadpobudliwość i dekoncentrację.  W połączeniu z benzoesanami jest podejrzewany o wywoływanie ADHD u dzieci."));

        skladnikiWplywDAO.addSkladnikWplyw(new SkladnikiWplyw(getString(R.string.rodzaj_obojetny),"E410 Mączka chleba świętojańskiego","Treść:\n" +
                "Występuje w postaci proszku, który ma barwę białą przechodzącą do żółtej. Praktycznie nie posiada zapachu ani smaku. Jest ona rozpuszczalna w gorącej wodzie. Zawiera >75% galaktomannanu, składającego się z D-mannozy i D-galaktozy (1:2), ok. 4% pentozanów, 6-7% białek, <1,2% popiołu. Mączkę tę otrzymuje się z bielma zmielonych nasion drzewa szańczyna strąkowego, który rośnie w rejonie Morza Śródziemnego oraz Azji Mniejszej.\n" +
                "\n" +
                "Wykorzystanie:\n" +
                "Stosowana głównie przy produkcji serów topionych, lodach, przetworach owocowych oraz produktach mlecznych. Ponadto można spotkać go w jadalnych kasztanach, dżemach, powidłach, marmoladach, niektórych produktach przeznaczonych dla dzieci i innych produktach spożywczych.\n" +
                "\n" +
                "Informacje dodatkowe:\n" +
                "Nie jest określone dopuszczalne dzienne spożycie. Brak informacji na temat skutków ubocznych. U niektórych zbyt duże ilości mogą doprowadzić wzdęć.\n" +
                "\n" +
                "Funkcja technologiczna:\n" +
                "zagęstnik, emulgator, nośnik, stabilizator"));

        skladnikiWplywDAO.addSkladnikWplyw(new SkladnikiWplyw(getString(R.string.rodzaj_pozytywny),"E160a Karoteny Karoten","Treść:\n" +
                "Jest to naturalny barwnik, który posiada charakterystyczną pomarańczowo-żółtą barwę. Znajduje się on w kilku roślinach np. marchwi, dyni, pomidorach. Do celów produkcyjnych uzyskuje się go przede wszystkim z marchwi. Otrzymuje się go w wyniku syntezy węglowodorów o niskiej masie cząsteczkowej. Są to nienasycone węglowodory o wzorze sumarycznym C40H56. Występuje w postaci kryształów lub w formie proszku o strukturze krystalicznej.  Karoteny nie rozpuszczają się w wodzie.\n" +
                "\n" +
                "Wykorzystanie:\n" +
                "Stosuje się je do barwienia margaryny, przetworów mlecznych oraz wyrobów cukierniczych.\n" +
                "\n" +
                "Informacje dodatkowe:\n" +
                "Dopuszczalne dzienne spożycie wynosi 5mg/kg masy ciała. Duże spożycie karotenu powoduje zmianę barwy skóry. Karoten jest doskonałym źródłem witaminy A. Jego nadmiar może wywołać objawy zatrucia tą właśnie witaminą czyli zawroty głowy, wymioty, nudności oraz złą koordynację mięśniową.\n" +
                "\n" +
                "Funkcja technologiczna:\n" +
                "barwnik"));*/



        skladnikiWplywList = skladnikiWplywDAO.getSkladnikiWplywALL();

        final SkladnikiWplywListAdapter adapter=new SkladnikiWplywListAdapter(this, skladnikiWplywList);
        lista=(ListView)findViewById(R.id.lista_skladniki_wplyw);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                finish();

                Intent intent = new Intent(getBaseContext(),SzczegolySkladnikiWplyw.class);
                intent.putExtra("id_skladnika", skladnikiWplywList.get(position).get_id());
                startActivity(intent);
            }
        });

        dodajSkladnikWplyw = (Button) findViewById(R.id.dodaj_pozycje);
        dodajSkladnikWplyw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Intent intent = new Intent(getBaseContext(),DodajSkladnikWplyw.class);
                startActivity(intent);
            }
        });

    }
}
