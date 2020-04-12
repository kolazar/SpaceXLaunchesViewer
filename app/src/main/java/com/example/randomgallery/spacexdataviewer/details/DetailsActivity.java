package com.example.randomgallery.spacexdataviewer.details;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.randomgallery.spacexdataviewer.App;
import com.example.randomgallery.spacexdataviewer.R;
import com.example.randomgallery.spacexdataviewer.model.Launch;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_LAUNCH_ID = "LAUNCH_ID";

    private TextView nameTextView;
    private TextView descriptionTextView;
    private ProgressBar progressBar;

    private DetailsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameTextView = findViewById(R.id.nameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        progressBar = findViewById(R.id.progress);

        long launchId = getIntent().getLongExtra(EXTRA_LAUNCH_ID,-1);
        if(launchId == -1){
            throw new RuntimeException(("There is no repository ID"));
        }

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this,app.getViewModelFactory());
        viewModel = viewModelProvider.get(DetailsViewModel.class);

        viewModel.loadLaunchById(launchId);

        viewModel.getResults().observe(this,result ->{
            switch (result.getStatus()){
                case SUCCESS:
                    Launch launch = result.getData();
                    nameTextView.setText(launch.getMissionName());
                    descriptionTextView.setText(launch.getDetails());
                    progressBar.setVisibility(View.GONE);
                    break;
                case EMPTY:
                    nameTextView.setText("");
                    descriptionTextView.setText("");
                    progressBar.setVisibility(View.GONE);
                    break;
                case LOADING:
                    nameTextView.setText("");
                    descriptionTextView.setText("");
                    progressBar.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }
}
