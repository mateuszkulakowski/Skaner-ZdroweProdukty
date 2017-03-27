package mateusz.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mateusz.activities.R;
import mateusz.klasy.SkladnikiWplyw;

/**
 * Created by Mateusz on 02.01.2017.
 */

public class SkladnikiWplywListAdapter extends ArrayAdapter<SkladnikiWplyw> {

    private final Activity context;
    private List<SkladnikiWplyw> listaSkladnikow;

    public SkladnikiWplywListAdapter(Activity context, List<SkladnikiWplyw> wejsciowalista) {


        super(context, R.layout.skladnikiwplyw_list, wejsciowalista);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.listaSkladnikow = wejsciowalista;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.skladnikiwplyw_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(listaSkladnikow.get(position).getNazwa());

        if(listaSkladnikow.get(position).getRodzaj().equals("Pozytywny"))
        {
            imageView.setImageResource(R.drawable.wesola);
        }
        else if(listaSkladnikow.get(position).getRodzaj().equals("Obojętny"))
        {
            imageView.setImageResource(R.drawable.srednia);
        }
        else
        {
            imageView.setImageResource(R.drawable.negatywna);
        }
        extratxt.setText(context.getString(R.string.Rodzaj)+": "+listaSkladnikow.get(position).getRodzaj());
        return rowView;

    };
}
