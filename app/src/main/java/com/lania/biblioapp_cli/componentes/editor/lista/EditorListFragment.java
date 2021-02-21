package com.lania.biblioapp_cli.componentes.editor.lista;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lania.biblioapp_cli.R;
import com.lania.biblioapp_cli.componentes.editor.EditorActivity;
import com.lania.biblioapp_cli.componentes.editor.EditorController;
import com.lania.biblioapp_cli.componentes.editor.EditorViewModel;
import com.lania.biblioapp_cli.databinding.FragmentEditorListBinding;

public class EditorListFragment extends Fragment {

    private static final String TAG = "LOG[EditorListFragment]";
    private FragmentEditorListBinding binding;

    public static EditorListFragment newInstance() {
        return new EditorListFragment();
    }


    public EditorListFragment() {
        super(R.layout.fragment_editor_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        EditorActivity parent = (EditorActivity) getActivity();
        EditorViewModel editorViewModel = new ViewModelProvider(parent).get(EditorViewModel.class);
        editorViewModel.getToolbarsubtitle().setValue("Lista");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        EditorActivity parent = (EditorActivity) getActivity();
        EditorController editorController = parent.getEditorController();

        EditorViewModel editorViewModel = new ViewModelProvider(parent).get(EditorViewModel.class);
        EditorAdapter editorAdapter = editorViewModel.getEditorAdapter();
        editorAdapter.setEditorController(editorController);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor_list, container, false);
        binding.setEditorAdapter(editorAdapter);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        RecyclerView recyclerView = view.findViewById(R.id.editorlist_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        FloatingActionButton floatingActionButton = view.findViewById(R.id.editor_floatingbutton_go2form);
        floatingActionButton.setOnClickListener(editorController);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}