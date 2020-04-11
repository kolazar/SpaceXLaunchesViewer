package com.example.randomgallery.spacexdataviewer.details;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.randomgallery.spacexdataviewer.App;
import com.example.randomgallery.spacexdataviewer.R;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_LAUNCH_ID = "LAUNCH_ID";

    private TextView nameTextView;
    private TextView descriptionTextView;

    private DetailsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);

        long repositoryId = getIntent().getLongExtra(EXTRA_LAUNCH_ID,-1);
        if(repositoryId == -1){
            throw new RuntimeException(("There is no repository ID"));
        }

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this,app.getViewModelFactory());
        viewModel = viewModelProvider.get(DetailsViewModel.class);
    }
}
