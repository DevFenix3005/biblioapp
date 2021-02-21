package com.lania.biblioapp.componentes.editor;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.lania.biblioapp.MainActivity;
import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.editor.formulario.EditorFormFragment;
import com.lania.biblioapp.componentes.editor.lista.EditorListFragment;
import com.lania.biblioapp.databinding.ActivityEditorBinding;
import com.lania.biblioapp.dominio.entidades.Editor;
import com.lania.biblioapp.provedor.LibroContentProvider;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


public class EditorActivity extends AppCompatActivity {

    @Getter
    private EditorController editorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_editor);

        EditorViewModel editorViewModel = new ViewModelProvider(this).get(EditorViewModel.class);
        editorViewModel.getEditorAdapter().setEditorList(initEditorList());

        binding.setEditorVm(editorViewModel);
        binding.setLifecycleOwner(this);

        editorController = new EditorController(this, editorViewModel);

        MaterialToolbar toolbar = findViewById(R.id.editorToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.goToListView();
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
                .replace(R.id.editor_fragment, EditorListFragment.newInstance())
                .commit();
    }

    public void goToEditorForm() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                )
                .addToBackStack(null)
                .replace(R.id.editor_fragment, EditorFormFragment.newInstance())
                .commit();
    }


    private List<Editor> initEditorList() {
        Cursor cursor = getContentResolver().query(LibroContentProvider.CONTENT_URI_EDITOR, null, null, null, null);
        List<Editor> editors = new ArrayList<>();
        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(Editor._ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(Editor._NOMBRE));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow(Editor._DIRECCION));
            editors.add(Editor.createEditor(id, nombre, direccion));
        }
        cursor.close();
        return editors;
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