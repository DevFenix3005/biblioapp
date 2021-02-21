package com.lania.biblioapp_cli.componentes.libros.lista;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lania.biblioapp_cli.BR;
import com.lania.biblioapp_cli.R;
import com.lania.biblioapp_cli.componentes.libros.LibroController;
import com.lania.biblioapp_cli.databinding.ItemviewLibroBinding;
import com.lania.biblioapp_cli.entidades.Libro;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.rambler.libs.swipe_layout.SwipeLayout;


@Data
@EqualsAndHashCode(callSuper = true)
public class LibrosListAdapter extends RecyclerView.Adapter<LibrosListAdapter.LibroViewHolder> {

    private LibroController libroController;
    private List<Libro> libroList;

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemviewLibroBinding itemviewLibroBinding = DataBindingUtil.inflate(layoutInflater, R.layout.itemview_libro, parent, false);
        return new LibroViewHolder(itemviewLibroBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {

        holder.bind(this.libroList.get(position));

    }

    @Override
    public int getItemCount() {
        return libroList.size();
    }

    public void add(Libro libro) {
        this.libroList.add(libro);
        this.notifyItemInserted(this.libroList.size());
    }

    public void remove(Libro libro) {
        int index = this.libroList.indexOf(libro);
        this.libroList.remove(libro);
        this.notifyItemRemoved(index);
    }

    public class LibroViewHolder extends RecyclerView.ViewHolder {

        private final ItemviewLibroBinding itemviewLibroBinding;
        private SwipeLayout swipeLayout;

        public LibroViewHolder(ItemviewLibroBinding itemviewLibroBinding) {
            super(itemviewLibroBinding.getRoot());
            this.itemviewLibroBinding = itemviewLibroBinding;
            this.swipeLayout = this.itemView.findViewById(R.id.item_libro_swipe);
            this.swipeLayout.setOnSwipeListener(libroController);
        }

        public void bind(Libro libro) {
            this.swipeLayout.setTag(libro);
            itemviewLibroBinding.setVariable(BR.itemview_libro_var, new LibroItemViewModel(libro));
            itemviewLibroBinding.executePendingBindings();
        }


    }


}
