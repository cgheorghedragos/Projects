package com.example.greenlight.presentation.views.application.map;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlight.R;
import com.example.greenlight.data.requests.RequestCodes;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.presentation.viewmodel.MapViewModel;
import com.example.greenlight.presentation.views.application.map.adapters.AddIncidentPhotosAdapter;
import com.example.greenlight.presentation.views.dialogs.LoadingDialog;
import com.example.greenlight.utils.Converters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class ReportDetailFragment extends Fragment {
    @Inject
    public MapViewModel mapViewModel;

    private EditText description;
    private RecyclerView recyclerView;
    private AddIncidentPhotosAdapter recycleViewAdapter;
    private ImageView imageAddButton;
    private ImageView problemIcon;
    private ImageView declineButton;
    private ImageView placeTheMaker;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_report_detail, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupView(view);
        setupListeners(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.report_event_green));
    }

    private void setupView(@NonNull View view) {
        imageAddButton = view.findViewById(R.id.imageButton);
        loadingDialog = new LoadingDialog(view.getContext());
        description = view.findViewById(R.id.descriptionIdFromDetail);
        declineButton = view.findViewById(R.id.decline_from_adding_incident);
        problemIcon = view.findViewById(R.id.problem_icon_detail);
        placeTheMaker = view.findViewById(R.id.placeTheMarker);

        problemIcon.setImageResource(Converters.getMarkerIcon(mapViewModel.getMarker().getValue().getMarkerType()));

        recyclerView = view.findViewById(R.id.recycleViewIncidents);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recycleViewAdapter = new AddIncidentPhotosAdapter(new ArrayList<>());

        recyclerView.setAdapter(recycleViewAdapter);
    }


    private void setupListeners(@NonNull View view) {
        description.addTextChangedListener(descriptionWatcher);


        imageAddButton.setOnClickListener( v -> {
            uploadPhoto();
        });

        declineButton.setOnClickListener( v -> {
            Navigation.findNavController(v).navigate(R.id.action_reportDetailFragment_to_googleMapFragment2);
        });

        placeTheMaker.setOnClickListener( v -> {
            mapViewModel.uploadIncident(mapViewModel.getAuthToken());
            Navigation.findNavController(v).navigate(R.id.action_reportDetailFragment_to_googleMapFragment2);
        });

        mapViewModel.getSendPhotoResult().observe(requireActivity(), photos -> {
            switch (photos.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    loadingDialog.showDialog();
                    break;
                case SUCCESS:
                    loadingDialog.hideDialog();
                    Toast.makeText(getContext(), "Successfully loaded photo", Toast.LENGTH_LONG).show();
                    recycleViewAdapter.updateList(photos.getData().getResponse());
                    Log.e("PHOTOS",photos.getData().getResponse().toString());
                    break;
                case ERROR:
                    loadingDialog.hideDialog();
                    Toast.makeText(getContext(), "Unable to reach connection, " +
                            "please ensure the access to the internet or try again later.", Toast.LENGTH_LONG).show();
                    Log.e("PHOTOS",photos.getError().toString());
                    break;
            }
        });


    }

    private void uploadPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,RequestCodes.PICK_MULTIPLE_PHOTOS);
    }

    private MultipartBody.Part prepareFile(File file){
        RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));

//                // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getName(), requestFile);

            return body;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCodes.PICK_MULTIPLE_PHOTOS && resultCode == RESULT_OK && data != null) {
            List<File> fileList = getFileListFromUri(data);

            List<MultipartBody.Part> body = new ArrayList<>();
            for(File file:fileList){
                body.add(prepareFile(file));
            }

            mapViewModel.uploadPhotos(body,mapViewModel.getAuthToken());
        }
    }


    public List<File> getFileListFromUri(Intent data) {
        List<String> imagePathList = new ArrayList<>();
        List<File> files = new ArrayList<>();
        if (data.getClipData() != null) {

            int count = data.getClipData().getItemCount();
            for (int i = 0; i < count; i++) {
                Uri imageUri = data.getClipData().getItemAt(i).getUri();
                if (getImageFilePath(imageUri) != null) {
                    imagePathList.add(getImageFilePath(imageUri));
                }
            }
        } else if (data.getData() != null) {
            Uri imgUri = data.getData();
            if (getImageFilePath(imgUri) != null) {
                imagePathList.add(getImageFilePath(imgUri));
            }
        }

        for(String imagePath : imagePathList){
            files.add(new File(imagePath));
        }
        return files;
    }

    @SuppressLint("Range")
    public String getImageFilePath(Uri uri) {
        String imagePath;

        File file = new File(uri.getPath());
        String[] filePath = file.getPath().split(":");
        String image_id = filePath[filePath.length - 1];

        Cursor cursor = requireContext().getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        if (cursor != null) {
            cursor.moveToFirst();
            imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            return imagePath;
        }
        return null;
    }


    private TextWatcher descriptionWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mapViewModel.updateDescription(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}