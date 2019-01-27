package com.example.colleaugeinfo.room;

import android.content.Context;

import com.example.colleaugeinfo.model.Colleague;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Colleague.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDb ;

    public abstract ColleagueDao colleagueDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(appDb == null){
            appDb = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "colleagueinfo")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return appDb ;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
