package com.lania.biblioapp.componentes.miembro;

import android.content.ContentResolver;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.miembro.lista.MiembroListAdapter;
import com.lania.biblioapp.dialogos.DatePickerFragment;
import com.lania.biblioapp.dominio.entidades.Miembro;
import com.lania.biblioapp.provedor.LibroContentProvider;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import lombok.Data;
import lombok.NonNull;
import ru.rambler.libs.swipe_layout.SwipeLayout;

@Data
public class MiembroController implements View.OnClickListener, SwipeLayout.OnSwipeListener, TextWatcher {

    private static final String TAG = "LOG[MiembroController]";

    @NonNull
    private final MiembroActivity activity;

    @NonNull
    private final MiembroViewModel miembroViewModel;

    @NonNull
    private final ContentResolver contentResolver;

    public MiembroController(@NonNull MiembroActivity activity, @NonNull MiembroViewModel miembroViewModel) {
        this.activity = activity;
        this.miembroViewModel = miembroViewModel;
        this.contentResolver = activity.getContentResolver();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.miembro_registrar:
                registerMiembro();
                break;
            case R.id.birthday_miembro_field_true:
                showDatePickerDialog();
                break;
            case R.id.miembro_floatingbutton_go2form:
                this.miembroViewModel.clean();
                this.activity.go2Form();
                break;
        }
    }

    private void deleteMiembro(Miembro miembro) {

        String mensaje = String.format(Locale.getDefault(), "El miembro '%s' fue borrado", miembro.getNombre());
        Toast.makeText(activity, mensaje, Toast.LENGTH_LONG).show();

        Uri deleteMiembroUri = Uri.parse(LibroContentProvider.CONTENT_STR_URL_MIEMBRO + "/" + miembro.getId());
        int q = this.contentResolver.delete(deleteMiembroUri, null, null);

        MiembroListAdapter miembroListAdapter = this.miembroViewModel.getMiembroListAdapter();
        miembroListAdapter.remove(miembro);

    }

    private void registerMiembro() {
        Miembro miembro = miembroViewModel.createMiembroFromViewModel();
        String mensaje;
        Miembro miembro2Update = miembroViewModel.getMiembro2Update();
        if (miembro2Update != null) {

            miembro.setFechaDeRegistro(miembro2Update.getFechaDeRegistro());
            miembro.setId(miembro2Update.getId());

            Uri updateMiembroUri = Uri.parse(LibroContentProvider.CONTENT_STR_URL_MIEMBRO + "/" + miembro2Update.getId());
            this.contentResolver.update(updateMiembroUri, miembro.toContentValue(), null, null);

            MiembroListAdapter miembroListAdapter = this.miembroViewModel.getMiembroListAdapter();
            miembroListAdapter.remove(miembro2Update);
            miembroListAdapter.add(miembro);

            mensaje = String.format(Locale.getDefault(), "El miembro '%s' fue actualizado", miembro.getNombre());
            Toast.makeText(activity, mensaje, Toast.LENGTH_LONG).show();

        } else {
            Uri resultInsertUri = this.contentResolver.insert(LibroContentProvider.CONTENT_URI_MIEMBRO, miembro.toContentValue());
            Long newId = Long.parseLong(resultInsertUri.getLastPathSegment());
            miembro.setId(newId);

            MiembroListAdapter miembroListAdapter = this.miembroViewModel.getMiembroListAdapter();
            miembroListAdapter.add(miembro);

            mensaje = String.format(Locale.getDefault(), "El miembro '%s' fue creado y se le asigno el folio %d", miembro.getNombre(), newId);
        }

        Toast.makeText(activity, mensaje, Toast.LENGTH_LONG).show();
        miembroViewModel.clean();
        this.activity.getSupportFragmentManager().popBackStack();

    }


    private void showDatePickerDialog() {

        String selectedDate = miembroViewModel.getFechaDeNacimiento().getValue();

        DatePickerFragment newFragment = DatePickerFragment
                .newInstance((view, year, month, dayOfMonth) -> {
                            String fecha = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth);
                            miembroViewModel.getFechaDeNacimiento().setValue(fecha);
                        }, Objects.nonNull(selectedDate) ? selectedDate : null
                );
        newFragment.show(activity.getSupportFragmentManager(), "datePicker");
    }


    @Override
    public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {

    }

    @Override
    public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
        Miembro miembro = (Miembro) swipeLayout.getTag();
        if (!moveToRight) {
            this.deleteMiembro(miembro);
        } else {
            this.miembroViewModel.setValues2Update(miembro);
            this.activity.go2Form();
        }
    }

    @Override
    public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

    }

    @Override
    public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, "Valor del busqueda -> " + s);
        List<Miembro> miembroList;
        if (s.length() > 0) {
            miembroList = this.activity.getMiembros(s.toString());
        } else {
            miembroList = this.activity.getMiembros(null);
        }
        this.miembroViewModel.getMiembroListAdapter().update(miembroList);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
