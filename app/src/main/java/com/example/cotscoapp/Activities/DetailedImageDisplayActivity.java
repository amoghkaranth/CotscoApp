package com.example.cotscoapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cotscoapp.Models.ImageDisplayModel;
import com.example.cotscoapp.R;
import com.squareup.picasso.Picasso;
import java.util.Objects;

public class DetailedImageDisplayActivity extends AppCompatActivity {
    private ImageDisplayModel mImageDisplayModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_image_display_layout);

        mImageDisplayModel = getIntent().getParcelableExtra("KEY");
        loadViews();
        createCustomActionBar();
    }

    private void loadViews() {
        TextView tempText = findViewById(R.id.temp_text);
        ImageView detailedImageView = findViewById(R.id.detailed_image_view);

        tempText.setText(mImageDisplayModel.getMessage());
        Picasso.get().load(mImageDisplayModel.getUrl()).into(detailedImageView);
    }

    private void createCustomActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        View customActionBar = findViewById(R.id.custom_action_bar);
        ImageView backButton = customActionBar.findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());
    }

}
