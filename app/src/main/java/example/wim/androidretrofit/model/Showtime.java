package example.wim.androidretrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Wim on 7/19/16.
 */
public class Showtime implements Parcelable {

    private String bioskop;
    private List<String> jam;
    private String harga;
    public Showtime() {
    }

    protected Showtime(Parcel in) {
        this.bioskop = in.readString();
        this.jam = in.createStringArrayList();
        this.harga = in.readString();
    }

    public static final Parcelable.Creator<Showtime> CREATOR = new Parcelable.Creator<Showtime>() {
        @Override
        public Showtime createFromParcel(Parcel source) {
            return new Showtime(source);
        }

        @Override
        public Showtime[] newArray(int size) {
            return new Showtime[size];
        }
    };

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }

    public List<String> getJam() {
        return jam;
    }

    public void setJam(List<String> jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bioskop);
        dest.writeStringList(this.jam);
        dest.writeString(this.harga);
    }


}
