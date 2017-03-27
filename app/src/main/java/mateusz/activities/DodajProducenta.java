package mateusz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mateusz.classDAO.ProducentDAO;
import mateusz.classDAO.ProductDAO;
import mateusz.klasy.MyContext;
import mateusz.klasy.Producent;
import mateusz.klasy.Produkt;

public class DodajProducenta extends AppCompatActivity {

    EditText nazwa;
    EditText adres;

    Button wroc;
    Button dodaj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_producenta);

        final ProducentDAO producentDAO = new ProducentDAO(this);
        final ProductDAO productDAO = new ProductDAO(this);

        nazwa = (EditText) findViewById(R.id.nazwa);
        adres = (EditText) findViewById(R.id.adres);
        wroc = (Button) findViewById(R.id.wroc);
        dodaj = (Button) findViewById(R.id.dodaj);

        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                startActivity(intent);
            }
        });

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nazwa.getText().toString().isEmpty() || adres.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Long id_producent;


                    id_producent = producentDAO.addProducent(new Producent(nazwa.getText().toString(),adres.getText().toString()));

                    //ustawiamy producentID w produkcie

                    Produkt produkt = productDAO.getProduct(MyContext.getKod_kreskowy());
                    produkt.setProducent_id(id_producent.intValue());

                    productDAO.updateProduct(produkt);


                    finish();

                    Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                    startActivity(intent);

                }

            }
        });



    }
}
