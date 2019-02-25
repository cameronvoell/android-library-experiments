package com.cameronvoell.articledraftmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_draft_table")
public class ArticleDraft implements Parcelable {

    public static final String INTENT_EXTRA_NAME = "article.draft.intent.extra";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "draftId")
    public int draftId = 0;

    @NonNull
    @ColumnInfo(name = "title")
    public String mTitle;
    @ColumnInfo(name = "body")
    public String mBody;
    @NonNull
    @ColumnInfo(name = "date")
    public long mDate;

    public ArticleDraft(String title, String body, long date){
        mTitle = title;
        mBody = body;
        mDate = date;
    }

    protected ArticleDraft(Parcel in) {
        draftId = in.readInt();
        mTitle = in.readString();
        mBody = in.readString();
        mDate = in.readLong();
    }

    public static final Creator<ArticleDraft> CREATOR = new Creator<ArticleDraft>() {
        @Override
        public ArticleDraft createFromParcel(Parcel in) {
            return new ArticleDraft(in);
        }

        @Override
        public ArticleDraft[] newArray(int size) {
            return new ArticleDraft[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(draftId);
        dest.writeString(mTitle);
        dest.writeString(mBody);
        dest.writeLong(mDate);
    }
}
