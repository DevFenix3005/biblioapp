package com.lania.biblioapp.provedor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.lania.biblioapp.dominio.AppDatabase;
import com.lania.biblioapp.dominio.dao.EditorDao;
import com.lania.biblioapp.dominio.dao.LibroDao;
import com.lania.biblioapp.dominio.dao.MiembroDao;
import com.lania.biblioapp.dominio.entidades.Editor;
import com.lania.biblioapp.dominio.entidades.Libro;
import com.lania.biblioapp.dominio.entidades.Miembro;

public class LibroContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.lania.biblioapp.provedor.LibroContentProvider";
    public static final String URL = "content://" + PROVIDER_NAME;

    public static final String LIBRO_RESOURCES = "libro";
    public static final String LIBRO_ID_RESOURCES = "libro/#";
    public static final String MIEMBRO_RESOURCES = "miembro";
    public static final String MIEMBRO_ID_RESOURCES = "miembro/#";
    public static final String EDITOR_RESOURCES = "editor";
    public static final String EDITOR_ID_RESOURCES = "editor/#";
    public static final String COUNT_RESOURCES = "count";

    public static final String CONTENT_STR_URL_LIBROS = URL + "/" + LIBRO_RESOURCES;
    public static final String CONTENT_STR_URL_MIEMBRO = URL + "/" + MIEMBRO_RESOURCES;
    public static final String CONTENT_STR_URL_EDITOR = URL + "/" + EDITOR_RESOURCES;

    public static final Uri CONTENT_URI_LIBROS = Uri.parse(CONTENT_STR_URL_LIBROS);
    public static final Uri CONTENT_URI_MIEMBRO = Uri.parse(CONTENT_STR_URL_MIEMBRO);
    public static final Uri CONTENT_URI_EDITOR = Uri.parse(CONTENT_STR_URL_EDITOR);
    public static final Uri CONTENT_URI_COUNT = Uri.parse(URL + "/" + COUNT_RESOURCES);

    public static final UriMatcher URI_MATCHER;
    public static final String DATABASE_NAME = "biblioteca";

    public static final int LIBRO_CODE = 1;
    public static final int LIBRO_CODE_ID = 10;
    public static final int COUNT_CODE = 2;
    public static final int MIEMBRO_CODE = 3;
    public static final int MIEMBRO_CODE_ID = 30;
    public static final int EDITOR_CODE = 4;
    public static final int EDITOR_CODE_ID = 40;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(PROVIDER_NAME, LIBRO_RESOURCES, LIBRO_CODE);
        URI_MATCHER.addURI(PROVIDER_NAME, LIBRO_ID_RESOURCES, LIBRO_CODE_ID);

        URI_MATCHER.addURI(PROVIDER_NAME, MIEMBRO_RESOURCES, MIEMBRO_CODE);
        URI_MATCHER.addURI(PROVIDER_NAME, MIEMBRO_ID_RESOURCES, MIEMBRO_CODE_ID);

        URI_MATCHER.addURI(PROVIDER_NAME, EDITOR_RESOURCES, EDITOR_CODE);
        URI_MATCHER.addURI(PROVIDER_NAME, EDITOR_ID_RESOURCES, EDITOR_CODE_ID);

        URI_MATCHER.addURI(PROVIDER_NAME, COUNT_RESOURCES, COUNT_CODE);
    }


    private AppDatabase appDatabase;
    private Context context;

    @Override
    public boolean onCreate() {
        context = getContext();
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        SupportSQLiteStatement qry0 = appDatabase.compileStatement("SELECT 1 + 1");
        long result = qry0.simpleQueryForLong();

        return result == 2;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        final int matcherCode = URI_MATCHER.match(uri);

        switch (matcherCode) {
            case LIBRO_CODE:
                LibroDao libroDao = appDatabase.libroDao();
                Cursor cursor = libroDao.getAllLibrosByCursor();
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            case MIEMBRO_CODE:
                MiembroDao miembroDao = appDatabase.miembroDao();
                if (selectionArgs != null) {
                    String nombre = "%" + selectionArgs[0].toUpperCase() + "%";
                    return miembroDao.findMiembroByName(nombre);
                } else {
                    return miembroDao.getAllMiembrosByCursor();
                }
            case EDITOR_CODE:
                EditorDao editorDao = appDatabase.editorDao();
                return editorDao.getAllEditoresByCursor();
            case COUNT_CODE:
                String qry1 = "select ";
                qry1 += "(select count(*) from libros) as countLibros, ";
                qry1 += "(select count(*) from miembros) as countMiembros, ";
                qry1 += "(select count(*) from editores) as countEditores ";
                System.out.println(qry1);
                return appDatabase.query(qry1, null);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (URI_MATCHER.match(uri)) {
            case LIBRO_CODE:
            case MIEMBRO_CODE:
            case EDITOR_CODE:
                return PROVIDER_NAME + "/dir";
            case COUNT_CODE:
            case LIBRO_CODE_ID:
            case MIEMBRO_CODE_ID:
            case EDITOR_CODE_ID:
                return PROVIDER_NAME + "/item";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @NonNull ContentValues values) {
        switch (URI_MATCHER.match(uri)) {
            case LIBRO_CODE:
                LibroDao libroDao = appDatabase.libroDao();
                Libro libro = Libro.createLibroByContentValues(values);
                Long idLibro = libroDao.insertOne(libro);
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, idLibro);
            case MIEMBRO_CODE:
                MiembroDao miembroDao = appDatabase.miembroDao();
                Miembro miembro = Miembro.createMiembroByContentValues(values);
                Long idMiembro = miembroDao.insertOne(miembro);
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, idMiembro);
            case EDITOR_CODE:
                EditorDao editorDao = appDatabase.editorDao();
                Editor editor = Editor.createEditorByContentValues(values);
                Long editorId = editorDao.insertOne(editor);
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, editorId);
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String id = uri.getLastPathSegment();
        switch (URI_MATCHER.match(uri)) {
            case LIBRO_CODE_ID:
                LibroDao libroDao = appDatabase.libroDao();
                libroDao.deleteBookById(Long.parseLong(id));
                context.getContentResolver().notifyChange(uri, null);
                return 1;
            case MIEMBRO_CODE_ID:
                MiembroDao miembroDao = appDatabase.miembroDao();
                miembroDao.deleteById(Long.parseLong(id));
                context.getContentResolver().notifyChange(uri, null);
                return 1;
            case EDITOR_CODE_ID:
                EditorDao editorDao = appDatabase.editorDao();
                editorDao.deleteById(Long.parseLong(id));
                context.getContentResolver().notifyChange(uri, null);
                return 1;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String id = uri.getLastPathSegment();
        switch (URI_MATCHER.match(uri)) {
            case MIEMBRO_CODE_ID:
                Miembro miembro = Miembro.createMiembroByContentValues(values);
                miembro.setId(Long.parseLong(id));
                MiembroDao miembroDao = appDatabase.miembroDao();
                miembroDao.updateMiembro(miembro);
                context.getContentResolver().notifyChange(uri, null);
                return 1;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
