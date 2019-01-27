package com.example.colleaugeinfo.room;

import com.example.colleaugeinfo.model.Colleague;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ColleagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Colleague colleague);

    @Update
    void update(Colleague colleague);

    @Delete
    void delete(Colleague colleague);

    @Query("select * from colleague order by name")
    LiveData<List<Colleague>> getAllColleague();


}
