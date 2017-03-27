package mateusz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mateusz.classDAO.SkladnikiWplywDAO;
import mateusz.klasy.SkladnikiWplyw;

public class EdytujSkladnikWplyw extends AppCompatActivity {

    Spinner lista_rodzaj;
    EditText nazwa;
    EditText opis;
    Button edytuj;
    Button wroc;

    int id_skladnika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_skladnik_wplyw);

        id_skladnika = getIntent().getIntExtra("id_skladnika",-1);

        final SkladnikiWplywDAO skladnikiWplywDAO = new SkladnikiWplywDAO(this);
        SkladnikiWplyw skladnikiWplyw = skladnikiWplywDAO.getSkladnikById(id_skladnika);

        lista_rodzaj = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_rodzaj_skladnika, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lista_rodzaj.setAdapter(adapter);

        if(skladnikiWplyw.getRodzaj().equals("Pozytywny"))
        {
            lista_rodzaj.setSelection(0);
        }
        else if(skladnikiWplyw.getRodzaj().equals("Negatywny"))
        {
            lista_rodzaj.setSelection(1);
        }
        else
        {
            lista_rodzaj.setSelection(2);
        }


        nazwa = (EditText) findViewById(R.id.nazwa);
        nazwa.setText(skladnikiWplyw.getNazwa());

        opis = (EditText) findViewById(R.id.opis);
        opis.setMovementMethod(new ScrollingMovementMethod());
        opis.setText(skladnikiWplyw.getOpis());


        edytuj = (Button) findViewById(R.id.edytuj);
        edytuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nazwa.getText().toString().isEmpty() || opis.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show();

                }
                else
                {
                    SkladnikiWplyw sw = new SkladnikiWplyw(id_skladnika,lista_rodzaj.getSelectedItem().toString(),nazwa.getText().toString(),opis.getText().toString());
                    skladnikiWplywDAO.updateSkladnikWplyw(sw);
                    finish();

                    Intent intent = new Intent(getBaseContext(),SzczegolySkladnikiWplyw.class);
                    intent.putExtra("id_skladnika", id_skladnika);
                    startActivity(intent);

                }
            }
        });


        wroc = (Button) findViewById(R.id.wroc);
        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Intent intent = new Intent(getBaseContext(),SzczegolySkladnikiWplyw.class);
                intent.putExtra("id_skladnika", id_skladnika);
                startActivity(intent);
            }
        });

    }
}
