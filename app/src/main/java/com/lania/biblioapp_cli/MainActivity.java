package com.lania.biblioapp_cli;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.lania.biblioapp_cli.componentes.editor.EditorActivity;
import com.lania.biblioapp_cli.componentes.libros.LibroActivity;
import com.lania.biblioapp_cli.componentes.miembro.MiembroActivity;
import com.lania.biblioapp_cli.provedor.LibroContentProvider;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topBar);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        updateUi();
    }

    private void updateUi() {

        long libroCount = 0L;
        long miembroCount = 0L;
        long editorCount = 0L;

        try (Cursor cursor = getContentResolver().query(LibroContentProvider.CONTENT_URI_COUNT, null, null, null, null)) {
            if (cursor.moveToNext()) {
                libroCount = cursor.getLong(0);
                miembroCount = cursor.getLong(1);
                editorCount = cursor.getLong(2);
            }
        }


        TextView libroCounterLabel = findViewById(R.id.libros_counter_label);
        TextView miembroCounterLabel = findViewById(R.id.miembro_counter_label);
        TextView editorCounterLabel = findViewById(R.id.editores_counter_label);

        Resources res = getResources();

        libroCounterLabel.setText(String.format(Locale.getDefault(), res.getString(R.string.libros_registrados_1_s), libroCount));
        miembroCounterLabel.setText(String.format(Locale.getDefault(), res.getString(R.string.miembros_registrados_1_s), miembroCount));
        editorCounterLabel.setText(String.format(Locale.getDefault(), res.getString(R.string.editores_registrados_1_s), editorCount));
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.item_libro:
                intent = new Intent(MainActivity.this, LibroActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_editor:
                intent = new Intent(MainActivity.this, MiembroActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_miembro:
                intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
}