package com.example.randomgallery.spacexdataviewer.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.randomgallery.spacexdataviewer.App;
import com.example.randomgallery.spacexdataviewer.R;
import com.example.randomgallery.spacexdataviewer.details.DetailsActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView launchesList;
    private ProgressBar progress;
    private TextView emptyTextView;
    private TextView  errorTextView;
    private Button getButton;

    private MainViewModel viewModel;

    private LaunchesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getButton = findViewById(R.id.getButton);
        launchesList = findViewById(R.id.launchesList);
         progress = findViewById(R.id.progress);
         emptyTextView = findViewById(R.id.emptyTextView);
         errorTextView = findViewById(R.id.errorTextView);

        App app = (App) getApplication();
         ViewModelProvider viewModelProvider = new ViewModelProvider(this,app.getViewModelFactory());
        viewModel = viewModelProvider.get(MainViewModel.class);

        viewModel.getViewState().observe(this,state ->{
            getButton.setVisibility(toVisibility(state.isEnableGetButton()));
            launchesList.setVisibility(toVisibility(state.isShowList()));
            progress.setVisibility(toVisibility(state.isShowProgress()));
            emptyTextView.setVisibility(toVisibility(state.isShowEmptyHint()));
            errorTextView.setVisibility(toVisibility(state.isShowError()));

            adapter.setLaunches(state.getLaunches());
        });

        getButton.setOnClickListener(v->{
            viewModel.getLaunches();
        });

        initLaunchesList();
    }

    private void initLaunchesList(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        launchesList.setLayoutManager(layoutManager);

        adapter = new LaunchesAdapter(launch -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_LAUNCH_ID, launch.getFlightNumber());
            startActivity(intent);
        });

        launchesList.setAdapter(adapter);
    }

    static int toVisibility(boolean show){
        return show ? View.VISIBLE : View.GONE;
    }
}
