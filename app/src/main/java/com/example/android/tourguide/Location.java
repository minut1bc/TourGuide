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

    private String name;
    private String address;
    private String startHour;
    private String stopHour;
    private float rating;
    private int imageResourceId;

    public Location(String name, String address, String startHour, String stopHour, float rating, int imageResourceId) {
        this.name = name;
        this.address = address;
        this.startHour = startHour;
        this.stopHour = stopHour;
        this.rating = rating;
        this.imageResourceId = imageResourceId;
    }

    // In constructor you will read the variables from Parcel. Make sure to read them in the same sequence in which you have written them in Parcel.

    private Location(Parcel in) {
        name = in.readString();
        address = in.readString();
        startHour = in.readString();
        stopHour = in.readString();
        rating = in.readFloat();
        imageResourceId = in.readInt();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getStartHour() {
        return startHour;
    }

    public String getStopHour() {
        return stopHour;
    }

    public float getRating() {
        return rating;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    boolean isOpen() {

        if (startHour.equals("Non") && stopHour.equals("Stop"))
            return true;

        Calendar calendar = Calendar.getInstance();

        int currentTime = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);

        int startTime = Integer.parseInt(startHour.substring(0, 2)) * 60 + Integer.parseInt(startHour.substring(3, 5));

        int stopTime = Integer.parseInt(stopHour.substring(0, 2)) * 60 + Integer.parseInt(stopHour.substring(3, 5));

        if (startHour.substring(6, 8).equals("PM") && !startHour.substring(0, 2).equals("12"))
            startTime += 720;

        if (stopHour.substring(6, 8).equals("PM") && !stopHour.substring(0, 2).equals("12"))
            stopTime += 720;

        if (startHour.substring(6, 8).equals("PM") && stopHour.substring(6, 8).equals("AM"))
            if ((currentTime >= startTime && currentTime <= 1440) || (currentTime >= 0 && currentTime <= stopTime))
                return true;

        if (startHour.substring(6, 8).equals("AM") && stopHour.substring(6, 8).equals("AM") && (currentTime >= startTime || currentTime <= stopTime))
            return true;

        if (startHour.substring(6, 8).equals("PM") && stopHour.substring(6, 8).equals("PM") && (currentTime >= startTime || currentTime <= stopTime))
            return true;

        if (startHour.substring(6, 8).equals("AM") && stopHour.equals("00:00 AM"))
            if (currentTime >= startTime && currentTime <= 1440)
                return true;

        return currentTime >= startTime && currentTime <= stopTime;
    }

    // This is to de-serialize the object

    public int describeContents() {
        return 0;
    }

    // This is where you will write your member variables in Parcel. Here you can write in any order. It is not necessary to write all members in Parcel.

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Write data in any order
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(startHour);
        dest.writeString(stopHour);
        dest.writeFloat(rating);
        dest.writeInt(imageResourceId);
    }
}
