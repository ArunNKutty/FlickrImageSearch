package com.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Base Object. Has only ID for now
 *
 * @author akutty
 */
public class BaseObject implements Parcelable {

    public static final String ID = "_id";

    @SerializedName(ID)
    public String id;

    public BaseObject() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    private BaseObject(Parcel in) {
        this.id = in.readString();
    }

    public static final Creator<BaseObject> CREATOR = new Creator<BaseObject>() {
        public BaseObject createFromParcel(Parcel source) {
            return new BaseObject(source);
        }

        public BaseObject[] newArray(int size) {
            return new BaseObject[size];
        }
    };
}
