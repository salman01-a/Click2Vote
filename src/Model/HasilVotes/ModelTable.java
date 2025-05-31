/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HasilVotes;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author salmanfaris
 */
public class ModelTable extends AbstractTableModel {
    private final List<ModelHasilVotes> data;
    private final String[] columnNames = {"No", "Nama Kandidat", "Jumlah Suara"};

    public ModelTable(List<ModelHasilVotes> data) {
        this.data = data;
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
    public Object getValueAt(int row, int col) {
        ModelHasilVotes hasil = data.get(row);
        return switch (col) {
            case 0 -> row + 1;
            case 1 -> hasil.getKandidat();
            case 2 -> hasil.getJumlahSuara();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
