package com.cameronvoell.articledraftmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "published_article_table")
public class PublishedArticle implements Parcelable {

    public static final String INTENT_EXTRA_NAME = "published.extra.intent.extra";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    public String mTitle;
    @ColumnInfo(name = "body")
    public String mBody;
    @NonNull
    @ColumnInfo(name = "date")
    public long mDate;

    public PublishedArticle(String title, String body, long date){
        mTitle = title;
        mBody = body;
        mDate = date;
    }

    protected PublishedArticle(Parcel in) {
        //publishedId = in.readInt();
        mTitle = in.readString();
        mBody = in.readString();
        mDate = in.readLong();
    }

    public static final Creator<PublishedArticle> CREATOR = new Creator<PublishedArticle>() {
        @Override
        public PublishedArticle createFromParcel(Parcel in) {
            return new PublishedArticle(in);
        }

        @Override
        public PublishedArticle[] newArray(int size) {
            return new PublishedArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeInt(publishedId);
        dest.writeString(mTitle);
        dest.writeString(mBody);
        dest.writeLong(mDate);
    }
}
