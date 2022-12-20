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

/**
 * Activity used to display the detailed information about the image
 */
public class DetailedImageDisplayActivity extends AppCompatActivity {
    private ImageDisplayModel mImageDisplayModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_image_display_layout);

        mImageDisplayModel = getIntent().getParcelableExtra("KEY");
        loadViews();
        createCustomActionBar();
    }

    /**
     * Loads the associated views from the previously obtained activity
     */
    private void loadViews() {
        TextView tempText = findViewById(R.id.temp_text);
        TextView userId = findViewById(R.id.user_id);
        TextView id = findViewById(R.id.id);
        TextView title = findViewById(R.id.title);
        TextView completed = findViewById(R.id.completed);
        ImageView detailedImageView = findViewById(R.id.detailed_image_view);

        tempText.setText(mImageDisplayModel.getMessage());
        userId.setText("User Id: " + (mImageDisplayModel.getUserId()));
        id.setText(R.string.id + (mImageDisplayModel.getId()));
        title.setText(R.string.title + mImageDisplayModel.getTitle());
        if(mImageDisplayModel.getMCompleted()) {
            completed.setText(R.string.completed_true);
        } else {
            completed.setText(R.string.completed_false);
        }

        Picasso.get().load(mImageDisplayModel.getUrl()).into(detailedImageView);
    }

    /**
     * Design for the custom actionbar that the user can use to go back to potentially perform another search
     */
    private void createCustomActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        View customActionBar = findViewById(R.id.custom_action_bar);
        ImageView backButton = customActionBar.findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> finish());
    }

}
