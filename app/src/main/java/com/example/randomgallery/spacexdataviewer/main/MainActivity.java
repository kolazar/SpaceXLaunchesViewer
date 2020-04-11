package com.example.randomgallery.spacexdataviewer.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.randomgallery.spacexdataviewer.R;

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

        ViewModelProvider viewModelProvider = new ViewModelProvider((ViewModelStoreOwner) this);
        viewModel = viewModelProvider.get(MainViewModel.class);

        viewModel.getViewState().observe(this,state ->{
            getButton.setVisibility(toVisibility(state.isEnableGetButton()));
            launchesList.setVisibility(toVisibility(state.isShowList()));
            progress.setVisibility(toVisibility(state.isShowProgress()));
            emptyTextView.setVisibility(toVisibility(state.isShowEmptyHint()));
            errorTextView.setVisibility(toVisibility(state.isShowError()));
        });

        getButton.setOnClickListener(v->{
            viewModel.getLaunches();
        });
    }

    private void initLaunchesList(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        launchesList.setLayoutManager(layoutManager);

        adapter = new LaunchesAdapter();

        launchesList.setAdapter(adapter);
    }

    static int toVisibility(boolean show){
        return show ? View.VISIBLE : View.GONE;
    }
}
