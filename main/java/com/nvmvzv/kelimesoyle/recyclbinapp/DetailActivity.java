package com.nvmvzv.kelimesoyle.recyclbinapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class DetailActivity  extends AppCompatActivity {

    public static String TCTCTCTC= "";

    int sayac = 0;

    String line;
    boolean netvar = true;
    BufferedReader reader;


    String xgonderveri = "";


    String AllData = "";

    String data = "";
    String dataX = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final String TCTC = AccountActivity.TCTCTC.toString();


        if (!TCTC.equals("")) {

            try {


                xgonderveri = TCTC.toString();


                dataX = URLEncoder.encode("gonderveri1", "UTF-8") + "=" + URLEncoder.encode(xgonderveri.toString(), "UTF-8");


                URL url = new URL("http://recyclbinapp.nvmvzv.com/detail.aspx");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(100000);
                conn.setConnectTimeout(100000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(dataX.length()));
                conn.setDoInput(true);
                conn.setDoOutput(true);


                DataOutputStream out = new DataOutputStream(conn.getOutputStream());

                out.writeBytes(dataX);

                out.flush();

                out.close();
                // Defined URL  where to send data


                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                AllData = "";

                while ((line = reader.readLine()).indexOf("</html") < 0) {

                    //sb.append(line);

                    AllData += line.toString();


                }


                String veriveri = AllData.toString().replace("æææ", "ß");
                String[] veriveriveri = veriveri.toString().split("ß");

                if (veriveriveri.length > 0) {

                    String sAdSoyad     = veriveriveri[1].toString();
                    String sKimlik      = veriveriveri[2].toString();
                    String sTelefon     = veriveriveri[3].toString();
                    String sDoğumYeri   = veriveriveri[4].toString();
                    String sDogumTarihi = veriveriveri[5].toString();
                    String sSifre       = veriveriveri[6].toString();

                    TextView txtAdSoyad = (TextView) findViewById(R.id.textViewAdSoyad);
                    TextView txtTCKimlik = (TextView) findViewById(R.id.textViewTCkimlik);
                    TextView txtTelefon = (TextView) findViewById(R.id.textViewTelefon);

                    txtAdSoyad.setText("AD SOYAD : " + sAdSoyad.toString());
                    txtTCKimlik.setText("TC KİMLİK NO : " + sKimlik.toString());
                    txtTelefon.setText("TELEFON : " + sTelefon.toString());

                }


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        Button BtnGunc = (Button) findViewById(R.id.buttonGuncelleme);
        BtnGunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TCTCTCTC = TCTC.toString();

                Intent intent = new Intent(getApplicationContext(), DetailUpdateActivity.class);
                startActivity(intent);

            }});

    }


}
