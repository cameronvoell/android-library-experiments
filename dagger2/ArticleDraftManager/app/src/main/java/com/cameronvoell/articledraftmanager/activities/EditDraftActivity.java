package com.cameronvoell.articledraftmanager.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.cameronvoell.articledraftmanager.R;
import com.cameronvoell.articledraftmanager.data.ArticleDraft;
import com.cameronvoell.articledraftmanager.data.ArticleDraftRepository;
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
        ArticleDraftRepository repository = new ArticleDraftRepository(getApplication());
        if (mEditedDraft == null) {
            repository.insert(new ArticleDraft(mTitleEditTextView.getText().toString(),
                    mBodyEditTextView.getText().toString(), System.currentTimeMillis()));
        } else {
            mEditedDraft.mTitle = mTitleEditTextView.getText().toString();
            mEditedDraft.mBody = mBodyEditTextView.getText().toString();
            mEditedDraft.mDate = System.currentTimeMillis();
            repository.insert(mEditedDraft);
        }
        finish();
    }
}
