package com.example.sqlroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;
import com.example.sqlroom.Contact;
import java.util.List;
@Dao
public interface ContactDAO {
    @Query("SELECT * FROM Contact")
    List<Contact> getAll();
    @Insert
    void insert(Contact contacts);
    @Delete
    void delete (Contact contacts);
}
