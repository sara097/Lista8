package com.example.user.lab7;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //atrybuty klasy, które są elementami aplikacji
    private TextView ans;
    private Spinner genderSpinner;
    private Spinner unitsSpinner;
    private Spinner waySpinner;
    private EditText weight;
    private EditText height;
    private EditText age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //przypisanie elementów dodanych do strybutów klasy
        ans = (TextView) findViewById(R.id.ETAns);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        unitsSpinner = (Spinner) findViewById(R.id.units);
        waySpinner = (Spinner) findViewById(R.id.waySpinner);
        weight = (EditText) findViewById(R.id.ETWeight);
        height = (EditText) findViewById(R.id.ETHeight);
        age = (EditText) findViewById(R.id.ETAge);
        age.setInputType(InputType.TYPE_NULL);

        //dodanie do pola tekstowego wieku OnFocusListenera
        age.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) { //jesli jest focus na tym polu tekstowym

                    Calendar mcurrentDate = Calendar.getInstance(); //utworzenie instancji kalendarza
                    //finalne zmienne na rok, miesiąc i dzień
                    final int mYear = mcurrentDate.get(Calendar.YEAR);
                    final int mMonth = mcurrentDate.get(Calendar.MONTH);
                    final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    //dialog z datePickerem
                    DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        //metoda jesli data jest wybrana
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            //zmienna na przechowywanie wieku
                            int ageCalculated = mYear - selectedyear; //różnica w latach
                            //jesli wybrany miesiac jest wiekszy od obecnego lub jesli wybrany miesiac jest równy
                            // ale wybrany dzien jest wiekszy od obecnego to zmniejszam rok
                            if (selectedmonth > mMonth || (selectedmonth == mMonth && selectedday >= mDay)) {
                                ageCalculated--;
                            }
                            if(ageCalculated>0) {
                                //ustawienie zeby wiek wyswietlał sie w polu tekstowym
                                age.setText(String.valueOf(ageCalculated));
                                age.clearFocus(); //usuniecie focusu z pola tekstowego, zeby po ponowym kliknieciu pojawiło się znowu wybieranie daty
                            } else{
                                age.setText("Niepoprawna data");
                                age.clearFocus();
                            }
                        }
                    }, mYear, mMonth, mDay);
                    datePicker.setTitle("Select date of birth"); //tytuł date pickera
                    datePicker.show(); //pokazanie date pickera
                }
            }
        });
    }


    //metoda po kliknięciu przycisku
    public void onClick(View view) {
        //zapisanie wartosci ze spinnerów
        String sex = String.valueOf(genderSpinner.getSelectedItem());
        String units = String.valueOf(unitsSpinner.getSelectedItem());
        int way = waySpinner.getSelectedItemPosition();

        //blok try i catch wyrzucający wyjątek, gdy wprowadzone dane nie sa numeryczne
        try {
            //zmienne pomocnicze do obliczeń
            double weight2 = Double.valueOf(weight.getText().toString());
            double height2 = Double.valueOf(height.getText().toString());
            double age2 = Double.valueOf(age.getText().toString());
            //utworzenie obiektu klasy PPM
            PPM ppm = new PPM();
            double result = 0; //wynik do wyświetlenia

            //w zalezności od indeksu wybranego elementu ze spinnera ze sposobem wywołana jest odpowiednia metoda z klasy PPM
            if (way == 0) {
                result = ppm.PPM_BH(sex, weight2, height2, age2, units);
                ans.setText(" Benedict-Harris - PPM = " + String.valueOf(result));
            } else {
                result = ppm.PPM_M(sex, weight2, height2, age2, units);
                ans.setText(" Mifflin - PPM = " + String.valueOf(result));
            }

        } catch (NumberFormatException e) {
            //jesli złapany jest wyjątek to nic nie jest obliczane tylko wyświetlany jest komunikat.
            ans.setText("Co najmniej jedno z pól nie jest liczba");

        }

    }


}
