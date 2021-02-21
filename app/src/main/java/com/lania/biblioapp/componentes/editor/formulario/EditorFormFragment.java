package com.lania.biblioapp.componentes.editor.formulario;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.editor.EditorActivity;
import com.lania.biblioapp.componentes.editor.EditorController;
import com.lania.biblioapp.componentes.editor.EditorViewModel;
import com.lania.biblioapp.databinding.FragmentEditorFormBinding;

import java.util.Objects;


public class EditorFormFragment extends Fragment {

    private static final String TAG = "LOG[EditorActivity]";
    private EditorViewModel editorViewModel;
    private FragmentEditorFormBinding fragmentEditorFormBinding;

    public static EditorFormFragment newInstance() {
        return new EditorFormFragment();
    }

    public EditorFormFragment() {
        super(R.layout.fragment_editor_form);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EditorActivity editorActivity = (EditorActivity) this.getActivity();
        EditorController editorController = editorActivity.getEditorController();
        this.editorViewModel = new ViewModelProvider(editorActivity).get(EditorViewModel.class);
        this.editorViewModel.setToolbarsubtitle("Formulario de Editores");

        fragmentEditorFormBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor_form, container, false);
        fragmentEditorFormBinding.setEditor(editorViewModel);
        fragmentEditorFormBinding.setLifecycleOwner(this.getViewLifecycleOwner());

        View view = fragmentEditorFormBinding.getRoot();
        createValidations(view);
        this.editorViewModel.clean();
        Button button = view.findViewById(R.id.register_editor);
        button.setOnClickListener(editorController);

        return view;
    }

    private void createValidations(View view) {
        editorViewModel.getNombre().observe(this.getViewLifecycleOwner(), value -> this.emptyFieldValidator(view, value, R.id.editor_nombre_field, editorViewModel.getNombreValid()));
        editorViewModel.getDireccion().observe(this.getViewLifecycleOwner(), value -> this.emptyFieldValidator(view, value, R.id.editor_direccion_field, editorViewModel.getDireccionValid()));
    }

    private void emptyFieldValidator(View view, String value, int fieldId, MutableLiveData<Boolean> validFlag) {
        TextInputLayout textInputLayout = view.findViewById(fieldId);

        if (Objects.isNull(value)) {
            validFlag.setValue(Boolean.FALSE);
            textInputLayout.setError("");
            textInputLayout.setErrorEnabled(false);
            return;
        }

        Log.d(TAG, "Valor del campo " + value);

        if (value.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("No puede ser un campo vacio");
            validFlag.setValue(Boolean.FALSE);
        } else {
            validFlag.setValue(Boolean.TRUE);
            textInputLayout.setError("");
            textInputLayout.setErrorEnabled(false);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentEditorFormBinding = null;
        editorViewModel = null;
    }


}