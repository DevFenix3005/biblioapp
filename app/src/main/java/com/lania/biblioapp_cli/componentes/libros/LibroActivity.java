package com.lania.biblioapp_cli.componentes.libros;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.lania.biblioapp_cli.MainActivity;
import com.lania.biblioapp_cli.R;
import com.lania.biblioapp_cli.componentes.libros.formulario.LibroFormFragment;
import com.lania.biblioapp_cli.componentes.libros.lista.LibroListFragment;
import com.lania.biblioapp_cli.databinding.ActivityLibroBinding;
import com.lania.biblioapp_cli.entidades.Libro;
import com.lania.biblioapp_cli.provedor.LibroContentProvider;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class LibroActivity extends AppCompatActivity {

    @Getter
    private LibroController libroController;

    private LibroViewModel libroViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libroViewModel = new ViewModelProvider(this).get(LibroViewModel.class);
        libroViewModel.getLibroAdapter().setLibroList(loadCurrentLista());

        ActivityLibroBinding activityLibroBinding = DataBindingUtil.setContentView(this, R.layout.activity_libro);
        activityLibroBinding.setLibroVm(libroViewModel);
        activityLibroBinding.setLifecycleOwner(this);

        this.libroController = new LibroController(this, libroViewModel);

        MaterialToolbar toolbar = findViewById(R.id.libroToolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.goToListView();
    }

    public void goToListView() {
        libroViewModel.getToolbarsubtitle().setValue("Lista");
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .replace(R.id.libroviewport_fragment, LibroListFragment.newInstance())
                .commit();
    }

    public void go2Form() {
        libroViewModel.getToolbarsubtitle().setValue("Registro");
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .addToBackStack(null)
                .replace(R.id.libroviewport_fragment, LibroFormFragment.newInstance())
                .commit();
    }


    private List<Libro> loadCurrentLista() {
        List<Libro> libros = new ArrayList<>();
        try (Cursor cursor = getContentResolver().query(LibroContentProvider.CONTENT_URI_LIBROS, null, null, null, null)) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(Libro.LIBRO_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(Libro.LIBRO_NOMBRE));
                int disponibilidad = cursor.getInt(cursor.getColumnIndexOrThrow(Libro.LIBRO_DISPONIBILIDAD));
                float precio = cursor.getFloat(cursor.getColumnIndexOrThrow(Libro.LIBRO_PRECIO));
                String autor = cursor.getString(cursor.getColumnIndexOrThrow(Libro.LIBRO_AUTOR));
                Libro fetchLibro = Libro.createLibro(id, nombre, disponibilidad, precio, autor);
                libros.add(fetchLibro);
            }
        }
        return libros;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }

}