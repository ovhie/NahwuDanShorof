package nurekaf.tugasakhir.nahwudanshorof.manajemendb;

/**
 * Created by ovhie on 2/20/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.nio.charset.Charset;
import java.util.ArrayList;

import nurekaf.tugasakhir.nahwudanshorof.R;

public class ManajemenDB extends SQLiteOpenHelper {
    public final static String NAMADB = "dbnahwudanshorof.db";
    public final static String TABEL_NAHWU = "TabelNahwu";
    public final static String TABEL_SHOROF = "TabelShorof";
    public final static String TABEL_SOAL = "TabelSoal";
    public final static String TABEL_JAWABAN = "TabelJawaban";
    public final static String TABEL_MUFRODATS = "TabelMufrodats";

    private Context cont;
    private SQLiteDatabase db;
    private static ManajemenDB objek;

    private ManajemenDB(Context cont) {
        super(cont, NAMADB, null, 1);
        this.cont = cont;
        this.objek = null;
    }

    public static ManajemenDB dapatkanObjek(Context cont) {
        if(objek == null) {
            objek = new ManajemenDB(cont);
            objek.buka();
        }

        return objek;
    }

    public void buka() {
        try {
            db = getWritableDatabase();
            System.out.println("DIEKSEKUSI !!!!");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void tutup() {
        if(db.isOpen()) {
            db.close();
        }
    }

    public void input(String namaTabel, StrukturTabel data) {
        Cursor kur = db.rawQuery("select * from " + namaTabel, null);

        int jumlahField = kur.getColumnCount();
        ContentValues val = new ContentValues();

        for(int i = 1; i < jumlahField; i++) {
            val.put(kur.getColumnName(i), data.dapatkanData(i));
        }

        db.insert(namaTabel, null, val);
    }

    public ArrayList<StrukturTabel> dapatkanSemuaData(String namaTabel) {
        ArrayList<StrukturTabel> data = new ArrayList<>();

        Cursor kur = db.rawQuery("select * from " + namaTabel, null);
        kur.moveToFirst();

        int jumlahField = kur.getColumnCount();

        while(!kur.isAfterLast()) {
            String[] sebarisData = new String[jumlahField];

            for(int i = 0; i < jumlahField; i++) {
                sebarisData[i] = kur.getString(i);
            }

            data.add(new StrukturTabel(sebarisData));

            kur.moveToNext();
        }

        return data;
    }

    public StrukturTabel dapatkanData(String namaTabel, String field, String value) {
        Cursor kur = db.rawQuery("select * from " + namaTabel +
                                        " where " + field + " = '" + value + "'",null);
        kur.moveToFirst();

        int jumlahField = kur.getColumnCount();
        String[] sebarisData = new String[jumlahField];

        for(int i = 0; i < jumlahField; i++) {
            sebarisData[i] = kur.getString(i);
        }

        return new StrukturTabel(sebarisData);
    }

    public void update(String namaTabel, String fieldKriteria, String valKriteria, String... field) {
        Cursor kur = db.rawQuery("select * from " + namaTabel, null);

        int jumlahField = kur.getColumnCount();
        ContentValues val = new ContentValues();

        for(int i = 0; i < jumlahField; i++) {
            val.put(kur.getColumnName(i), field[i]);
        }

        db.update(namaTabel, val, fieldKriteria + " = " + valKriteria, null);
    }

    public void hapus(String namaTabel, String field, String val) {
        db.delete(namaTabel, field + " = " + val, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb) {
        sqldb.execSQL(cont.getResources().getString(R.string.TabelNahwu));
        sqldb.execSQL(cont.getResources().getString(R.string.TabelShorof));
        sqldb.execSQL(cont.getResources().getString(R.string.TabelSoal));
        sqldb.execSQL(cont.getResources().getString(R.string.TabelJawaban));
        sqldb.execSQL(cont.getResources().getString(R.string.TabelMufrodats));

        ContentValues val = new ContentValues();
        val.put("kosakata", "رَأْسٌ");
        val.put("arti", "Kepala");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَبْهَةٌ");
        val.put("arti", "Jidat");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَجْهٌ");
        val.put("arti", "Wajah");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata","شَفَةٌ");
        val.put("arti", "Bibir");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata","فَمٌّ");
        val.put("arti", "Mulut");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَيْنٌ");
        val.put("arti", "Mata");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "شَعْرٌ");
        val.put("arti", "Rambut");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَدٌّ");
        val.put("arti", "Pipi");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَنْفٌ");
        val.put("arti", "Hidung");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لِسَانٌ");
        val.put("arti", "Lidah");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سِنٌ");
        val.put("arti", "Gigi");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُذُنٌ");
        val.put("arti", "Telinga");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَقَبَةٌ");
        val.put("arti", "Leher");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَتِفٌ");
        val.put("arti", "Bahu");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ظَهْرٌ");
        val.put("arti", "Punggung");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَدْرٌ");
        val.put("arti", "Dada");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَطْنٌ");
        val.put("arti", "Perut");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَتِفٌ");
        val.put("arti", "Pundak");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "يَدٌ");
        val.put("arti", "Tangan");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِبْهَامٌ");
        val.put("arti", "Ibu jari");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَبَابَةٌ");
        val.put("arti", "Jari telunjuk");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وُسْطَى");
        val.put("arti", "Jari tengah");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بِنْصَرٌ");
        val.put("arti", "Jari manis");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خِنْصِرٌ");
        val.put("arti", "Jari kelingking");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ظُفْرٌ");
        val.put("arti", "Kuku");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ذِرَاعٌ");
        val.put("arti", "Lengan");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِرْفَقٌ");
        val.put("arti", "Siku");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُصْبُعٌ");
        val.put("arti", "Jari");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَفٌّ");
        val.put("arti", "Telapak tangan");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَدَمٌ");
        val.put("arti", "Telapak kaki");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رِجْلٌ");
        val.put("arti", "Kaki");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رُكْبَةٌ");
        val.put("arti", "lutut");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَخِذٌ");
        val.put("arti", "Paha");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُصْبُعُ الْقَدَمِ");
        val.put("arti", "Jari kaki");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَقْبٌ");
        val.put("arti", "Mata kaki");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَمٌّ");
        val.put("arti", "Darah");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لَحْمٌ");
        val.put("arti", "Daging");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَصَبٌ");
        val.put("arti", "Urat syaraf");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَلْبٌ");
        val.put("arti", "Hati");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دِمَاغٌ");
        val.put("arti", "Otak");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَظْمٌ");
        val.put("arti", "Tulang");
        val.put("kategori", "anggota tubuh");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَسْوَدٌ");
        val.put("arti", "Hitam");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَبْيَضٌ");
        val.put("arti", "Putih");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَحْمَرٌ");
        val.put("arti", "Merah");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَزْرَقٌ");
        val.put("arti", "Biru");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَخْضَرٌ");
        val.put("arti", "Hijau");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَصْفَرٌ");
        val.put("arti", "Kuning");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بُرْتُقَالِيٌّ");
        val.put("arti", "Orange");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَنَفْسَجِيٌّ");
        val.put("arti", "Ungu");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَمَادِيٌّ");
        val.put("arti", "Abu-abu");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَرْدِيٌّ");
        val.put("arti", "Pink");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بُنِيٌّ");
        val.put("arti", "coklat");
        val.put("kategori", "warna");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "يَنَايِرْ");
        val.put("arti", "Januari");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَبْرَايِرْ");
        val.put("arti", "Februari");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَارِسْ");
        val.put("arti", "Maret");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَبْرِيْلْ");
        val.put("arti", "April");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَايُو");
        val.put("arti", "Mei");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "يُوْنِيُو");
        val.put("arti", "Juni");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "يُوْلِيُو");
        val.put("arti", "Juli");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَغُسْطُسْ");
        val.put("arti", "Agustus");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سِبْتَمْبِرْ");
        val.put("arti", "September");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَكْتُوْبَرْ");
        val.put("arti", "Oktober");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نُوْفِمْبِرْ");
        val.put("arti", "November");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دِيْسمْبِرْ");
        val.put("arti", "Desember");
        val.put("kategori", "Bulan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِوَزَّةٌ");
        val.put("arti", "Angsa");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَلْبٌ");
        val.put("arti", "Anjing");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَجَاجَةٌ");
        val.put("arti", "Ayam betina");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دِيْكٌ");
        val.put("arti", "Ayam jantan");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خِنْزِيْرٌ");
        val.put("arti", "Babi");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَوْرٌ");
        val.put("arti", "Banteng");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَطَّةٌ");
        val.put("arti", "Bebek");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَرَادٌ");
        val.put("arti", "Belalang");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تِمْسَاحٌ");
        val.put("arti", "Buaya");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَيْرٌ");
        val.put("arti", "Burung");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَبَّغَاءٌ");
        val.put("arti", "Burung Beo");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بُوْمَةٌ");
        val.put("arti", "Burung hantu");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عُصْفُوْرٌ");
        val.put("arti", "Burung Pipit");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَزَعٌ");
        val.put("arti", "Cicak");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَاعِزٌ");
        val.put("arti", "Domba");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَقْرٌ");
        val.put("arti", "Elang");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فِيْلٌ");
        val.put("arti", "Gajah");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَمِرٌ");
        val.put("arti", "Harimau");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَمَكَةٌ");
        val.put("arti", "Ikan");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "غَنَمٌ");
        val.put("arti", "Kambing");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ضِفْدَعٌ");
        val.put("arti", "Katak");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حِمَارٌ");
        val.put("arti", "Keledai");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَرْنَبٌ");
        val.put("arti", "Kelinci");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قِرْدٌ");
        val.put("arti", "Kera");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَامُوْسٌ");
        val.put("arti", "Kerbau");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قِطَةٌ");
        val.put("arti", "Kucing");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حِصَانٌ");
        val.put("arti", "Kuda");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَرَاشَةٌ");
        val.put("arti", "Kupu-kupu");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سُلَحْفَاةٌ");
        val.put("arti", "Kura-kura");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَنْكَبُوْتٌ");
        val.put("arti", "Laba-laba");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ذُبَابٌ");
        val.put("arti", "Lalat");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَحْلَةٌ");
        val.put("arti", "Lebah");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَمَامَةٌ");
        val.put("arti", "Merpati");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَعُوْضَةٌ");
        val.put("arti", "Nyamuk");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حُوْتٌ");
        val.put("arti", "Paus");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَعْلَبٌ");
        val.put("arti", "Rubah");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "غَزَالٌ");
        val.put("arti", "Rusa");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَقَرَةٌ");
        val.put("arti", "Sapi");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خُفَاشٌ");
        val.put("arti", "Kelelawar");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَمْلَةٌ");
        val.put("arti", "Semut");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَسَدٌ");
        val.put("arti", "Singa");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَأْرَةٌ");
        val.put("arti", "Tikus");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَعْبَانٌ");
        val.put("arti", "Ular");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَمَلٌ");
        val.put("arti", "Unta");
        val.put("kategori", "hewan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَحْلَبِيَّةٌ");
        val.put("arti", "Orkid");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَنَاغَا");
        val.put("arti", "Kenanga");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تُوْلِيْبٌ");
        val.put("arti", "Tulip");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَهْرَةٌ الْمَرْجَانِ");
        val.put("arti", "Bunga Dedap");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُقْحُوَانٌ");
        val.put("arti", "Kekwa");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَهْرَةُالرَّبِيْعِ");
        val.put("arti", "Daisi");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بُوْغَنْفِيْلِيَّةٌ");
        val.put("arti", "Bunga kertas");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خُبَّازَةٌ");
        val.put("arti", "Bunga rayo");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَمْبُوْجَا");
        val.put("arti", "Bunga kamboja");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لُوْتُسٌ");
        val.put("arti", "Telepok");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لَبْلَابٌ");
        val.put("arti", "Bunga seri pagi");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَمْبَاكَا");
        val.put("arti", "Cempaka");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَهْلِيَّةٌ");
        val.put("arti", "Dahlia");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَلْسَمٌ");
        val.put("arti", "Keembung");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَرْدَةٌ");
        val.put("arti", "Mawar");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَنْبَقٌ");
        val.put("arti", "Bakung");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عُبَادُالشَّمْسِ");
        val.put("arti", "Bunga matahari");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَبْلُوْفَرٌ مَاىِْيٌّ");
        val.put("arti", "Teratai");
        val.put("kategori", "Bunga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تُفَّاحٌ");
        val.put("arti", "Apel");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عِنَبٌ");
        val.put("arti", "Anggur");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَفُوْكَتُهُ");
        val.put("arti", "Alpukat");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بُرْتُقَالٌ");
        val.put("arti", "Jeruk");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لِيْمُوْنٌ");
        val.put("arti", "Jeruk nipis");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَنَانَسٌ");
        val.put("arti", "Nanas");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَامْبُوْتَانٌ");
        val.put("arti", "Rambutan");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَمْرُ شَائِكٌ");
        val.put("arti", "Durian");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تَمْرٌ");
        val.put("arti", "Kurma");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رُمَّانٌ");
        val.put("arti", "Delima");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بِطَيْخٌ");
        val.put("arti", "Semangka");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَوْزُالْهِنْدِ");
        val.put("arti", "Kelapa");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَوَافَةٌ");
        val.put("arti", "Jambu biji");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَوْزٌ");
        val.put("arti", "Pisang");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "هِلْيُوْنَ");
        val.put("arti", "Asparagus");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَصَلٌ");
        val.put("arti", "Bawang merah");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَوْمٌ");
        val.put("arti", "Bawang putih");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "الإِسْبَانَخُ : بَقْلَةٌ");
        val.put("arti", "Bayam");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لَوْبَيَاءٌ");
        val.put("arti", "Buncis");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَرَقَ الْبَصَلِ");
        val.put("arti", "Daun bawang");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَرَقَ الْكَرَفْسِ");
        val.put("arti", "Daun Seledri");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَرَفْسٌ");
        val.put("arti", "Seledri");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ذَرَةٌ");
        val.put("arti", "Jagung");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فُطْرٌ");
        val.put("arti", "Jamur");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "الدَّجَرٌ");
        val.put("arti", "Kacang hijau");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فُوْلٌ صُبَا");
        val.put("arti", "Kacang keledai");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "اللّوْبِيَاءُ");
        val.put("arti", "Kacang polong");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَنْجَوَ");
        val.put("arti", "Mangga");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَابَايَا");
        val.put("arti", "Pepaya");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بُرْغُوْمٌ");
        val.put("arti", "Kecambah");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَطَاطِسٌ");
        val.put("arti", "Kentang");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كُرُوْبٌ : مَلْفُوْفٌ");
        val.put("arti", "Kol (Kubis)");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "الدِّبَّاءُ");
        val.put("arti", "Labu");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خِيَارٌ");
        val.put("arti", "Mentimun");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّلَطَةُ");
        val.put("arti", "Selada");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَمَاطِمٌ");
        val.put("arti", "Tomat");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَازِنْجَانٌ");
        val.put("arti", "Terong");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَزَرٌ");
        val.put("arti", "Wortel");
        val.put("kategori", "Buah dan Sayur");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُسْرَةٌ");
        val.put("arti", "Keluarga");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَقْرِبَاءٌ");
        val.put("arti", "Sanak saudara");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُمٌّ");
        val.put("arti", "Ibu");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَبٌ");
        val.put("arti", "Ayah");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَدٌّ");
        val.put("arti", "Kakek");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَدَّةٌ");
        val.put("arti", "Nenek");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَلَدٌ");
        val.put("arti", "Anak laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بِنْتٌ");
        val.put("arti", "Anak perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَخٌ");
        val.put("arti", "Saudara Laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُخْتٌ");
        val.put("arti", "Saudara perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَخُ الصَّغيْرُ");
        val.put("arti", "Adik laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُخْتُ الصَّغِيْرَةٌ");
        val.put("arti", "Adik perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَخٌ كَبِيْرٌ");
        val.put("arti", "Kakak Laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُخْتٌ كَبِيْرَةٌ");
        val.put("arti", "Kakak perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَمٌّ");
        val.put("arti", "Paman");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَمَّةٌ");
        val.put("arti", "Bibi");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِبْنُ / إِبْنَةُالعَمِّ");
        val.put("arti", "Saudara sepupu");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَبْنَاءُالأَخِِ");
        val.put("arti", "Keponakan laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَبْنَاءُالأُخْتِ");
        val.put("arti", "Keponakan perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَفِيْدُ سَبْطٍ");
        val.put("arti", "Cucu laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَفِيْدَةُ سَبْطَةِ");
        val.put("arti", "Cucu perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَابَّةٌ / رَبِيْبَةٌ");
        val.put("arti", "Ibu tiri");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَابَّ");
        val.put("arti", "Ayah tiri");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَمْوٌ");
        val.put("arti", "Ayah mertua");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَمَّاةٌ");
        val.put("arti", "ibu mertua");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَهْرَةٌ");
        val.put("arti", "Ipar perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَهْرٌ");
        val.put("arti", "Ipar laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَتَنٌ");
        val.put("arti", "Menantu laki-laki");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَتْنَةٌ");
        val.put("arti", "Menantu perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَضِيْعٌ");
        val.put("arti", "Bayi");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَيِّبٌ / أَرْمَلَةٌ");
        val.put("arti", "Janda");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَرْمَلٌ");
        val.put("arti", "Duda");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَوْجٌ");
        val.put("arti", "Suami");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَوْجَةٌ");
        val.put("arti", "Istri");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِمْرَأَةٌ");
        val.put("arti", "Perempuan");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "الوَلَدُ المُتَبَنَّي");
        val.put("arti", "Anak angkat");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَخٌ شَقِيْقٌ");
        val.put("arti", "Saudara laki-laki kandung");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُخْتٌ شَقِيْقَةٌ");
        val.put("arti", "Saudara perempuan kandung");
        val.put("kategori", "Keluarga");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَلَكِيٌّ");
        val.put("arti", "Ahli astronomi");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لُغَوِيٌّ");
        val.put("arti", "Ahli bahasa");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَرضاحٌ");
        val.put("arti", "Ahli bedah");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَانُوْنِيٌّ");
        val.put("arti", "Ahli hukum");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رِيَاضِيٌّ");
        val.put("arti", "Ahli matematika");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِيْكَانِيْكِيٌّ");
        val.put("arti", "Ahli mekanik");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَحْوِيٌّ");
        val.put("arti", "Ahli nahwu");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُمَثَلٌ");
        val.put("arti", "Aktor");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُمَثَلَةٌ");
        val.put("arti", "Aktris");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُحَاسِبٌ");
        val.put("arti", "Akuntan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَيْدَلِيٌّ");
        val.put("arti", "Apoteker");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُهَنْدِسٌ مِعْمَارِيٌّ");
        val.put("arti", "Arsitek");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَائِدُالفَضَاءِ");
        val.put("arti", "Astronot");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَاضِنَةٌ");
        val.put("arti", "Baby sister");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُهَرِّجٌ");
        val.put("arti", "Badut");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَرْصَانٌ");
        val.put("arti", "Bajak laut");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَمِنُ الصُّنْدُوْقِ");
        val.put("arti", "Bendahara");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَابِلَةٌ");
        val.put("arti", "Bidan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُحَافِظٌ");
        val.put("arti", "Bupati");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُصَمِّمٌ");
        val.put("arti", "Designer");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُدِيْرٌ");
        val.put("arti", "Direktur");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَبِيْبٌ");
        val.put("arti", "Dokter");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَبِيْبُ أَسْنَانِ");
        val.put("arti", "Dokter gigi");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُصَوِّرٌ");
        val.put("arti", "Photografer");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَمِيْرُ مَنْطِقَةِ");
        val.put("arti", "Gubernur");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُدَرِّسٌ");
        val.put("arti", "Guru");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَاضِ");
        val.put("arti", "Hakim");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَبَّةُ البَيْتِ");
        val.put("arti", "Ibu rumah tangga");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُهَنْدِسٌ");
        val.put("arti", "Insinyur");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَحَفِيٌّ");
        val.put("arti", "Jurnalis");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُتَحَدِّسٌ");
        val.put("arti", "Juru bicara");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَامِلٌ");
        val.put("arti", "Karyawan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عُمْدَةٌ");
        val.put("arti", "Kepala desa");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَئَيْسُ مَجْلِسِ الإِدَارَةِ");
        val.put("arti", "Kepala kantor");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَئِيْسُ الدَّوْلَةِ");
        val.put("arti", "Kepala negara");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَئِيْسُ المَدْرَسَةِ");
        val.put("arti", "Kepala sekolah");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَئِيْسُ الفَصْلِ");
        val.put("arti", "Ketua kelas");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَبَّاخٌ");
        val.put("arti", "Koki");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُقَاوِلٌ");
        val.put("arti", "Kontraktor");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَالِبُ الجَامِعَةِ");
        val.put("arti", "Mahasiswa");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَالِبَةُ الجَامِعَةِ");
        val.put("arti", "Mahasiswi");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَاسُوْسٌ");
        val.put("arti", "Mata-mata");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَزِيْرٌ");
        val.put("arti", "Mentri");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَيَّادُ السَّمَكِ");
        val.put("arti", "Nelayan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تَاجِرٌ");
        val.put("arti", "Pedagang");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُوَظَفٌ");
        val.put("arti", "Pegawai");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَالِبٌ");
        val.put("arti", "Pelajar");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُدَرِّبٌ");
        val.put("arti", "Pelatih");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَادِمٌ");
        val.put("arti", "Pembantu / pelayan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَسَّامٌ");
        val.put("arti", "Pelukis");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَجُلُ المَطَافِئِ");
        val.put("arti", "Pemadam kebakaran");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَلِيْلٌ");
        val.put("arti", "Pemandu wisata");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَاطِعُ الطَرِيْقِ");
        val.put("arti", "Pembegal");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لِصٌّ");
        val.put("arti", "Pencuri");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُدَرِّسٌ");
        val.put("arti", "Pengajar / dosen");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَطَالٌ");
        val.put("arti", "Pengangguran");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُشْرِفٌ");
        val.put("arti", "Pengawas");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُجْرِمٌ");
        val.put("arti", "Penjahat");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَيّاطٌ");
        val.put("arti", "Penjahit");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَلاَّحٌ");
        val.put("arti", "Penjual garam");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَاتِبٌ");
        val.put("arti", "Penulis");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "شَاعِرٌ");
        val.put("arti", "Penyair");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُغَنٍّ");
        val.put("arti", "Penyanyi");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُذِيْعٌ");
        val.put("arti", "Penyair radio");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَاحِرٌ");
        val.put("arti", "Penyihir");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُشَعْوِذٌ");
        val.put("arti", "Penyulap");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُنَجِّمٌ");
        val.put("arti", "Peramal");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُمَرِّضٌ");
        val.put("arti", "Perawat");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَلاَّحٌ");
        val.put("arti", "Petani");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَامِلُ تَنْظِيْفٍ");
        val.put("arti", "Petugas kebersihan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَامِلُ صِيَانَةٍ");
        val.put("arti", "Petugas maintenance");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَيَّارٌ");
        val.put("arti", "Pilot");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "شُرْطِيٌّ");
        val.put("arti", "Polisi");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سِيَاسَيٌّ");
        val.put("arti", "Politikus");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُضِيْفٌ");
        val.put("arti", "Pramugara");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُضِيْفَةٌ");
        val.put("arti", "Pramugari");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَلاَّلٌ");
        val.put("arti", "Pramuniaga");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَئِيْسُ جُمْهُوْرِيَّةٍ");
        val.put("arti", "Presiden");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أُسْتَاذٌ");
        val.put("arti", "Profesor");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُبَرْمِجٌ");
        val.put("arti", "Programer");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَلِكٌ");
        val.put("arti", "Raja");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَدِيْبٌ");
        val.put("arti", "Sastrawan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُؤَرِّخٌ");
        val.put("arti", "Sejarawan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَوَّابٌ");
        val.put("arti", "Security");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تِلْمِيْذٌ");
        val.put("arti", "Siswa");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تِلْمِيْذَةٌ");
        val.put("arti", "Siswi");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَائِقٌ");
        val.put("arti", "Supir");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَجِيْنٌ");
        val.put("arti", "Tahanan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جُنْدِيٌّ");
        val.put("arti", "Tentara");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَدَّادٌ");
        val.put("arti", "Tukang besi");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَهَّانٌ");
        val.put("arti", "Tukang cat");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَلاَّقٌ");
        val.put("arti", "Tukang cukur");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَائِغٌ");
        val.put("arti", "Tukang emas");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَجَّاجٌ");
        val.put("arti", "Tukang kaca");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَمْكَرِيٌّ");
        val.put("arti", "Tukang kaleng");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَجَّارٌ");
        val.put("arti", "Tukang kayu");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بُسْتَانِيٌّ");
        val.put("arti", "Tukang kebun");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَرّاقٌ");
        val.put("arti", "Tukang kertas");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَهْرُبَائِيٌّ");
        val.put("arti", "Tukang listrik");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُدَلَكٌ");
        val.put("arti", "Tukang pijat");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَبَّازٌ");
        val.put("arti", "Tukang roti");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَبَّالٌ");
        val.put("arti", "Tukang sampah");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِسْكَافِيٌّ");
        val.put("arti", "Tukang sepatu");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَوَّاءٌ");
        val.put("arti", "Tukang setrika");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عُمْدَةٌ");
        val.put("arti", "Walikota");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَحَافِيٌّ");
        val.put("arti", "Wartawan");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَكَمٌ");
        val.put("arti", "Wasit");
        val.put("kategori", "Pekerjaan");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَدْرَسَةٌ");
        val.put("arti", "Sekolah");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَصْلٌ");
        val.put("arti", "Kelas");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَكْتَبٌ");
        val.put("arti", "Meja");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كُرْسِيٌّ");
        val.put("arti", "Kursi");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَبُّوْرَةٌ");
        val.put("arti", "Papan tulis");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خِزَانَةٌ");
        val.put("arti", "Lemari");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَلَمُ حِبْرِ");
        val.put("arti", "Spidol");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حِبْرٌ");
        val.put("arti", "Tinta");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِمْسَحَةٌ");
        val.put("arti", "Penghapus");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِسْطَرَةٌ");
        val.put("arti", "Penggaris");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَرِيْطَةٌ");
        val.put("arti", "Peta");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِرْوَحَةٌ");
        val.put("arti", "Kipas angin");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لَوْحَةُ الإِعْلاَمِ");
        val.put("arti", "Papan pengumuman");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَشْفُ الْحُضُوْرِ");
        val.put("arti", "Absen");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَدْوَلُ الدِّرَاسِيُّ");
        val.put("arti", "Jadwal pelajaran");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَزْبَلَةٌ");
        val.put("arti", "Tempat sampah");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَبْشُوْرَةٌ");
        val.put("arti", "Kapur");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِكْنَسَةٌ");
        val.put("arti", "Sapu");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِصْبَاحٌ");
        val.put("arti", "Lampu");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَفٌّ");
        val.put("arti", "Rak");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُعْجَمٌ");
        val.put("arti", "Kamus");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قِلاَدَةٌ");
        val.put("arti", "Kalung");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سِوَارٌ");
        val.put("arti", "Gelang");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قُرْطٌ");
        val.put("arti", "Anting-anting");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَاتِمٌ");
        val.put("arti", "Cincin");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تَاجٌ");
        val.put("arti", "Mahkota");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَاعَةُ اليَدِ");
        val.put("arti", "Jam tangan");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ذَهَبٌ");
        val.put("arti", "Emas");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فِضَّةٌ");
        val.put("arti", "Perak");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لُؤْلُؤٌ");
        val.put("arti", "Mutiara");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَوْهَرَةٌ");
        val.put("arti", "Berlian");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَيْتٌ");
        val.put("arti", "Rumah");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "غُصْبٌ");
        val.put("arti", "Ranting");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَابٌ");
        val.put("arti", "Pintu");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَدِيْنَةٌ");
        val.put("arti", "Kota");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَسْجِدٌ");
        val.put("arti", "Mesjid");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَرْيَةٌ");
        val.put("arti", "Desa");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَقْعَدٌ");
        val.put("arti", "Bangku");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَحْفَظَةٌ");
        val.put("arti", "Tas");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَافِذَةٌ");
        val.put("arti", "Jendela");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِفْتَاحٌ");
        val.put("arti", "Kunci");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِمْحَاةٌ");
        val.put("arti", "Penghapus pensil");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَلاَّسَةٌ");
        val.put("arti", "Penghapus papan tulis");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِنَشَّةٌ");
        val.put("arti", "Kemoceng");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حُجْرَةٌ");
        val.put("arti", "Kamar");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَدِيْقَةٌ");
        val.put("arti", "Taman");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِرْآةٌ");
        val.put("arti", "Cermin");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَلاَّجَةٌ");
        val.put("arti", "Kulkas");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَهْوَةٌ");
        val.put("arti", "Kopi");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَاعَةٌ");
        val.put("arti", "Jam");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَرِيْدَةٌ");
        val.put("arti", "Koran");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَجَلَّةٌ");
        val.put("arti", "Majalah");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِنْشَفَةٌ");
        val.put("arti", "Handuk");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِدَارَةٌ");
        val.put("arti", "Kantor");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وِسَادَةٌ");
        val.put("arti", "Bantal");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بِرْكَةٌ");
        val.put("arti", "Kolam");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَرَاجَةٌ");
        val.put("arti", "Sepeda");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَيَّارَةٌ");
        val.put("arti", "Mobil");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَافِلَةٌ");
        val.put("arti", "Bis");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَائِرَةٌ");
        val.put("arti", "Pesawat");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَفِيْنَةٌ");
        val.put("arti", "Perahu");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِقْلَمَةٌ");
        val.put("arti", "Kotak pensil");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُثَلَّثٌ");
        val.put("arti", "Penggaris segitiga");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كُرَّاسَةٌ");
        val.put("arti", "Buku tulis");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قِرْطَاسٌ");
        val.put("arti", "Kertas");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِلْعَقَةٌ");
        val.put("arti", "Sendok");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "شَوْكَةٌ");
        val.put("arti", "Garfu");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَحْنٌ");
        val.put("arti", "Piring");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سِكِّيْنٌ");
        val.put("arti", "Pisau");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِكْوَاةٌ");
        val.put("arti", "Setrika");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قِدْرٌ");
        val.put("arti", "Panci");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كُوْبٌ");
        val.put("arti", "Gelas");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِبْرِيْقٌ");
        val.put("arti", "Teko");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "غَسَّالَةٌ");
        val.put("arti", "Mesin cuci");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مُكَيِّفٌ");
        val.put("arti", "AC");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مِدْفَأَةٌ");
        val.put("arti", "Penghangan ruangan");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "هَاتِفٌ");
        val.put("arti", "Telpon");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فِرَاشٌ");
        val.put("arti", "Kasur");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لِحَافٌ");
        val.put("arti", "Selimut");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَرِيْرٌ");
        val.put("arti", "Ranjang");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَعْلٌ");
        val.put("arti", "Sendal");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَغْرَفَةٌ");
        val.put("arti", "Gayung");
        val.put("kategori", "Benda");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِحْتَارَ - يَحْتَارُ");
        val.put("arti", "Memilih");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِرْتَفَعَ - يَرْتَفِعُ ");
        val.put("arti", "Meningkat");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِسْتَحَمَ - يَسْتَحِمُ");
        val.put("arti", "Mandi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِسْتَغْرَقَ - يَسْتَغْرِقُ");
        val.put("arti", "Memerlukan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِسْتَيْقَظَ - يَسْتَيْقِظُ");
        val.put("arti", "Bangun");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِسْتَرَى - يَسْتَرِى");
        val.put("arti", "Membeli");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِعْتَمَرَ - يَعْتَمِرُ");
        val.put("arti", "Berumroh");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِقْتَرَبَ - يَقْتَرِبُ");
        val.put("arti", "Mendekat");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "اِنْتَظَرَ - يَنْتَظِرُ");
        val.put("arti", "Menanti");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "اِنْتَقَلَ - يَنْتَقِلُ");
        val.put("arti", "Berpindah");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "اِنْتَهَى - يَنْتَهِى");
        val.put("arti", "Habis");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَحَبَّ - يُحِبُّ");
        val.put("arti", "Mencintai");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَخَذَ - يَأْخُذُ");
        val.put("arti", "Mengambil");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَرَادَ - يُرِيْدُ");
        val.put("arti", "Ingin");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَشْعَلَ - يُشْعِلُ");
        val.put("arti", "Menyalakan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَصَابَ - يُصِيْبُ");
        val.put("arti", "Mengenai");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَعْطَى - يُعْطِى");
        val.put("arti", "Memberi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَقَامَ - يُقِيْمُ");
        val.put("arti", "Berdiri");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَكَلَ - يَأْكُلُ");
        val.put("arti", "Makan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَمْطَرَ - يَمْطِرُ");
        val.put("arti", "Turun hujan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَيْقَظَ - يُوْقِظُ");
        val.put("arti", "Bangun");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَاعَ - يُبِيْعُ");
        val.put("arti", "Menjual");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَدَأَ - يَبْدَأُ");
        val.put("arti", "Memulai");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "بَقِيَ - يَبْقَى");
        val.put("arti", "Tetep / sisa");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تَزَوَّجَ - يَتَزَوَّجُ");
        val.put("arti", "Menikah");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تَغَيَّبَ - يَتَغَيَّبُ");
        val.put("arti", "Tidak hadir");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تَكَلَّمَ - يَتَكَلَّمُ");
        val.put("arti", "Berbicara");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تَوَضَأَ - يَتَوَضَأُ");
        val.put("arti", "Berwudhu");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَأَرَ - يَثْأُرُ");
        val.put("arti", "Membalas");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَبَتَ - يَثْبُتُ");
        val.put("arti", "Tetap");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَادَلَ - يُجَادِلُ");
        val.put("arti", "Berdebat");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَاهَدَ - يُجَاهِدُ");
        val.put("arti", "Berjihat / sungguh-sungguh");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَاوَزَ - يُجَاوِزُ");
        val.put("arti", "Melewati");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "جَلَسَ - يَجْلِسُ");
        val.put("arti", "Duduk");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَجَّ - يَحُجُّ");
        val.put("arti", "Haji");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَزِنَ - يَحْزَنُ");
        val.put("arti", "Sedih");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَسِبَ - يَحْسُبُ");
        val.put("arti", "Menghitung");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَضَرَ - يَحْضُرُ");
        val.put("arti", "Hadir");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "حَلَقَ - يَحْلُقُ");
        val.put("arti", "Mencukur");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَانَ - يَخُوْنُ");
        val.put("arti", "Berkhianat");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَرَجَ - يَخْرُجُ");
        val.put("arti", "Keluar");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَلَعَ - يَخْلَعُ");
        val.put("arti", "Melepas");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَخَلَ - يَدْخُلُ");
        val.put("arti", "Masuk");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَارَ - يَدُوْرُ");
        val.put("arti", "Berkeliling");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَرَّسَ - يُدَرِّسُ");
        val.put("arti", "Mengajar");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَرَسَ - يَدْرُسُ");
        val.put("arti", "Belajar");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَفَعَ - يَدْفَعُ");
        val.put("arti", "Membayar");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ذَاقَ - يَذُوْقُ");
        val.put("arti", "Memcicipi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ذَبَحَ - يَذْبَحُ");
        val.put("arti", "Menyembelih");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ذَهَبَ - يَذْهَبُ");
        val.put("arti", "Pergi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَأَى - يَرَى");
        val.put("arti", "Melihat");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رَمَى - يَرْمِى");
        val.put("arti", "Melempar");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَادَ - يَزِيْدُ");
        val.put("arti", "Bertambah");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَارَ - يَزُوْرُ");
        val.put("arti", "Mengunjungi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "زَيَّنَ - يُزَيِّنُ");
        val.put("arti", "Menghiasi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَاعَدَ - يُسَاعِدُ");
        val.put("arti", "Membantu");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَافَرَ - يُسَافِرُ");
        val.put("arti", "Pergi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَالَ - يَسِيْلُ");
        val.put("arti", "Mengalir");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَكَنَ - يَسْكِنُ");
        val.put("arti", "Tinggal");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَمِعَ - يَسْمَعُ");
        val.put("arti", "Mendengarkan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "شَاهَدَ - يُشَاهِدُ");
        val.put("arti", "Menyaksikan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "شَرِبَ - يَشْرَبُ");
        val.put("arti", "Minum");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "شَعَرَ - يَشْعُرُ");
        val.put("arti", "Merasakan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَامَ - يَصُوْمُ");
        val.put("arti", "Puasa");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَلَّى - يُصَلِّى");
        val.put("arti", "Sholat");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ضَاعَ - يَضِيْعُ");
        val.put("arti", "Hilang");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ضَحِكَ - يَضْحَكُ");
        val.put("arti", "Tertawa");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ضَرَبَ - يَضْرِبُ");
        val.put("arti", "Memukul");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَبَخَ - يَطْبُخُ");
        val.put("arti", "Memasak");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "طَلَبَ - يَطْلُبُ");
        val.put("arti", "Meminta");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ظَلَمَ - يَظْلِمُ");
        val.put("arti", "Dzalim");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ظَنَّ - يَظُنُّ");
        val.put("arti", "Menyangka");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ظَهَرَ - يَظْهَرُ");
        val.put("arti", "Tampak");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَمِلَ - يَعْمَلُ");
        val.put("arti", "Bekerja");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "غَادَرَ - يُغَادِرُ");
        val.put("arti", "Meninggalkan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "غَسَلَ - يَغْسِلُ");
        val.put("arti", "Mencuci");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَتَحَ - يَفْتَحُ");
        val.put("arti", "Membuka");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَضَّلَ - يُفَضِّلُ");
        val.put("arti", "Lebih memilih");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَعَلَ - يَفْعَلُ");
        val.put("arti", "Melakukan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "فَقَدَ - يَفْقِدُ");
        val.put("arti", "Kehilangan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَابَلَ - يُقَابِلُ");
        val.put("arti", "Menemui");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَالَ - يَقُوْلُ");
        val.put("arti", "Berkata");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَرَأَ - يَقْرَأُ");
        val.put("arti", "Membaca");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَسَّمَ - يُقَسِّمُ");
        val.put("arti", "Membagi");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَطَفَ - يَقْطَفُ");
        val.put("arti", "Memetik");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "قَطَعَ - يَقْطَعُ");
        val.put("arti", "Memotong");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَتَبَ - يَكْتُبُ");
        val.put("arti", "Menulis");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَنَسَ - يَكْنُسُ");
        val.put("arti", "Menyapu");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "كَوَى - يَكْوِى");
        val.put("arti", "Menyetrika");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَامَ - يَنَامُ");
        val.put("arti", "Tidur");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَصَحَ - يَنْصَحُ");
        val.put("arti", "Menasehati");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَظَرَ - يَنْظُرُ");
        val.put("arti", "Melihat");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَفَخَ - يَنْفُخُ");
        val.put("arti", "Meniup");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَصَلَ - يَصِلُ");
        val.put("arti", "Sampai");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَضَعَ - يَضَعُ");
        val.put("arti", "Meletakkan");
        val.put("kategori", "Kata Kerja");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الوَاحِدَةُ ");
        val.put("arti", "Jam satu ");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الثَّانِيَةُ ");
        val.put("arti", "Jam dua ");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الثَّالِثَةُ ");
        val.put("arti", "Jam tiga ");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الرَّابِعَةُ ");
        val.put("arti", "Jam empat ");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الخَامِسَةُ ");
        val.put("arti", "Jam lima");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ السَّادِسَةُ ");
        val.put("arti", "Jam enam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ السَّابِعَةُ ");
        val.put("arti", "Jam tujuh");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الثَّامِنَةُ ");
        val.put("arti", "Jam delapan");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ التَّاسِعَةُ ");
        val.put("arti", "Jam sembilan");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ العَاشِرَةُ ");
        val.put("arti", "Jam sepuluh");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الحَادِيَةَ عَشْرَةَ ");
        val.put("arti", "Jam sebelas");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "السَّاعَةُ الثَّانِيَةَ عَشْرَةَ ");
        val.put("arti", "Jam duabelas");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَانِيَةٌ ج ثَوَانٍ");
        val.put("arti", "Detik");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "دَقيْقَةٌ ج دَقَائِقُ");
        val.put("arti", "Menit");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "وَ");
        val.put("arti", "Lewat");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "إِلاَّ");
        val.put("arti", "Kurang");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "الرُّبْعُ");
        val.put("arti", "Seperempat");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "النِّصْفُ");
        val.put("arti", "Setengah");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَمْسُ دَقَائِقَ");
        val.put("arti", "5 menit");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَشْرُ دَقَائِقَ");
        val.put("arti", "10 menit");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عِشْرُوْنَ دَقِيْقَةً");
        val.put("arti", "20 menit");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "رُبْعُ سَاعَةٍ");
        val.put("arti", "Limabelas menit/ seperempat jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نِصْفُ سَاعَةٍ");
        val.put("arti", "Setengah jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "صَبَاحٌ");
        val.put("arti", "Pagi");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "نَهَارٌ");
        val.put("arti", "Siang");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "مَسَاءٌ");
        val.put("arti", "Sore");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "لَيْلَةٌ");
        val.put("arti", "Malam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَاعَةٌوَاحِدَةٌ");
        val.put("arti", "Satu jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَاعَتَانِ");
        val.put("arti", "Dua jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَلاَثُ سَاعَةٍ");
        val.put("arti", "Tiga jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "أَرْبَعُ سَاعَةٍ");
        val.put("arti", "Empat jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "خَمْسُ سَاعَةٍ");
        val.put("arti", "Lima jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سِتُ سَاعَةٍ");
        val.put("arti", "Enam jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "سَبْعُ سَاعَةٍ");
        val.put("arti", "Tujuh jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "ثَمَانِيُ سَاعَةٍ");
        val.put("arti", "Delapan jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "تِسْعُ سَاعَةٍ");
        val.put("arti", "Sembilan jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kosakata", "عَشْرُ سَاعَةٍ");
        val.put("arti", "Sepuluh jam");
        val.put("kategori", "Waktu");
        sqldb.insert(ManajemenDB.TABEL_MUFRODATS, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", "Pengertian Nahwu");
        val.put("teori", "Membahas kondisi dan susuan kalimat" +
                " Lebih berfokus kepada bagaimana suatu kalimat itu disusun serta aturan-aturan yang terkait dengannya" +
                " seperti harakat, letak kata, dan bentuk kata yang tepat sehingga suatu kalimat dapat dipahami dengan mudah" +
                " Contoh : جَلَسَ زَيْدٌ (Zaid telah duduk)" +
                " Kata زَيْدٌ memiliki harakat dhommatain (ُ ). Pemberian harakat ini tidak sembarangan" +
                " melainkan ada aturan yang baku mengenai hal tersebut ");
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", "isim");
        val.put("teori", "Isim jika diterjemahkan kedalam bahasa indonesia adalah sebagai kata benda." +
                " Dalam bahasa arab, isim mencangkup kata benda, kata sifat, keterangan waktu, keterangan tempat, " +
                "dan lain sebagainya " );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", "Ciri Isim 1");
        val.put("teori", "الاَلِفُ وَ اللاَمُ Alif dan Lam" +
                " kata yang mengandung ال " +
                " Contoh : الكِتَابُ , القُرْءَانُ" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", "Ciri Isim 2");
        val.put("teori", "Tanwin التَّنْوِيْنُ" +
                " Semua kata yang berharakat tanwin baik Dhammatain(ٌ ), Fathatain(ً ), maupun Kasratain(ٍ )" +
                " Contoh : قَلَمٌ , اَبَدًا" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", "Ciri Isim 3");
        val.put("teori", "Huruf Jar الْجَارُ " +
                " Bila suatu kata didahui oleh huruf jar" +
                " Huruf Jar : اِلَى, مِنْ, بِ, فِيْ, لِ, كَ, عَنْ, عَلَىْ, رُبَّ" +
                " Contoh : ذَهَبَ زَيْدٌ اِلَى المَدْرَسَةِ" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", "Ciri Isim 4");
        val.put("teori", "Ta bulat (Marbuthoh) تَاءُالمَرْبُوْطَةٌ" +
                " Huruf Ta' (ة) biasanya disimpan di akhir kata" +
                " Contoh : مُؤْمِنَةٌ" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jumlah Isim Mufrod الاِسْمُ الْمُفْرَدُ ");
        val.put("teori", " Isim Mufrad adalah kata tunggal" +
                " Contohnya : مُسْلِمَةٌ, مُسْلٌِ (Seorang Muslim, seorang Muslimah)" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jumlah Isim Tatsniyah التَّثْنِيَّةُ");
        val.put("teori", " Tatsniyah atau Mutsanna (مُثَنَّي) adalah kata atau sesuatu yang memiliki makna dua atau ganda" +
                " Contohnya : مُسْلِمَتَانِ, مُسْلِمَانِ (Dua orang Muslim, Dua orang Muslimah) " +
                " Rumus Perubahan mufrad ke tatsniyah ada 2 yaitu : " +
                " 1. Mufrad + انْ (aani) untuk keadaan Rofa " +
                " 2. Mufrad + يْنِ (aini) untuk Nashab dan Jar" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jumlah Isim Jamak الْجَمْعُ");
        val.put("teori", " Jamak dalam bahasa arab terbagi menjadi 3 jenis Yaitu : " +
                " 1. Jamak Mudzakkar Salim (جَمْعُ مُذَكَّرٍ سَالِمٌ)" +
                " 2. Jamak Muannats Salim ( جَمْعُ مُؤَنَّثٍ سَالِمٌ)" +
                " 3. Jamak Taksir (جَمْعُ تَكْسِيْرٍ)" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jamak Mudzakkar Salim (جَمْعُ مُذَكَّرٍ سَالِمٌ) ");
        val.put("teori", " Bentuk jamak bagi isim-isim yang Mudzakkar" +
               " Contoh : مُسْلِمِيْنَ, مُسْلِمُوْنَ (Keduany memiliki arti yaitu Orang-orang Muslimin)" +
               " Rumus perubahan mufrad ke Jamak Mudzakkar Salim ada 2 yaitu : " +
               " 1. Mufrad +  وْنَ (uuna) Untuk keadaan Rofa) " +
               " 2. Mufrad + يْنَ (iina) Untuk keadaan Nashab dan Jar" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jamak Muannats Salim ( جَمْعُ مُؤَنَّثٍ سَالِمٌ) ");
        val.put("teori", " Bentuk jamak bagi isim-isim yang muannats " +
                " Contoh : مُسْلِمَاتٌ (Orang - Orang Muslimah)" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jamak Taksir (جَمْعُ تَكْسِيْرٍ) ");
        val.put("teori", " Jamak Taksir adalah jamak yang tidak memiliki aturan baku " +
                " jamak ini biasanya digunakan untuk kata benda mati seperti : Pulpen, Buku, Pensil dan sebagainya. " +
                " Akan tetapi nama benda mati yang mengandung Ta Marbuthah ( ٌ  ), bisa diubah ke jamak muannats " +
                " Contoh : شَجَرَةٌ (Pohon), -> شَجَرَاتٌ (Pohon-pohon)." +
                " Jamak Taksir terdiri dari 2 jenis yaitu : " +
                " 1. Jamak Taksir Lil'Aqil " +
                " 2. Jamak Taksir Lighairil 'Aqil" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jamak Taksir Lil 'Aqil ");
        val.put("teori", " Jamak taksir untuk yang berakal " +
                " Contoh : رَجُلٌ - رِجَالٌ (Laki-laki)" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jamak Taksir Lighairil 'Aqil ");
        val.put("teori", " Jamak taksir untuk kata benda " +
                " Contoh : كِتَابٌ - كُتُبٌ (Buku)" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jenis Isim  ");
        val.put("teori", " Dalam bahsa arab, dikenal pembagian kata berdasarkan jenis " +
                " seperti kata jenis laki-laki (maskulin / Mudzakkar) dan jenis wanita " +
                "(feminin / Muannats baik untuk manusia maupun untuk benda) " +
                " Isim berdasarkan jenisnya dibedakan menjadi 2 :" +
                " 1. Isim Mudzakkar ( الاِسْمُ الْمُذَكَّرُ)" +
                " 2. Isim Muannats (الاِسْمُ الْمُؤَنَّثُ)" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jenis Isim : 1. Isim Mudzakkar ( الاِسْمُ الْمُذَكَّرُ)");
        val.put("teori", " Isim Mudzakkar memiliki arti laki-laki. yang mana semua" +
                " nama manusia dan nama benda yang tidak mengandung huruf ta Marbuthah (ة)" +
                " contoh : 1. Nama Orang : اَحْمَدُ, زَيْدٌ, نُوْحٌ)" +
                "2. Nama Benda : كِتَابٌ (Buku), قَلَمٌ (Pulpen)" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Jenis Isim : 2. Isim Muannats (الاِسْمُ الْمُؤَنَّثُ)");
        val.put("teori", " Isim Muannats memiliki arti wanita. yang mana semua" +
                " nama manusia dan nama benda yang mengandung huruf ta Marbuthah (ة), " +
                " contoh : 1. Nama Orang : فَاطِمَةٌ, عَائِشَةٌ" +
                "2. Nama Benda : مَدْرَسَةٌ(Sekolah), مِرْوَحَةٌ(Kipas Angin)" +
                " Serta benda yang berpasangan maka akan termaksud Muannats  " +
                " Contoh : (Telinga)  أُذُنٌ,  (Bumi dan Matahari) أرْدٌ و شَمْسٌ )" );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Isim Ditinjau dari Umum dan Khusus ");
        val.put("teori", " Ditinjau dari keumuman dan kekhususan kata, " +
                " Isim dibedakan menjadi 2 yaitu :" +
                " 1. Isim Ma'rifah ( Kata Khusus )" +
                " 2. Isim Nakirah ( Kata Umum ) " );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Isim Ma'rifah ( اِسْمُ مَعْرِفَةٌ ) ");
        val.put("teori", " Isim Ma'rifah ( Kata Khusus ) adalah kata obyek pembicaraannya telah ditentukan." +
                " contoh : هَذَا كِتَابُ العَرَبِيَّةِ ( ini adalah buku bahasa arab ) Maka Kata buku ( كِتَابُ ) termaksud Ma'rifah" +
                " karena buku ditentukan jenisnya yaitu ( العَرَبِيَّةِ) yang mana Buku berjenis Bahasa Arab. " );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);

        val = new ContentValues();
        val.put("kaidah_nahwu", " Isim Nakirah ( اِسْمُ نَكِرَةٌ ) ");
        val.put("teori", " Isim Nakirah ( Kata Umum ) adalah kata yang objeknya tidak ditentukan." +
                " Artinya mencangkup semua kriteria yang masuk dalam cakupan pembicaraan" +
                " contoh : هَذَا كِتَابُ ( ini adalah buku ) Maka kata buku ( كِتَابُ ) termaksud Nakirah (umum)" +
                " karena kata buku belum ditentukan atau dijelaskan apakah ini buku bahasa indonesia atau buku bahasa arab" +
                " ataupun buku milik siapa " );
        val.put("gambar_tabel_nahwu", "-");
        sqldb.insert(ManajemenDB.TABEL_NAHWU, null, val);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int i, int i1) {
        sqldb.execSQL(cont.getResources().getString(R.string.hapustabel));
        onCreate(sqldb);
    }

    public class StrukturTabel {
        private String[] data;

        public StrukturTabel(String[] data) {
            this.data = new String[data.length];

            for(int i = 0; i < data.length; i++) {
                this.data[i] = data[i];
            }
        }

        public void setData(int indexField, String data) {
            this.data[indexField] = data;
        }

        public String dapatkanData(int indexField) {
            return this.data[indexField];
        }

        public int dapatkanJumlahField() {
            return this.data.length;
        }
    }
}
