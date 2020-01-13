package com.nvmvzv.kelimesoyle.recyclbinapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetailUpdateActivity   extends AppCompatActivity {

    int sayac = 0;

    String line;
    boolean netvar = true;
    BufferedReader reader;


    String xgonderveri1 = "";
    String xgonderveri2 = "";

    String gonderveri1 = "";
    String gonderveri2 = "";
    String gonderveri3 = "";
    String gonderveri4 = "";
    String gonderveri5 = "";
    String gonderveri6 = "";

    String AllData = "";

    String data = "";
    String dataX = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailupdate);

        ////////////////////

        String TCTC = DetailActivity.TCTCTCTC.toString();

        TextView eTCTC = (TextView) findViewById(R.id.editTextTC);
        eTCTC.setText(TCTC.toString());

        if (!TCTC.equals("")) {

            try {


                String xgonderveri = TCTC.toString();


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

                    String sAdSoyad = veriveriveri[1].toString();
                    String sTelefon = veriveriveri[3].toString();
                    String sDogumYeri = veriveriveri[4].toString();
                    String sDogumTarihi = veriveriveri[5].toString();
                    String sSifre = veriveriveri[6].toString();

                    EditText eAdSoyad = (EditText) findViewById(R.id.editTextAdSoyad);
                    eAdSoyad.setText(sAdSoyad.toString());

                    EditText eTelefon = (EditText) findViewById(R.id.editTextTelefon);
                    eTelefon.setText(sTelefon.toString());

                    EditText eDogumYeri = (EditText) findViewById(R.id.editTextDogumYeri);
                    eDogumYeri.setText(sDogumYeri.toString());

                    EditText eDogumTarihi = (EditText) findViewById(R.id.editTextDogumTarih);
                    eDogumTarihi.setText(sDogumTarihi.toString());

                    EditText eSifre = (EditText) findViewById(R.id.editTextSifre);
                    eSifre.setText(sSifre.toString());


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




            Button btnMENU = (Button) findViewById(R.id.buttonMENU);
            btnMENU.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    startActivity(intent);

                }
            });

                    /////////////////////////////////////////////////////////////////

            Button btnkayit = (Button) findViewById(R.id.buttonGuncelle);
            btnkayit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//////////////////////////////////////

                    EditText eAdSoyad = (EditText) findViewById(R.id.editTextAdSoyad);
                    String AdSoyad = eAdSoyad.getText().toString();


                    TextView eTC = (TextView) findViewById(R.id.editTextTC);
                    String TC = eTC.getText().toString();


                    EditText eDogumYeri = (EditText) findViewById(R.id.editTextDogumYeri);
                    String DogumYeri = eDogumYeri.getText().toString();


                    EditText eDogumTarih = (EditText) findViewById(R.id.editTextDogumTarih);
                    String DogumTarih = eDogumTarih.getText().toString();


                    EditText eSifre = (EditText) findViewById(R.id.editTextSifre);
                    String Sifre1 = eSifre.getText().toString();


                    EditText eTelefon = (EditText) findViewById(R.id.editTextTelefon);
                    String Telefon = eTelefon.getText().toString();

                    try {

                        gonderveri1 = AdSoyad.toString();
                        gonderveri2 = TC.toString();
                        gonderveri3 = Telefon.toString();
                        gonderveri4 = DogumYeri.toString();
                        gonderveri5 = DogumTarih.toString();
                        gonderveri6 = Sifre1.toString();

                        data = URLEncoder.encode("gonderveri1", "UTF-8") + "=" + URLEncoder.encode(gonderveri1.toString(), "UTF-8");
                        data += "&" + URLEncoder.encode("gonderveri2", "UTF-8") + "=" + URLEncoder.encode(gonderveri2.toString(), "UTF-8");
                        data += "&" + URLEncoder.encode("gonderveri3", "UTF-8") + "=" + URLEncoder.encode(gonderveri3.toString(), "UTF-8");
                        data += "&" + URLEncoder.encode("gonderveri4", "UTF-8") + "=" + URLEncoder.encode(gonderveri4.toString(), "UTF-8");
                        data += "&" + URLEncoder.encode("gonderveri5", "UTF-8") + "=" + URLEncoder.encode(gonderveri5.toString(), "UTF-8");
                        data += "&" + URLEncoder.encode("gonderveri6", "UTF-8") + "=" + URLEncoder.encode(gonderveri6.toString(), "UTF-8");


                        URL url = new URL("http://recyclbinapp.nvmvzv.com/detailguncelleme.aspx");


                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setReadTimeout(100000);
                        conn.setConnectTimeout(100000);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestProperty("Content-Length", String.valueOf(data.length()));
                        conn.setDoInput(true);
                        conn.setDoOutput(true);


                        DataOutputStream out = new DataOutputStream(conn.getOutputStream());

                        out.writeBytes(data);

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


                        String veriveri = AllData.toString().replace("@@@", "æ");
                        String[] veriveriveri = veriveri.toString().split("æ");

                        if (veriveriveri.length > 0) {

                            String Sonuc = veriveriveri[1].toString();


                            if (Sonuc.equals("guncellemeokey")) {

                                Toast.makeText(DetailUpdateActivity.this, "KAYIT BAŞARIYLA GÜNCELLENDİ !", Toast.LENGTH_SHORT).show();

                            }
                            if (Sonuc.equals("guncellemeerror")) {

                                Toast.makeText(DetailUpdateActivity.this, "HATA ! KAYIT YAPILAMADI !", Toast.LENGTH_SHORT).show();

                            }

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
            });


        }

    }


}

