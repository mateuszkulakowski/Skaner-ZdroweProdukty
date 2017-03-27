package mateusz.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mateusz.classDAO.ProductDAO;
import mateusz.klasy.Produkt;

public class EdytujProdukt extends AppCompatActivity {

    EditText nazwa;
    EditText skladniki;
    Button wroc;
    Button edytuj;
    Long id_produktu;

    Integer id_producenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_produkt);

        id_produktu = getIntent().getLongExtra("id_produktu",-1);

        final ProductDAO productDAO = new ProductDAO(this);
        Produkt produkt = productDAO.getProduct(id_produktu);
        id_producenta = produkt.getProducent_id();

        nazwa = (EditText) findViewById(R.id.nazwa);
        skladniki = (EditText) findViewById(R.id.skladniki);
        wroc = (Button) findViewById(R.id.wroc);
        edytuj = (Button) findViewById(R.id.edytuj);


        nazwa.setText(produkt.getNazwa());
        skladniki.setText(produkt.getSkladniki());



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
                if(nazwa.getText().toString().isEmpty() || skladniki.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Uzupełnij wszystkie pola!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Produkt help = new Produkt(id_produktu,nazwa.getText().toString(),skladniki.getText().toString(),id_producenta);
                    productDAO.updateProduct(help);
                    finish();

                    Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                    startActivity(intent);
                }
            }
        });
    }
}
