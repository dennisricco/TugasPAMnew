package com.example.sqlroom;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Contact implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "group")
    private String group;
    @ColumnInfo(name = "instagram")
    private String instagram;
    public Contact(String nama, String number, String group, String instagram) {
        this.name = nama;
        this.number = number;
        this.group = group;
        this.instagram = instagram;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getInstagram() {
        return instagram;
    }
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.group);
        dest.writeString(this.instagram);
    }
    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.number = source.readString();
        this.group = source.readString();
        this.instagram = source.readString();
    }
    public Contact() {
    }
    protected Contact(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.number = in.readString();
        this.group = in.readString();
        this.instagram = in.readString();
    }
    public static final Creator<Contact> CREATOR = new
            Creator<Contact>() {
                @Override
                public Contact createFromParcel(Parcel source) {
                    return new Contact(source);
                }
                @Override
                public Contact [] newArray(int size) {
                    return new Contact [size];
                }
            };
}

