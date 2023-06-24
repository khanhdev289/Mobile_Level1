package khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.adapter.ItemAdapter;
import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.data.DAO;
import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.model.SinhVien;

public class AddItem extends AppCompatActivity {
    EditText tenxe, hangxe, namsx, giaxe;
    Button luu, thoat;
    DAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        SinhVien sv = new SinhVien();
        tenxe = findViewById(R.id.add_tenxe);
        hangxe = findViewById(R.id.add_hangxe);
        namsx = findViewById(R.id.add_namsx);
        giaxe = findViewById(R.id.add_giaxe);
        luu = findViewById(R.id.add_luu);
        thoat = findViewById(R.id.add_thoat);

        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new DAO(getApplicationContext());

                String tenXe = tenxe.getText().toString();
                String hangXe = hangxe.getText().toString();
                String namSXString = namsx.getText().toString();
                String giaXeString = giaxe.getText().toString();

                if (tenXe.isEmpty() || hangXe.isEmpty() || namSXString.isEmpty() || giaXeString.isEmpty()) {
                    Toast.makeText(AddItem.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    int year = Integer.parseInt(namSXString);
                    double price = Double.parseDouble(giaXeString);

                    if (price < 0) {
                        Toast.makeText(AddItem.this, "Giá bán không được nhỏ hơn 0", Toast.LENGTH_SHORT).show();
                    } else if (year < 1980 || year > 2023) {
                        Toast.makeText(AddItem.this, "Năm sản xuất phải từ 1980 đến 2023", Toast.LENGTH_SHORT).show();
                    } else {
                        sv.setName(tenXe);
                        sv.setHang(hangXe);
                        sv.setYear(year);
                        sv.setPrice(price);

                        if (dao.insert(sv) > 0) {
                            Toast.makeText(AddItem.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddItem.this, "Thêm thất bại!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItem.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}