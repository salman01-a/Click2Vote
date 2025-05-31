/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HasilVotes;

/**
 *
 * @author salmanfaris
 */
public class ModelHasilVotes {
    private String kandidat;
    private int jumlahSuara;

    public ModelHasilVotes  (String kandidat, int jumlahSuara) {
        this.kandidat = kandidat;
        this.jumlahSuara = jumlahSuara;
    }

    public String getKandidat() {
        return kandidat;
    }

    public int getJumlahSuara() {
        return jumlahSuara;
    }
}
