package vn.edu.fpt.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Date d = new Date();
    private TextView amountTextView;
    private EditText minhHoaEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView) findViewById(R.id.amountTextView);
        amountTextView.setText("Hihi");
        minhHoaEdit = (EditText) findViewById(R.id.amountEditText);
        minhHoaEdit.addTextChangedListener(amountEditTextWatcher);
    }
    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            System.out.println("onTextChanged da chay , hahaha");
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}