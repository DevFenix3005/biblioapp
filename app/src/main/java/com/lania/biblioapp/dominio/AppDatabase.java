package com.lania.biblioapp.dominio;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.lania.biblioapp.dominio.convertes.DateConvertes;
import com.lania.biblioapp.dominio.dao.EditorDao;
import com.lania.biblioapp.dominio.dao.LibroDao;
import com.lania.biblioapp.dominio.dao.MiembroDao;
import com.lania.biblioapp.dominio.entidades.Editor;
import com.lania.biblioapp.dominio.entidades.Libro;
import com.lania.biblioapp.dominio.entidades.Miembro;

@Database(entities = {Libro.class, Miembro.class, Editor.class}, version = 7, exportSchema = false)
@TypeConverters({DateConvertes.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract LibroDao libroDao();

    public abstract MiembroDao miembroDao();

    public abstract EditorDao editorDao();

}
