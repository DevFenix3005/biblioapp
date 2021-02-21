package com.lania.biblioapp_cli.provedor;

import android.net.Uri;

public class LibroContentProvider {

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

}
