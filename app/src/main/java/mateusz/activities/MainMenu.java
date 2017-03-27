package mateusz.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import mateusz.klasy.MyContext;

public class MainMenu extends AppCompatActivity {

    Button skanuj;
    Button składniki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        skanuj = (Button) findViewById(R.id.skanuj);
        skanuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainMenu.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
                intentIntegrator.setPrompt(getString(R.string.skanuj));
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
            }
        });


        składniki = (Button) findViewById(R.id.Składniki);
        składniki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),WszystkieSkladnikiWplyw.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null)
        {
            if(result.getContents()==null)
            {
                Toast.makeText(this,getString(R.string.AnulowanieSkanowania),Toast.LENGTH_SHORT).show();
            }
            else // skanowanie powiodło się
            {
                Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);

                MyContext.setKod_kreskowy(Long.parseLong(result.getContents()));

                Intent intent = new Intent(getBaseContext(),Szczegoly.class);
                startActivity(intent);

            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
