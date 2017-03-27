package mateusz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import mateusz.classDAO.SkladnikiWplywDAO;
import mateusz.klasy.SkladnikiWplyw;

public class SzczegolySkladnikiWplyw extends AppCompatActivity {

    TextView nazwa;
    TextView rodzaj;
    TextView opis;
    ImageView obrazek;
    Button wroc;
    Button edytuj;
    Button usun;

    int id_skladnika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szczegoly_skladniki_wplyw);

        nazwa = (TextView) findViewById(R.id.nazwa);
        rodzaj = (TextView) findViewById(R.id.rodzaj);
        opis = (TextView) findViewById(R.id.opis);

        id_skladnika = getIntent().getIntExtra("id_skladnika",-1);

        final SkladnikiWplywDAO skladnikiWplywDAO = new SkladnikiWplywDAO(this);

        SkladnikiWplyw skladnikiWplyw = skladnikiWplywDAO.getSkladnikById(id_skladnika);

        nazwa.setText(skladnikiWplyw.getNazwa());
        rodzaj.setText(skladnikiWplyw.getRodzaj());
        opis.setMovementMethod(new ScrollingMovementMethod());
        opis.setText(skladnikiWplyw.getOpis());

        obrazek = (ImageView) findViewById(R.id.obrazek);

        if(skladnikiWplyw.getRodzaj().equals("Pozytywny"))
        {
            obrazek.setImageResource(R.drawable.wesola);
        }
        else if(skladnikiWplyw.getRodzaj().equals("Negatywny"))
        {
            obrazek.setImageResource(R.drawable.negatywna);
        }
        else
        {
            obrazek.setImageResource(R.drawable.srednia);
        }

        final int opcja = getIntent().getIntExtra("without_edit",-1);

        wroc = (Button) findViewById(R.id.wroc);
        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                if(opcja == -1) {
                    Intent intent = new Intent(getBaseContext(), WszystkieSkladnikiWplyw.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getBaseContext(), Szczegoly.class);
                    startActivity(intent);
                }

            }
        });


        usun = (Button) findViewById(R.id.usun);
        edytuj = (Button) findViewById(R.id.edytuj);

        if(opcja == -1) {
            usun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    skladnikiWplywDAO.deleteSkladnikWplyw(new SkladnikiWplyw(id_skladnika, null, null, null));
                    finish();

                    Intent intent = new Intent(getBaseContext(), WszystkieSkladnikiWplyw.class);
                    startActivity(intent);

                }
            });


            edytuj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                    Intent intent = new Intent(getBaseContext(), EdytujSkladnikWplyw.class);
                    intent.putExtra("id_skladnika", id_skladnika);
                    startActivity(intent);
                }
            });
        }
        else // ukrywamy przyciski
        {
            usun.setVisibility(View.INVISIBLE);
            edytuj.setVisibility(View.INVISIBLE);
        }

    }
}
