package com.lania.biblioapp.componentes.editor.lista;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lania.biblioapp.BR;
import com.lania.biblioapp.R;
import com.lania.biblioapp.componentes.editor.EditorController;
import com.lania.biblioapp.databinding.ItemviewEditorBinding;
import com.lania.biblioapp.dominio.entidades.Editor;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.rambler.libs.swipe_layout.SwipeLayout;

@Data
@EqualsAndHashCode(callSuper = true)
public class EditorAdapter extends RecyclerView.Adapter<EditorAdapter.EditorItemViewHolder> {

    @NonNull
    private List<Editor> editorList;

    private EditorController editorController;

    public EditorAdapter() {
    }

    @NonNull
    @Override
    public EditorItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemviewEditorBinding editorItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.itemview_editor, parent, false);
        return new EditorItemViewHolder(editorItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EditorItemViewHolder holder, int position) {
        Editor editor = this.editorList.get(position);
        holder.bind(editor);
    }

    @Override
    public int getItemCount() {
        return this.editorList.size();
    }


    public void add(Editor editor) {
        this.editorList.add(editor);
        this.notifyItemInserted(this.editorList.size());
    }

    public void remove(Editor editor) {
        int index = this.editorList.indexOf(editor);
        this.editorList.remove(editor);
        this.notifyItemRemoved(index);
    }


    @EqualsAndHashCode(callSuper = true)
    public class EditorItemViewHolder extends RecyclerView.ViewHolder {

        @Getter
        private final SwipeLayout swipeLayout;
        private final ItemviewEditorBinding editorItemBinding;


        public EditorItemViewHolder(@NonNull ItemviewEditorBinding editorItemBinding) {
            super(editorItemBinding.getRoot());
            this.swipeLayout = this.itemView.findViewById(R.id.item_editor_swipe);
            this.swipeLayout.setOnSwipeListener(editorController);
            this.editorItemBinding = editorItemBinding;
        }

        public void bind(Editor editor) {
            this.swipeLayout.setTag(editor);
            editorItemBinding.setVariable(BR.editor_item_vm, new EditorItemViewModel(editor));
            editorItemBinding.executePendingBindings();
        }


    }


}
