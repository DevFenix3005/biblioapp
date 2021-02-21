package com.lania.biblioapp.dominio.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lania.biblioapp.dominio.entidades.Editor;

import java.util.List;

@Dao
public interface EditorDao {

    @Query("SELECT * FROM editores")
    List<Editor> getAllEditores();

    @Query("SELECT * FROM editores")
    Cursor getAllEditoresByCursor();

    @Query("SELECT count(*) FROM editores")
    Long count();

    @Insert
    List<Long> insertAll(Editor... editores);

    @Insert
    Long insertOne(Editor editor);

    @Delete
    void deleteEditores(Editor editor);

    @Query("DELETE FROM editores WHERE id = :id")
    void deleteById(Long id);
}
