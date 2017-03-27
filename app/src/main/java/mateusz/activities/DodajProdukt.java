package mateusz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mateusz.classDAO.ProductDAO;
import mateusz.klasy.MyContext;
import mateusz.klasy.Produkt;

public class DodajProdukt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_produkt);

        final EditText nazwa = (EditText) findViewById(R.id.nazwa);
        final EditText skladniki = (EditText) findViewById(R.id.skladniki);

        Button dodaj = (Button) findViewById(R.id.dodaj);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(nazwa.getText().toString().isEmpty() || skladniki.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show();
                }
                else {
                    ProductDAO productDAO = new ProductDAO(getBaseContext());

                    productDAO.addProduct(new Produkt(MyContext.getKod_kreskowy(),nazwa.getText().toString(),skladniki.getText().toString()));
                    finish();

                    Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                    startActivity(intent);
                }

            }
        });


        Button wroc = (Button) findViewById(R.id.wroc);
        wroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                startActivity(intent);
            }
        });
    }
}
