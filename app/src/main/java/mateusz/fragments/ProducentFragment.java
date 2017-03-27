package mateusz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import mateusz.activities.DodajProducenta;
import mateusz.activities.DodajSkladnikWplyw;
import mateusz.activities.EdytujProducent;
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

public class ProducentFragment extends Fragment {

    TextView nazwanaglowek;
    TextView nazwacontent;
    TextView adresnaglowek;
    TextView adrescontent;
    Button edytuj;
    Button dodaj;
    Button usun;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_producent, container, false);

        final ProductDAO productDAO = new ProductDAO(getActivity());
        final ProducentDAO producentDAO = new ProducentDAO(getActivity());

        nazwanaglowek = (TextView) v.findViewById(R.id.nazwanaglowek);
        nazwacontent = (TextView) v.findViewById(R.id.nazwacontent);
        adresnaglowek = (TextView) v.findViewById(R.id.adresnaglowek);
        adrescontent = (TextView) v.findViewById(R.id.adrescontent);
        edytuj = (Button) v.findViewById(R.id.edytuj);
        dodaj = (Button) v.findViewById(R.id.dodajproducent);
        usun = (Button) v.findViewById(R.id.usun);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

                Intent intent = new Intent(getActivity().getBaseContext(),DodajProducenta.class);
                startActivity(intent);
            }
        });





        final Produkt produkt = productDAO.getProduct(MyContext.getKod_kreskowy());

        if(produkt == null)
        {
            nazwanaglowek.setText(getString(R.string.brak_producenta));
            nazwacontent.setVisibility(View.INVISIBLE);
            adresnaglowek.setVisibility(View.INVISIBLE);
            adrescontent.setVisibility(View.INVISIBLE);
            edytuj.setVisibility(View.INVISIBLE);
            usun.setVisibility(View.INVISIBLE);
            dodaj.setVisibility(View.INVISIBLE);

        }
        else
        {
            Producent producent = null;

            try {
                producent = producentDAO.getProducent(produkt.getProducent_id());

            }catch (NullPointerException ex)
            {
                producent = null;
            }

            if(producent == null) //produkt jest ale producenta nie ma
            {
                nazwanaglowek.setText(getString(R.string.brak_producenta));
                nazwacontent.setVisibility(View.INVISIBLE);
                adresnaglowek.setVisibility(View.INVISIBLE);
                adrescontent.setVisibility(View.INVISIBLE);
                edytuj.setVisibility(View.INVISIBLE);
                usun.setVisibility(View.INVISIBLE);
                dodaj.setVisibility(View.VISIBLE);
            }
            else // producent jest ustawiony
            {
                nazwanaglowek.setText(getString(R.string.nazwa_producenta));
                nazwacontent.setVisibility(View.VISIBLE);
                adresnaglowek.setVisibility(View.VISIBLE);
                adrescontent.setVisibility(View.VISIBLE);
                edytuj.setVisibility(View.VISIBLE);
                usun.setVisibility(View.VISIBLE);
                dodaj.setVisibility(View.INVISIBLE);

                nazwacontent.setText(producent.getNazwa());
                adrescontent.setText(producent.getAdres());


                edytuj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();

                        Intent intent = new Intent(getActivity().getBaseContext(),EdytujProducent.class);
                        intent.putExtra("id_producenta",produkt.getProducent_id());
                        startActivity(intent);
                    }
                });
                usun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();


                        producentDAO.deleteProducent(new Producent(produkt.getProducent_id(),null,null));
                        produkt.setProducent_id(null);
                        productDAO.updateProduct(produkt);

                        Intent intent = new Intent(getActivity().getBaseContext(),Szczegoly.class);
                        startActivity(intent);

                    }
                });


            }
        }



        return v;
    }
}
