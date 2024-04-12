package com.raytech.androidmultilanguageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView sampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //uygulamayı yeniden başlattığımızda daha önceden seçilen dil ile başlatılabilmesi için
        LoadLocale();

        setContentView(R.layout.activity_main);
        sampleText=findViewById(R.id.translateText);
    }

    private void ChangeLanguage(String lang) {
        // Yeni bir dil için Locale oluşturuyoruz
        Locale locale = new Locale(lang);
        // Bu dilin varsayılan dil olmasını sağlıyoruz
        Locale.setDefault(locale);
        // Yeni bir ayar nesnesi oluşturuyoruz
        Configuration config = new Configuration();
        // Yeni dil ayarını Configuration'a ekliyoruz
        config.locale = locale;
        // Yeni dil ayarını uygulama kaynaklarına ve ekran ölçümlerine yansıtıyoruz
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // Güncellenen dil ayarını önbelleğe alıyoruz
        SharedPreferences.Editor editor  = getSharedPreferences("settings",MODE_PRIVATE).edit();
        editor.putString("My Lang",lang);
        editor.apply();

    }

    //oncreate methodumuzdan çağrılacak böylelikle en son işaretlenen dil uygulamayı açtığımızda seçili şekilde gelecek.
    public  void LoadLocale(){
        SharedPreferences prefs = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language=prefs.getString("My Lang","");
        ChangeLanguage(language);
    }


    public void BtnEnglish(View view) {
        // İngilizce diline geçiş yapılır
        ChangeLanguage("en");
        // Aktivite yeniden oluşturulur, böylece dil değişikliği görsel olarakta gözükür.
        recreate();
    }

    public void BtnRussian(View view) {
        // Rusça diline geçiş yapılır
        ChangeLanguage("ru");
        // Aktivite yeniden oluşturulur, böylece dil değişikliği görsel olarakta gözükür.
        recreate();
    }



}








