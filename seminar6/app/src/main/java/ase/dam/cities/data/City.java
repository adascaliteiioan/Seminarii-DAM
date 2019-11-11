package ase.dam.cities.data;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

    private String name;
    private String country;
    private String region;
    private String time;

    public City() {
    }

    public City(String name, String country, String region, String time) {
        this.name = name;
        this.country = country;
        this.region = region;
        this.time = time;
    }

    protected City(Parcel in) {
        name = in.readString();
        country = in.readString();
        region = in.readString();
        time = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(country);
        parcel.writeString(region);
        parcel.writeString(time);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
