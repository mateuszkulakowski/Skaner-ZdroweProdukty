package mateusz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import mateusz.activities.DodajProdukt;
import mateusz.activities.EdytujProdukt;
import mateusz.activities.R;
import mateusz.activities.Szczegoly;
import mateusz.classDAO.ProducentDAO;
import mateusz.classDAO.ProductDAO;
import mateusz.klasy.MyContext;
import mateusz.klasy.Producent;
import mateusz.klasy.Produkt;

/**
 * Created by Mateusz on 15.12.2016.
 */

public class SkladnikiFragment extends Fragment {

    TextView skladnikicontent;
    TextView skladnikinaglowek;
    TextView nazwanaglowek;
    TextView nazwacontent;
    Button dodajprodukt;
    Button edycja;
    Button usun;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_skladniki, container, false);

        skladnikicontent = (TextView) v.findViewById(R.id.skladnikicontent);
        skladnikicontent.setMovementMethod(new ScrollingMovementMethod());
        skladnikinaglowek = (TextView) v.findViewById(R.id.skladnikinaglowek);
        nazwacontent = (TextView) v.findViewById(R.id.nazwacontent);
        nazwanaglowek = (TextView) v.findViewById(R.id.nazwanaglowek);

        edycja = (Button) v.findViewById(R.id.edytuj);
        edycja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

                Intent intent = new Intent(getActivity().getBaseContext(),EdytujProdukt.class);
                intent.putExtra("id_produktu",MyContext.getKod_kreskowy());
                startActivity(intent);            }
        });


        dodajprodukt = (Button) v.findViewById(R.id.dodajprodukt);
        dodajprodukt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

                Intent intent = new Intent(getActivity().getBaseContext(),DodajProdukt.class);
                startActivity(intent);
            }
        });

        final ProductDAO productDAO = new ProductDAO(getActivity());
        final Produkt produkt = productDAO.getProduct(MyContext.getKod_kreskowy());

        usun = (Button) v.findViewById(R.id.usun);
        usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

                if(produkt.getProducent_id() != null)
                {
                    ProducentDAO producentDAO = new ProducentDAO(getActivity());
                    producentDAO.deleteProducent(new Producent(produkt.getProducent_id(),null,null));
                }

                productDAO.deleteProduct(new Produkt(MyContext.getKod_kreskowy(),null,null));

                Intent intent = new Intent(getActivity().getBaseContext(),Szczegoly.class);
                startActivity(intent);
            }
        });




        if(produkt == null)
        {
            skladnikinaglowek.setText(getString(R.string.brak_produktu));
            skladnikicontent.setText("");
            nazwacontent.setVisibility(View.INVISIBLE);
            nazwanaglowek.setVisibility(View.INVISIBLE);
            edycja.setVisibility(View.INVISIBLE);
            usun.setVisibility(View.INVISIBLE);
        }
        else
        {
            usun.setVisibility(View.VISIBLE);
            edycja.setVisibility(View.VISIBLE);
            nazwacontent.setVisibility(View.VISIBLE);
            nazwanaglowek.setVisibility(View.VISIBLE);

            nazwacontent.setText(produkt.getNazwa());

            skladnikinaglowek.setText(getString(R.string.skladniki_produktu));
            skladnikicontent.setText(produkt.getSkladniki());
            dodajprodukt.setVisibility(View.INVISIBLE);
        }

        return v;
    }
}
