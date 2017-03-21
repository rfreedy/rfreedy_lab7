package com.cse40333.rfreedy.rob_freedy_application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.security.AccessController.getContext;

public class DetailActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    @Override

    public void onCreate (Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        //String[] stringInfo = getIntent().getStringArrayExtra("team");
        Team team = (Team) getIntent().getSerializableExtra("team");

        TextView gameTime = (TextView) findViewById(R.id.gameTime);
        gameTime.setText(team.getGameDate());

        TextView gameLocation = (TextView) findViewById(R.id.gameLocation);
        gameLocation.setText(team.getGameLocation());

        ImageView opposingLogo = (ImageView) findViewById(R.id.opposingLogo);
        String mDrawableName = team.getOpponentLogo();
        int resID = getResources().getIdentifier(mDrawableName , "drawable",  getPackageName());
        opposingLogo.setImageResource(resID);

        TextView opposingName = (TextView) findViewById(R.id.opposingName);
        opposingName.setText(team.getGameOpponent());

        TextView opposingMascot = (TextView) findViewById(R.id.opposingMascot);
        opposingMascot.setText(team.getOpponentMascot());

        TextView opposingRec = (TextView) findViewById(R.id.opposingRec);
        opposingRec.setText(team.getOpponentRecord());

        TextView scoreID = (TextView) findViewById(R.id.scoreID);
        scoreID.setText(team.getGameScore());

        TextView finalString = (TextView) findViewById(R.id.finalString);
        finalString.setText(team.getGameResult());

        TextView ndName = (TextView) findViewById(R.id.ndName);
        ndName.setText(team.getNDName());

        TextView ndMascot = (TextView) findViewById(R.id.ndMascot);
        ndMascot.setText(team.getNDMascot());

        TextView ndRec = (TextView) findViewById(R.id.ndRec);
        ndRec.setText(team.getNDRecord());

        ImageView ndLogo = (ImageView) findViewById(R.id.ndLogo);
        String mDrawableName2 = team.getNDLogo();
        int resID2 = getResources().getIdentifier(mDrawableName2 , "drawable",  getPackageName());
        ndLogo.setImageResource(resID2);

        Button cameraButton = (Button) findViewById(R.id.cameraButton);
        cameraButton.setText(team.getGameCamera());
        cameraButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File PictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureName = getPictureName();
                File imageFile = new File(PictureDirectory, pictureName);
                Uri pictureUri = Uri.fromFile(imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }

            private String getPictureName() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String timestamp = sdf.format(new Date());
                return "BestMoments" + timestamp + ".jpg";
            }

            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                    if (requestCode == CAMERA_REQUEST) {
                        Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                        String pictureDirectoryPath = pictureDirectory.getPath();
                        Uri imageUri = Uri.parse(pictureDirectoryPath);
                        InputStream inputStream;
                        try {
                            inputStream = getContentResolver().openInputStream(imageUri);

                            Bitmap image = BitmapFactory.decodeStream(inputStream);
                            ImageView imgView = (ImageView) findViewById(R.id.picTaken);
                            imgView.setImageBitmap(image);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}