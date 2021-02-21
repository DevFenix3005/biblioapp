package com.lania.biblioapp.componentes.miembro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.lania.biblioapp.MainActivity;
import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.miembro.formulario.MiembroFormFragment;
import com.lania.biblioapp.componentes.miembro.lista.MiembroListFragment;
import com.lania.biblioapp.databinding.ActivityMiembroBinding;
import com.lania.biblioapp.dominio.convertes.DateConvertes;
import com.lania.biblioapp.dominio.entidades.Miembro;
import com.lania.biblioapp.provedor.LibroContentProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class MiembroActivity extends AppCompatActivity {

    @Getter
    private MiembroController miembroController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MiembroViewModel miembroViewModel = new ViewModelProvider(this).get(MiembroViewModel.class);
        miembroViewModel.getMiembroListAdapter().setMiembroList(initMiembrosList());

        ActivityMiembroBinding activityMiembroBinding = DataBindingUtil.setContentView(this, R.layout.activity_miembro);
        activityMiembroBinding.setMiembroVm(miembroViewModel);
        activityMiembroBinding.setLifecycleOwner(this);

        miembroController = new MiembroController(this, miembroViewModel);

        MaterialToolbar toolbar = findViewById(R.id.miembrosToolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        goToListView();

    }

    public void goToListView() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .replace(R.id.miembroviewport_fragment, MiembroListFragment.newInstance())
                .commit();
    }

    public void go2Form() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .addToBackStack(null)
                .replace(R.id.miembroviewport_fragment, MiembroFormFragment.newInstance())
                .commit();
    }


    private List<Miembro> initMiembrosList() {
        return this.getMiembros(null);
    }

    public List<Miembro> getMiembros(String nombreFilterValue) {

        String[] args = null;
        if (nombreFilterValue != null) {
            args = new String[]{nombreFilterValue};
        }

        List<Miembro> miembroList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(LibroContentProvider.CONTENT_URI_MIEMBRO, null, null, args, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(Miembro._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(Miembro._NOMBRE));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow(Miembro._DIRECCION));
            String fechaDeNacimiento = cursor.getString(cursor.getColumnIndexOrThrow(Miembro._FECHA_DE_NACIMIENTO));
            String fechaDeRegistro = cursor.getString(cursor.getColumnIndexOrThrow(Miembro._FECHA_DE_REGISTRO));
            miembroList.add(Miembro.createMiembro(id, nombre, LocalDateTime.parse(fechaDeNacimiento, DateConvertes.FORMATTER), direccion, LocalDateTime.parse(fechaDeRegistro, DateConvertes.FORMATTER)));
        }
        return miembroList;
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