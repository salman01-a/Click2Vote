/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Votes;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author salmanfaris
 */
public class ModelTable extends AbstractTableModel{
private final List<ModelVotes> data;
    private final String[] columnNames = {"No", "Nama Pemilih", "Waktu Memilih"};

    public ModelTable(List<ModelVotes> data) {
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
        ModelVotes vote = data.get(row);
        return switch (col) {
            case 0 -> row + 1; // Urutan baris, bukan id_vote
            case 1 -> vote.getPemilih();
            case 2 -> vote.getWaktu();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
    

