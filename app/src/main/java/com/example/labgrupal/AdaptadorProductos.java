package com.example.labgrupal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductosViewHolder> {

    private Context mCtx;
    private List<Modelo_Productos> ProductosList;

    public AdaptadorProductos(Context mCtx, List<Modelo_Productos> productosList) {
        this.mCtx = mCtx;
        this.ProductosList = productosList;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(mCtx);
        View view = inflate.inflate(R.layout.list_producto, null);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductos.ProductosViewHolder holder, int position) {
        Modelo_Productos dto = ProductosList.get(position);

        holder.textViewNombreProducto1.setText(dto.getNombre_producto());
        holder.textViewDescripcioProductos1.setText(dto.getDescripcion_producto());
        holder.textViewCatidaProducto1.setText(String.valueOf(dto.getCantidad_producto()));
        holder.textViewPrecioProducto1.setText(String.valueOf(dto.getPrecio()));
        holder.textViewEstadoProducto1.setText(String.valueOf(dto.getEstado()));
        holder.textViewCategoriaProductos1.setText(String.valueOf(dto.getId_categoria()));

    }

    @Override
    public int getItemCount() {
        return ProductosList.size();
    }

    public static class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreProducto1, textViewDescripcioProductos1, textViewCatidaProducto1, textViewPrecioProducto1
                ,textViewEstadoProducto1,textViewCategoriaProductos1;

        public ProductosViewHolder(View itemView) {
            super(itemView);
            textViewNombreProducto1 = itemView.findViewById(R.id.textViewNombre_Producto);
            textViewDescripcioProductos1 = itemView.findViewById(R.id.textViewDescripcionProducto);
            textViewCatidaProducto1 = itemView.findViewById(R.id.textViewCantidadProducto);
            textViewPrecioProducto1 = itemView.findViewById(R.id.textViewPrecioProducto);
            textViewEstadoProducto1 = itemView.findViewById(R.id.textViewEstadoProducto);
            textViewCategoriaProductos1 = itemView.findViewById(R.id.textViewIdCategoria);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
