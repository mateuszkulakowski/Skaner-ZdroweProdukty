package mateusz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mateusz.classDAO.ProducentDAO;
import mateusz.klasy.Producent;

public class EdytujProducent extends AppCompatActivity {

    EditText nazwa;
    EditText adres;
    Button wroc;
    Button edytuj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_producent);

        nazwa = (EditText) findViewById(R.id.nazwa);
        adres = (EditText) findViewById(R.id.adres);
        wroc = (Button) findViewById(R.id.wroc);
        edytuj = (Button) findViewById(R.id.edytuj);

        final ProducentDAO producentDAO = new ProducentDAO(this);

        final Producent producent = producentDAO.getProducent(getIntent().getIntExtra("id_producenta",-1));

        nazwa.setText(producent.getNazwa());
        adres.setText(producent.getAdres());


        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                startActivity(intent);
            }
        });


        edytuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nazwa.getText().toString().isEmpty() || adres.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    producentDAO.updateProducent(new Producent(producent.get_id(),nazwa.getText().toString(),adres.getText().toString()));

                    finish();

                    Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                    startActivity(intent);
                }
            }
        });

    }

}
