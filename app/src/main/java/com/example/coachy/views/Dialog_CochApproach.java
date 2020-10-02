package com.example.coachy.views;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.coachy.R;
import com.example.coachy.models.Constants;
import com.example.coachy.utils.AppUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class Dialog_CochApproach extends DialogFragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private CheckBox cb_trx, cb_crossfit, cb_functional, cb_aerobic, cb_other, cb_yoga, cb_rubber_band, cb_abs, cb_pilatis;
    private EditText et_phone, et_seniority;
    private LinearLayout bbb;
    private ImageView diploma, close;
    private Uri imageUri;
    private StorageReference approachStorageRef;
    private DatabaseReference approachDatabaseRef;
    private Button approach;
    private StorageTask uploadTask;
    private List<String> specialize;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_coach_approach, container, false);
        initialViews(v);
        onClickListeners();

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        //safety check;
        if (getDialog() == null)
            return;

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.80);
        //change size of the dialog
        getDialog().getWindow().setLayout(width, height);

    }

    private void initialViews(View view) {
        cb_trx = view.findViewById(R.id.checkbox_trx);
        cb_crossfit = view.findViewById(R.id.checkbox_crossfit);
        cb_functional = view.findViewById(R.id.checkbox_functional);
        cb_aerobic = view.findViewById(R.id.checkbox_aerobic);
        cb_other = view.findViewById(R.id.checkbox_other);
        cb_yoga = view.findViewById(R.id.checkbox_yoga);
        cb_yoga = view.findViewById(R.id.checkbox_yoga);
        cb_rubber_band = view.findViewById(R.id.checkbox_band);
        cb_abs = view.findViewById(R.id.checkbox_abs);
        cb_pilatis = view.findViewById(R.id.checkbox_pilatis);
        et_phone = view.findViewById(R.id.et_phone);
        et_seniority = view.findViewById(R.id.et_seniority);
        bbb = view.findViewById(R.id.bbb);
        diploma = view.findViewById(R.id.diploma_image_view);
        close = view.findViewById(R.id.drawer_dismiss_dialog_btn);
        approach = view.findViewById(R.id.btn_approach);
        TextView addDiploa = view.findViewById(R.id.add_diploma_tv);
        TextView title = view.findViewById(R.id.dialod_approach_title);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/BebasNeue-Regular.ttf");
        addDiploa.setTypeface(tf);
        title.setTypeface(tf);

        specialize = new ArrayList<>();
        approachStorageRef = FirebaseStorage.getInstance().getReference("ApproachApplications");

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void onClickListeners() {
        bbb.setOnClickListener(b -> {
            openFileChooser();
        });
        close.setOnClickListener(b -> {
            dismiss();
        });

        //checkboxes
        cb_functional.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_functional);
        });
        cb_aerobic.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_aerobic);
        });
        cb_abs.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_abs);
        });
        cb_crossfit.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_crossfit);
        });
        cb_yoga.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_yoga);
        });
        cb_other.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_other);
        });
        cb_pilatis.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_pilatis);
        });
        cb_trx.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_trx);
        });
        cb_rubber_band.setOnCheckedChangeListener((compoundButton, b) -> {
            setSpecializeForCoach(b, cb_rubber_band);
        });

        //final button
        approach.setOnClickListener(b -> {
            if (uploadTask != null && uploadTask.isInProgress()) {
                AppUtils.showSnackBar(getView(), "Upload in progress");
            } else
                uploadFile();
        });

    }

    //to add or remove the specialize from the list
    private void setSpecializeForCoach(boolean b, CheckBox checkBox) {
        if (b)
            specialize.add(checkBox.getText().toString());
        else
            specialize.remove(checkBox.getText().toString());
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cR.getType(uri));
    }

    //upload the whole application to firebase
    private void uploadFile() {
        if (imageUri != null) {
            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            StorageReference fileReference = approachStorageRef.child(Constants.COACHES).child(currentFirebaseUser.getUid()).child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            fileReference.getDownloadUrl().addOnSuccessListener(uri -> {

                            });
                        }

                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        }
                    })
                    .addOnFailureListener(exception -> {
                        // Handle unsuccessful uploads
                        Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    });



            //this data is after valid check
            approachDatabaseRef = FirebaseDatabase.getInstance().getReference(Constants.APPROACHING_APPLICATIONS);
            approachDatabaseRef.child(currentFirebaseUser.getUid()).child(Constants.SPECIALIZE).setValue(specialize);
            approachDatabaseRef.child(currentFirebaseUser.getUid()).child(Constants.PHONE).setValue(et_phone.getText().toString());
            approachDatabaseRef.child(currentFirebaseUser.getUid()).child(Constants.SENIORITY).setValue(et_seniority.getText().toString());

            Toast.makeText(getContext(), "Approach send succesfully", Toast.LENGTH_SHORT).show();
            dismiss();
            getActivity().findViewById(R.id.create_profile_button).setVisibility(View.GONE);


        } else {
            validCoachAppFields();
        }

    }


    private void validCoachAppFields() {
        //todo change the et color if the check valid or not
        if (et_phone.getText().toString().length() != 10) {
            AppUtils.showSnackBar(getView(), "Not valid phone number");
        } else if (et_seniority.getText().toString().isEmpty()) {
            AppUtils.showSnackBar(getView(), "Please enter seniority");
        } else if (specialize.isEmpty()) {
            AppUtils.showSnackBar(getView(), "Please enter your specialize");
        } else if (imageUri == null) {
            AppUtils.showSnackBar(getView(), "No image selected ");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            imageUri = data.getData();

            diploma.setVisibility(View.VISIBLE);
            Picasso.get().load(imageUri).into(diploma);
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
