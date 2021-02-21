package com.lania.biblioapp.dominio.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lania.biblioapp.dominio.entidades.Miembro;

import java.util.List;

@Dao
public interface MiembroDao {

    @Query("SELECT * FROM miembros")
    List<Miembro> getAllMiembros();

    @Query("SELECT * FROM miembros")
    Cursor getAllMiembrosByCursor();

    @Query("SELECT * FROM miembros WHERE upper(nombre) like :nombre")
    Cursor findMiembroByName(String nombre);

    @Insert
    Long insertOne(Miembro miembro);

    @Insert
    List<Long> insertAll(Miembro... miembros);

    @Update
    void updateMiembro(Miembro miembro);

    @Delete
    void deleteMiembro(Miembro miembro);

    @Query("SELECT count(*) FROM miembros")
    Long count();

    @Query("DELETE FROM miembros WHERE id = :id")
    void deleteById(Long id);
}
