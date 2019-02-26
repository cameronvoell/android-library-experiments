package com.cameronvoell.articledraftmanager.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.cameronvoell.articledraftmanager.R;
import com.cameronvoell.articledraftmanager.data.ArticleDraft;
import com.cameronvoell.articledraftmanager.data.ArticleDraftRepository;
import com.cameronvoell.articledraftmanager.data.PublishedArticle;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditDraftActivity extends AppCompatActivity {

    private EditText mTitleEditTextView;
    private EditText mBodyEditTextView;
    private ArticleDraft mEditedDraft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_draft);

        mTitleEditTextView = findViewById(R.id.editTitle);
        mBodyEditTextView = findViewById(R.id.editBody);

        mEditedDraft = getIntent().getParcelableExtra(ArticleDraft.INTENT_EXTRA_NAME);
        if (mEditedDraft != null) {
            mTitleEditTextView.setText(mEditedDraft.mTitle);
            mBodyEditTextView.setText(mEditedDraft.mBody);
        }
    }

    public void savePost(View view) {
        Toast.makeText(this, "article draft saved", Toast.LENGTH_SHORT).show();
        saveDraftToDatabase();
        finish();
    }

    private void saveDraftToDatabase() {
        mEditedDraft = new ArticleDraft(mTitleEditTextView.getText().toString(),
                mBodyEditTextView.getText().toString(), System.currentTimeMillis());
        ArticleDraftRepository repository = new ArticleDraftRepository(getApplication());
        repository.insert(mEditedDraft);
    }

    public void publishPost(View view) {
        saveDraftToDatabase();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference publisedArticlesRef = database.getReference("publishedArticles");
        DatabaseReference newArticleRef = publisedArticlesRef.push();
        newArticleRef.setValue(new PublishedArticle(mTitleEditTextView.getText().toString(),
                mBodyEditTextView.getText().toString(), System.currentTimeMillis()),
                new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(EditDraftActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
