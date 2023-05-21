package com.example.sqlroom;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.sqlroom.Contact;
import com.example.sqlroom.ContactDatabase;
@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase sIntance;
    private static MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    @VisibleForTesting
    public static final String DATABASE_NAME = "my_database";
    public abstract ContactDAO ContactDAO();
    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }
    private void updateDatabaseCreated(final Context context){
        if(context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }
    public static ContactDatabase buildDatabase(final Context context){
        return Room.databaseBuilder(context,
                ContactDatabase.class, DATABASE_NAME).addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        ContactDatabase database = ContactDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }}).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
    public static ContactDatabase getInstance(final Context context){
        if (sIntance == null){
            synchronized (ContactDatabase.class){
                if (sIntance == null){
                    sIntance = buildDatabase(context);
                    sIntance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sIntance;
    }
}

