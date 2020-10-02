package com.example.coachy.views;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.coachy.R;
import com.example.coachy.adapters.TrainingTypeAdapter;
import com.example.coachy.models.TrainingType;
import com.example.coachy.models.Video;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity implements TrainingTypeAdapter.OnClickSelected{

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_VIDEO_REQUEST = 2;

    private Uri imageUri;
    private Uri videoUri;
    private Video video;
    private VideoView videoView;
    private MediaController mediaController;
    private StorageReference storageRef;
    private DatabaseReference databaseRef;
    private Button uploadPhoto;
    private Button uploadVideo;
    private ProgressBar progressBar;
    private StorageTask uploadTask;
    private ImageView profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        /** write for firebase storage and database for photos **/
//        storageRef = FirebaseStorage.getInstance().getReference("uploads");
//        databaseRef = FirebaseDatabase.getInstance().getReference("coaches");
//
//        profileImage.setOnClickListener(v->{
//            openFileChooser();
//        });
//
//        uploadPhoto.setOnClickListener(v->{
//            if (uploadTask != null && uploadTask.isInProgress()){
//                Toast.makeText(this, "Upload in progress", Toast.LENGTH_SHORT).show();
//            }else
//                uploadFile();
//        });

        /** write for firebase **/
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
//
//
//        DatabaseReference for_shon = myRef.child("for shon");
//        for_shon.setValue("hello shon");
//        System.out.println("key shon" + for_shon.getKey());
//
////         Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot dataSnapshot) {
//                                            // This method is called once with the initial value and again
//                                            // whenever data at this location is updated.
//                                            String value = dataSnapshot.getValue(String.class);
//                                            Log.d(TAG, "Value is: " + value);
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                        }
//                                    }

        /** write video for storage **/
//        video = new Video();
//        storageRef = FirebaseStorage.getInstance().getReference("Videos");
//        databaseRef = FirebaseDatabase.getInstance().getReference("coaches");
//        videoView = findViewById(R.id.video_view);
//        mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        videoView.start();
//        uploadVideo = findViewById(R.id.btn_upload);
//
//        videoView.setOnClickListener(v->{
//            chooseVideo();
//        });
//
//        uploadVideo.setOnClickListener(v->{
//            if (uploadTask != null && uploadTask.isInProgress()){
//                Toast.makeText(this, "Upload in progress", Toast.LENGTH_SHORT).show();
//            }else
//            uploadVideo();
//        });


        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new TrainingTypeFragment()).commit();

    }

    private void uploadVideo() {
        if (videoUri != null){
            StorageReference reference = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(videoUri));

            uploadTask = reference.putFile(videoUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Toast.makeText(MainActivity.this, "yes1", Toast.LENGTH_SHORT).show();
                                    System.out.println("uri " + uri);
                                    databaseRef.child("0").child("video").setValue(uri.toString());
                                }
                            });
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(MainActivity.this, "No1", Toast.LENGTH_SHORT).show();
                            // ...
                        }
                    });

        }else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();

        }
    }


    private void chooseVideo(){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_VIDEO_REQUEST);
    }

    private void uploadFile() {
        if (imageUri != null){
            //coach 1 for the shon coach test
            StorageReference fileReference = storageRef.child("coaches").child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Toast.makeText(MainActivity.this, "yes1", Toast.LENGTH_SHORT).show();
                                    System.out.println("uri " + uri);
                                    databaseRef.child("0").child("profileImage").setValue(uri.toString());
                                }
                            });
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            Toast.makeText(MainActivity.this, "No1", Toast.LENGTH_SHORT).show();
                            // ...
                        }
                    });

        }else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }


    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cR.getType(uri));
    }

    private void initView() {
        SmoothBottomBar bottomNavigationBar = (SmoothBottomBar) findViewById(R.id.bottomBarMain);
//        uploadPhoto = findViewById(R.id.btn_upload);
//        progressBar = findViewById(R.id.progress);
//        profileImage = findViewById(R.id.profile_image);



        bottomNavigationBar.setOnItemSelectedListener(i -> {

            switch (i){
                case 0:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new TrainingTypeFragment()).commit();
                    return true;
                case 1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SearchFragment()).commit();
                    return true;
                case 2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ProfileFragment()).commit();
                    return true;
                default:
                    return false;

            }

        });



    }

    public void replaceFragment(TrainingType trainingType){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right,R.anim.exit_right_to_left)
                .replace(R.id.frame, new TrainingListFragment(trainingType)).addToBackStack("back").commit();

    }


    @Override
    public void onTrainingTypeSelected(TrainingType trainingType) {
        replaceFragment(trainingType);
    }

//    /** for the images **/
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
//            && data.getData() != null){
//            imageUri = data.getData();
//
//
//            Picasso.get().load(imageUri).into(profileImage);
//
//        }else if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null
//                && data.getData() != null){
//
//            videoUri = data.getData();
//
//            videoView.setVideoURI(videoUri);
//
//
//        }else{
//            Toast.makeText(this, "shit", Toast.LENGTH_SHORT).show();
//        }
//    }
}