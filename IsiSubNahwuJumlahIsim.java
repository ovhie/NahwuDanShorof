package nurekaf.tugasakhir.nahwudanshorof;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import nurekaf.tugasakhir.nahwudanshorof.manajemendb.ManajemenDB;

public class IsiSubNahwuJumlahIsim extends AppCompatActivity
implements Button.OnClickListener{

    public ManajemenDB db;
    public Button btnNext;
    public Button btnBack;
    public TextView txtMateri;

    public int indData = 0;

    public ArrayList<ManajemenDB.StrukturTabel> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_sub_nahwu_isim);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnBack = (Button) findViewById(R.id.btnBack);
        txtMateri = (TextView) findViewById(R.id.txtMateri);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        //buka database
        db = ManajemenDB.dapatkanObjek(this);
        //masukkan data dari tabel nahwu ke array "data"
        data = db.dapatkanSemuaData(ManajemenDB.TABEL_NAHWU);

        

        txtMateri.setText(data.get(0).dapatkanData(1).toUpperCase() + "\n \n" +
                            data.get(0).dapatkanData(2));

        //disable btnBack, enable tombol next
        btnBack.setEnabled(false);
        btnNext.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        //jika tombol next dipilih
        if(view.getId() == R.id.btnNext){
            //aktifkan tombol Back
            btnBack.setEnabled(true);

            //naikkan index materi
            ++indData;

            //jika index materi belum mencapai akhir
            if(indData < data.size()){
                //tampilkan materi
                txtMateri.setText(data.get(indData).dapatkanData(1).toUpperCase() + "\n \n" +
                        data.get(indData).dapatkanData(2));
                //jika index materi mencapai batas akhir
                if(indData == data.size() - 1) {
                    //Nonaktif tombol Next
                    btnNext.setEnabled(false);
                }
            }
            else {
                //jika index mencapai akhir, jangan naikkan index
                indData = data.size() - 1;
            }
        }
        else if(view.getId() == R.id.btnBack){   // begitu juga dengan tombol back

            btnNext.setEnabled(true);

            --indData;
            if(indData >= 0){
                txtMateri.setText(data.get(indData).dapatkanData(1).toUpperCase() + "\n \n" +
                        data.get(indData).dapatkanData(2));

                if(indData == 0) {
                    btnBack.setEnabled(false);
                }
            }
            else {
                indData = 0;
            }
        }
    }
}
