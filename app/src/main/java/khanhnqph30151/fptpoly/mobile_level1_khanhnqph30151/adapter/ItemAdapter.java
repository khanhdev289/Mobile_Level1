package khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.R;
import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.data.DAO;
import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.model.SinhVien;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemVH> {
    private Context context;
    private ArrayList<SinhVien> list;
    private DAO dao;


    public ItemAdapter(final ArrayList<SinhVien> list, final Context context) {
        this.list = list;
        this.context = context;
        dao = new DAO(context);

    }

    public void setData(ArrayList<SinhVien> lst) {
        this.list = lst;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, @SuppressLint("RecyclerView") int position) {
        SinhVien sv = list.get(position);

        holder.tv1.setText(sv.getName());
        holder.tv2.setText(sv.getHang());
        holder.tv3.setText(String.valueOf(sv.getYear()));
        holder.tv4.setText(String.valueOf(sv.getPrice()));


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bạn Có Muốn Xóa Không");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao = new DAO(context);
                        if (dao.delete(id) > 0) {
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            dao = new DAO(context);
                            list = dao.getAllData();
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public long getItemId(final int position) {
        return list.get(position).getId();
    }

    class ItemVH extends RecyclerView.ViewHolder {
        private TextView tv1, tv2, tv3, tv4;
        private ImageButton btnDelete;


        public ItemVH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tvName);
            tv2 = itemView.findViewById(R.id.tvHang);
            tv3 = itemView.findViewById(R.id.tvYear);
            tv4 = itemView.findViewById(R.id.tvPrice);
            btnDelete = itemView.findViewById(R.id.btn_sinhvien_item_delete);

        }
    }


}

