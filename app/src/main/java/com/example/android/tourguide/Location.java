package com.example.android.tourguide;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Location implements Parcelable {

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
    private String mName;
    private String mAddress;
    private String mStartHour;
    private String mStopHour;
    private float mRating;
    private int mImageResourceId;

    public Location(String name, String address, String startHour, String stopHour, float rating, int imageResourceId) {
        mName = name;
        mAddress = address;
        mStartHour = startHour;
        mStopHour = stopHour;
        mRating = rating;
        mImageResourceId = imageResourceId;
    }

    private Location(Parcel in) {
        mName = in.readString();
        mAddress = in.readString();
        mStartHour = in.readString();
        mStopHour = in.readString();
        mRating = in.readFloat();
        mImageResourceId = in.readInt();
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getStartHour() {
        return mStartHour;
    }

    public String getStopHour() {
        return mStopHour;
    }

    public float getRating() {
        return mRating;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }


    // In constructor you will read the variables from Parcel. Make sure to read them in the same sequence in which you have written them in Parcel.

    boolean isOpen() {

        Calendar calendar = Calendar.getInstance();
        int currentTime = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);

        if (mStartHour.equals("Non") && mStopHour.equals("Stop"))
            return true;
        else {

            int startTime = Integer.parseInt(mStartHour.substring(0, 2)) * 60 + Integer.parseInt(mStartHour.substring(3, 5));

            if (mStartHour.substring(6, 8).equals("PM") && !mStartHour.substring(0, 2).equals("12"))
                startTime += 720;

            int stopTime = Integer.parseInt(mStopHour.substring(0, 2)) * 60 + Integer.parseInt(mStopHour.substring(3, 5));

            if (mStopHour.substring(6, 8).equals("PM") && !mStopHour.substring(0, 2).equals("12"))
                stopTime += 720;

            if (mStartHour.substring(6, 8).equals("PM") && mStopHour.substring(6, 8).equals("AM"))
                if ((currentTime >= startTime && currentTime <= 1440) || (currentTime >= 0 && currentTime <= stopTime))
                    return true;

            if (mStartHour.substring(6, 8).equals("AM") && mStopHour.substring(6, 8).equals("AM") && (currentTime >= startTime || currentTime <= stopTime))
                return true;

            if (mStartHour.substring(6, 8).equals("PM") && mStopHour.substring(6, 8).equals("PM") && (currentTime >= startTime || currentTime <= stopTime))
                return true;

            if (mStartHour.substring(6, 8).equals("AM") && mStopHour.equals("00:00 AM"))
                if (currentTime >= startTime && currentTime <= 1440)
                    return true;

            return currentTime >= startTime && currentTime <= stopTime;
        }
    }

    // This is to de-serialize the object

    public int describeContents() {
        return 0;
    }

    // This is where you will write your member variables in Parcel. Here you can write in any order. It is not necessary to write all members in Parcel.

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Write data in any order
        dest.writeString(mName);
        dest.writeString(mAddress);
        dest.writeString(mStartHour);
        dest.writeString(mStopHour);
        dest.writeFloat(mRating);
        dest.writeInt(mImageResourceId);
    }
}
