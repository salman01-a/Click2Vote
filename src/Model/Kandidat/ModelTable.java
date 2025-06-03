// File: src/Model/Kandidat/KandidatTableModel.java
package Model.Kandidat;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom TableModel untuk entitas ModelKandidat.
 */
public class ModelTable extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Nama", "Photo URL", "Deskripsi", "No Urut"};
    private List<ModelKandidat> data = new ArrayList<>();

    public ModelTable() {
        // constructor kosong, data bisa di-set lewat setter
    }

    public ModelTable(List<ModelKandidat> initialData) {
        this.data = new ArrayList<>(initialData);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModelKandidat k = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getId();
            case 1:
                return k.getNama();
            case 2:
                return k.getPhoto_url();
            case 3:
                return k.getDescription();
            case 4:
                return k.getNo_urut();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) return Integer.class;
        return String.class;
    }

    /** Ganti seluruh isi tabel dengan list baru, lalu notifikasi perubahan */
    public void setData(List<ModelKandidat> newData) {
        this.data = new ArrayList<>(newData);
        fireTableDataChanged();
    }

    /** Ambil objek ModelKandidat di baris tertentu */
    public ModelKandidat getKandidatAt(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= data.size()) return null;
        return data.get(rowIndex);
    }

    /** Kalau mau menambahkan satu data baru ke table model */
    public void addKandidat(ModelKandidat k) {
        data.add(k);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    /** Hapus satu baris berdasarkan row index */
    public void removeKandidatAt(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= data.size()) return;
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
