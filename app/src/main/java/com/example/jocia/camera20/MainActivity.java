package com.example.jocia.camera20;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewFoto;
//    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},0);
        }

        imageViewFoto = (ImageView) findViewById(R.id.ivFoto);

        findViewById(R.id.btFoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        findViewById(R.id.btCompartilhar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilharFoto();
            }
        });
    }

    public void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 123);
    }

    public void compartilharFoto(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String texto = "Olá sou um texto compartilhado";
        sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 123 && resultCode == Activity.RESULT_OK){
            //usuario tirou a foto
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");

            imageViewFoto.setImageBitmap(imagem);
//                image = imagem;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
