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

public class DodajSkladnikWplyw extends AppCompatActivity {

    Spinner lista_rodzaj;
    EditText nazwa;
    EditText opis;
    Button dodaj;
    Button wroc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_skladnik_wplyw);

        final SkladnikiWplywDAO skladnikiWplywDAO = new SkladnikiWplywDAO(this);

        lista_rodzaj = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_rodzaj_skladnika, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lista_rodzaj.setAdapter(adapter);

        opis = (EditText) findViewById(R.id.opis);
        opis.setMovementMethod(new ScrollingMovementMethod());

        nazwa = (EditText) findViewById(R.id.nazwa);



        dodaj = (Button) findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nazwa.getText().toString().isEmpty() || opis.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show();

                }
                else
                {
                    SkladnikiWplyw sw = new SkladnikiWplyw(lista_rodzaj.getSelectedItem().toString(),nazwa.getText().toString(),opis.getText().toString());
                    skladnikiWplywDAO.addSkladnikWplyw(sw);
                    finish();

                    Intent intent = new Intent(getBaseContext(),WszystkieSkladnikiWplyw.class);
                    startActivity(intent);
                }
            }
        });


        wroc = (Button) findViewById(R.id.wroc);
        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Intent intent = new Intent(getBaseContext(),WszystkieSkladnikiWplyw.class);
                startActivity(intent);
            }
        });
    }
}
