package com.lania.biblioapp.componentes.editor;

import android.content.ContentResolver;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.editor.lista.EditorAdapter;
import com.lania.biblioapp.dominio.entidades.Editor;
import com.lania.biblioapp.provedor.LibroContentProvider;

import java.util.Locale;

import lombok.Data;
import lombok.NonNull;
import ru.rambler.libs.swipe_layout.SwipeLayout;

@Data
public class EditorController implements View.OnClickListener, SwipeLayout.OnSwipeListener {

    @NonNull
    private final EditorActivity activity;
    @NonNull
    private final EditorViewModel editorViewModel;
    @NonNull
    private final ContentResolver contentResolver;

    public EditorController(@NonNull EditorActivity activity, @NonNull EditorViewModel editorViewModel) {
        this.activity = activity;
        this.editorViewModel = editorViewModel;
        this.contentResolver = activity.getContentResolver();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register_editor:
                registarEditor();
                break;
            case R.id.editor_floatingbutton_go2form:
                this.activity.goToEditorForm();
                break;
        }
    }

    private void borrarEditor(Editor editor) {
        Toast.makeText(this.activity, String.format(Locale.getDefault(), "Se a borrado el editor %s con el di %d", editor.getNombre(), editor.getId()), Toast.LENGTH_LONG).show();

        Uri deleteUri = Uri.parse(LibroContentProvider.CONTENT_STR_URL_EDITOR + "/" + editor.getId());
        int q = this.contentResolver.delete(deleteUri, null, null);

        EditorAdapter editorAdapter = editorViewModel.getEditorAdapter();
        editorAdapter.remove(editor);

    }

    private void registarEditor() {
        Editor editor = editorViewModel.createEditorByViewModel();

        Uri editorInsertUri = this.contentResolver.insert(LibroContentProvider.CONTENT_URI_EDITOR, editor.toContetValues());
        String newId = editorInsertUri.getLastPathSegment();
        Long id = Long.parseLong(newId);
        editor.setId(id);

        Toast.makeText(this.activity, String.format(Locale.getDefault(), "Se agrego el editor %s con el di %s", editor.getNombre(), newId), Toast.LENGTH_LONG).show();
        EditorAdapter editorAdapter = editorViewModel.getEditorAdapter();
        editorAdapter.add(editor);
        this.editorViewModel.clean();

        FragmentManager fragmentManager = this.activity.getSupportFragmentManager();
        fragmentManager.popBackStack();

    }

    @Override
    public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {

    }

    @Override
    public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
        if (!moveToRight) {
            Editor editor = (Editor) swipeLayout.getTag();
            this.borrarEditor(editor);
        }

    }

    @Override
    public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

    }

    @Override
    public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {
    }
}
