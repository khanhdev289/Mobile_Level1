package khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.adapter.ItemAdapter;
import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.data.DAO;
import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.model.SinhVien;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recy;
    FrameLayout test;
    private Button btn_add;
    private DAO dao;
    private ArrayList<SinhVien> list;
    private ItemAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        btn_add = findViewById(R.id.btn_add);
        recy = findViewById(R.id.recy);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showadd();
            }
        });
    }

    private void showadd() {
        Intent intent = new Intent(MainActivity.this, AddItem.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloaddata();
    }

    public void reloaddata() {
        dao = new DAO(getApplicationContext());
        list = dao.getAllData();
        adapter = new ItemAdapter(list, MainActivity.this);
        recy.setAdapter(adapter);
        recy.setLayoutManager(new LinearLayoutManager(this));
    }
}