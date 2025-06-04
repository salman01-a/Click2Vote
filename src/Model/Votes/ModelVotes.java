package Model.Votes;

import java.util.Date;

public class ModelVotes {
    private int id;
    private String pemilih;
    private Date waktu;

    public ModelVotes(int id, String pemilih, Date waktu) {
        this.id = id;
        this.pemilih = pemilih;
        this.waktu = waktu;
    }

    public int getId() {
        return id;
    }

    public String getPemilih() {
        return pemilih;
    }

    public Date getWaktu() {
        return waktu;
    }
}
