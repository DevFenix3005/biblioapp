package com.lania.biblioapp_cli.componentes.libros;

import android.content.ContentResolver;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.lania.biblioapp_cli.R;
import com.lania.biblioapp_cli.componentes.libros.lista.LibrosListAdapter;
import com.lania.biblioapp_cli.entidades.Libro;
import com.lania.biblioapp_cli.provedor.LibroContentProvider;

import java.util.Locale;

import lombok.Data;
import lombok.NonNull;
import ru.rambler.libs.swipe_layout.SwipeLayout;

@Data

public class LibroController implements View.OnClickListener, SwipeLayout.OnSwipeListener {

    @NonNull
    private final LibroActivity libroActivity;

    @NonNull
    private final LibroViewModel model;

    @NonNull
    private final ContentResolver content;

    public LibroController(@NonNull LibroActivity libroActivity, @NonNull LibroViewModel model) {
        this.libroActivity = libroActivity;
        this.model = model;
        this.content = libroActivity.getContentResolver();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_libro:
                registrarLibro();
                break;
            case R.id.libro_floatingbutton_go2form:
                libroActivity.go2Form();
                break;
        }
    }


    private void registrarLibro() {

        Libro libro = this.model.createEntityFromView();
        Uri uriInsert = content.insert(LibroContentProvider.CONTENT_URI_LIBROS, libro.toContentValues());

        String newLibroId = uriInsert.getLastPathSegment();
        libro.setId(Long.parseLong(newLibroId));


        String mensaje = String.format(Locale.getDefault(), "El libro '%s' fue creado y se le asigno el folio %s", libro.getNombre(), newLibroId);
        Toast.makeText(this.libroActivity, mensaje, Toast.LENGTH_LONG).show();
        LibrosListAdapter libroAdapter = this.model.getLibroAdapter();
        libroAdapter.add(libro);
        model.clean();

        FragmentManager fragmentManager = this.libroActivity.getSupportFragmentManager();
        fragmentManager.popBackStack();
    }


    private void borrarLibro(Libro libro) {

        String mensaje = String.format(Locale.getDefault(), "El libro '%s' fue borrado", libro.getNombre());
        Toast.makeText(this.libroActivity, mensaje, Toast.LENGTH_LONG).show();

        String uri = LibroContentProvider.CONTENT_URI_LIBROS.toString();
        Uri deleteLibroUri = Uri.parse(uri + "/" + libro.getId());

        int q = content.delete(deleteLibroUri, null, null);
        LibrosListAdapter libroAdapter = this.model.getLibroAdapter();
        libroAdapter.remove(libro);

    }


    @Override
    public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {

    }

    @Override
    public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
        if (!moveToRight) {
            Libro editor = (Libro) swipeLayout.getTag();
            this.borrarLibro(editor);
        }

    }

    @Override
    public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

    }

    @Override
    public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

    }
}
