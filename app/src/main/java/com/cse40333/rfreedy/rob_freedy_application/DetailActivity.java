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
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
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
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.security.AccessController.getContext;

public class DetailActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    static final int REQUEST_TAKE_PHOTO = 1;
    File photoFile = null;
    String mCurrentPhotoPath;
    String photoName;

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
                dispatchTakePictureIntent();
            }
        });
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        photoName = imageFileName;

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "com.cse40333.rfreedy.rob_freedy_application.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                Uri imageUri = FileProvider.getUriForFile(getApplicationContext(), "com.cse40333.rfreedy.rob_freedy_application", photoFile);
                photoGalleryIntent.setData(imageUri);

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
}