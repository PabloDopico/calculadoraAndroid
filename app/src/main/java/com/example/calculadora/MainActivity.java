package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private TextView Screen;

private Button AC,Point,Equal,Power,Back,Div,Mul,Plus,Min,One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Zero,Ans;
private String input,Answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen=findViewById(R.id.screen);

        //Asignamos todos los botones con sus respectivos id del XML

        AC=findViewById(R.id.ac);
        Power=findViewById(R.id.power);
        Back=findViewById(R.id.back);
        Div=findViewById(R.id.div);
        Seven=findViewById(R.id.seven);
        Eight=findViewById(R.id.eight);
        Nine=findViewById(R.id.nine);
        Mul=findViewById(R.id.mul);
        Four=findViewById(R.id.four);
        Five=findViewById(R.id.five);
        Six=findViewById(R.id.six);
        Min=findViewById(R.id.min);
        One=findViewById(R.id.one);
        Two=findViewById(R.id.two);
        Three=findViewById(R.id.three);
        Plus=findViewById(R.id.plus);
        Zero=findViewById(R.id.zero);
        Point =findViewById(R.id.point);
        Ans=findViewById(R.id.ans);
        Equal=findViewById(R.id.equal);
    }

    //Indicamos lo que hará el programa al clickar sobre cada botón
    public void ButtonClick(View view){
        Button button =(Button) view;
        String data = button.getText().toString(); //asignamos a la variable data el texto del boton


            switch (data) {

                case "AC":
                    input = ""; // AC borra el texto en pantalla
                    break;

                case "Ans":
                    input += Answer; // Ans nos devuelve la respuesta anterior
                    break;

                case "*":
                    solve();  // llamamos al metodo Solve para resolver la operacion
                    input += "*";
                    break;

                case "^":
                    solve();
                    input += "^";
                    break;

                case "=":
                    solve();
                    Answer = input;
                    break;

                case ",":
                    input += ".";
                    break;

                case "del":
                    if(input.length()!=0){
                        String newText = input.substring(0, input.length() - 1);  // borramos la ultima cifra tecleada
                        input = newText;
                    }
                    break;

                default:
                    if (input == null) {
                        input = "";
                    }
                    if (data.equals("+") || data.equals("-") || data.equals("/")) { // si pulsamos una de estas operaciones llamamos al metodo solve
                        solve();
                    }
                    input += data;
            }
            Screen.setText(input); // añadimos el input al textview
        }

    private void solve() {
        if (input.split("\\*").length == 2) { // si pulsamos *
            String number[] = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul+"";
            } catch (Exception e) {
                //Toggleerror
            }

        } else if (input.split("/").length == 2) { // si pulsamos /
            String number[] = input.split("/");
            try {
                double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                input = div+"";
            } catch (Exception e) {
                //Toggleerror
            }

        } else if (input.split("\\^").length == 2) { // si pulsamos ^
            String number[] = input.split("\\^");

            try {
                double pow = Math.pow(Double.parseDouble(number[0]), Double.parseDouble(number[1]));
                input = pow+"";
            } catch (Exception e) {
                //Toggleerror
            }

        } else if (input.split("\\+").length == 2) { // si pulsamos +
            String number[] = input.split("\\+");
           try {
                double sum = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = sum+"";
            } catch (Exception e) {
                //Toggleerror
            }
        } else if (input.split("-").length > 1) { // si pulsamos -
            String number[] = input.split("-");
            //to substract from negative number
            if (number[0] == "" && number.length == 2) { // si el numero es negativo
                number[0] = 0 + "";
            }
            try {
                double sub = 0;
                if (number.length == 2) {
                    sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                }
                else if (number.length == 3) {
                    sub = Double.parseDouble(number[1])-Double.parseDouble(number[2]);
                }
                    input = sub+"";
            } catch (Exception e) {}
        }
        //para quitar el .0 despues de un numero entero
        String n[]=input.split("\\.");
        if(n.length>1) {
            if(n[1].equals("0")) { // si acaba en .0 escribimos unicamente lo que va antes del punto
                input=n[0];
            }
        }
        Screen.setText(input);
    }
}