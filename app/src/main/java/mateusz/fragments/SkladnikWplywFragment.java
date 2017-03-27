package mateusz.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mateusz.activities.R;
import mateusz.activities.SzczegolySkladnikiWplyw;
import mateusz.adapters.SkladnikiWplywListAdapter;
import mateusz.classDAO.ProductDAO;
import mateusz.classDAO.SkladnikiWplywDAO;
import mateusz.klasy.MyContext;
import mateusz.klasy.Produkt;
import mateusz.klasy.SkladnikiWplyw;

public class SkladnikWplywFragment extends Fragment {

    TextView brak_skladnikow;
    ListView lista;
    List<SkladnikiWplyw> listaSkladnikiWplyw;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_skladnik_wplyw, container, false);

        brak_skladnikow = (TextView) v.findViewById(R.id.napis_brak_skladnikow);

        ProductDAO productDAO = new ProductDAO(getActivity());
        SkladnikiWplywDAO skladnikiWplywDAO = new SkladnikiWplywDAO(getActivity());

        Produkt produkt = productDAO.getProduct(MyContext.getKod_kreskowy());

        listaSkladnikiWplyw = skladnikiWplywDAO.getSkladnikiWplywALL();
        List<SkladnikiWplyw> lista_pozycji_do_usuniecia = new ArrayList();


        for(SkladnikiWplyw sw : listaSkladnikiWplyw)
        {
            String cala_nazwa_skladnik_wplyw = sw.getNazwa();
            String[] rozdzielenie = cala_nazwa_skladnik_wplyw.split(" ",2);

            String kod_skladnik = rozdzielenie[0];

            try {
                String nazwa_skladnik_wplyw = rozdzielenie[1];

                if (!produkt.getSkladniki().toUpperCase().contains(kod_skladnik.toUpperCase()) && !produkt.getSkladniki().toUpperCase().contains(nazwa_skladnik_wplyw.toUpperCase())) {
                    lista_pozycji_do_usuniecia.add(sw);
                }

            } catch (IndexOutOfBoundsException ex) {
                if (!produkt.getSkladniki().toUpperCase().contains(kod_skladnik.toUpperCase())) {
                    lista_pozycji_do_usuniecia.add(sw);
                }
            }catch(NullPointerException ex)// wyrzucany jak nie ma przedmiotu
            {
                listaSkladnikiWplyw = new ArrayList<>();
            }

        }

        for(SkladnikiWplyw sw : lista_pozycji_do_usuniecia)
        {
            listaSkladnikiWplyw.remove(sw);
        }



        if(!listaSkladnikiWplyw.isEmpty()) {
            final SkladnikiWplywListAdapter adapter = new SkladnikiWplywListAdapter(getActivity(), listaSkladnikiWplyw);


            brak_skladnikow.setText(getString(R.string.wykryte_skladniki));
            lista = (ListView) v.findViewById(R.id.lista_skladniki_wplyw);
            lista.setAdapter(adapter);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    getActivity().finish();

                    Intent intent = new Intent(getActivity(), SzczegolySkladnikiWplyw.class);
                    intent.putExtra("id_skladnika", listaSkladnikiWplyw.get(position).get_id());
                    intent.putExtra("without_edit", 1);
                    startActivity(intent);
                }
            });
        }
        else
        {
            brak_skladnikow.setText(getString(R.string.brak_wykrytych_skladnikow));
            lista = (ListView) v.findViewById(R.id.lista_skladniki_wplyw);
            lista.setVisibility(View.INVISIBLE);
        }


        return v;
    }
}
