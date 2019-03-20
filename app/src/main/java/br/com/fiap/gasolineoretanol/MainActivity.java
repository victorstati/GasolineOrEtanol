package br.com.fiap.gasolineoretanol;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView gasPriceTextView;

    private SeekBar gasSeekBar;

    private TextView etanolPriceTextView;

    private SeekBar etanolSeekBar;

    private TextInputEditText resultTextInputEditText;

    private ImageView resultImageView;

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    double gasPrice = 0.0;
    double etanolPrice = 0.0;

    public class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            if(seekBar.getId() == R.id.gasSeekBar){
                gasPrice = i / 100d;
                gasPriceTextView.setText(currencyFormat.format(gasPrice));
            }else if(seekBar.getId() == R.id.etanolSeekBar){
                etanolPrice = i / 100d;
                etanolPriceTextView.setText(currencyFormat.format(etanolPrice));
            }

            combustivelMaisBarato();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    public void combustivelMaisBarato(){
        if((etanolPrice/gasPrice) >= 0.7){
            resultTextInputEditText.setText(R.string.gasoline);
            resultImageView.setImageResource(R.drawable.galao_gasolina);
        }else{
            resultTextInputEditText.setText(R.string.etanol);
            resultImageView.setImageResource(R.drawable.etanol);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperando as views
        gasPriceTextView = findViewById(R.id.gasPriceTextView);
        gasSeekBar = findViewById(R.id.gasSeekBar);
        etanolPriceTextView = findViewById(R.id.etanolPriceTextView);
        etanolSeekBar = findViewById(R.id.etanolSeekBar);
        resultTextInputEditText = findViewById(R.id.resultTextInputEditText);
        resultImageView = findViewById(R.id.resultTextInputEditText);

        //Formato moeda
        gasPriceTextView.setText(currencyFormat.format(0));
        etanolPriceTextView.setText(currencyFormat.format(0));

        gasSeekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
        etanolSeekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
    }
}
