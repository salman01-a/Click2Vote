package Model.Votes;

public class ModelVotes {
    private int id;
    private String pemilih;
    private String kandidat;

    public ModelVotes(int id, String pemilih, String kandidat) {
        this.id = id;
        this.pemilih = pemilih;
        this.kandidat = kandidat;
    }

    public int getId() {
        return id;
    }

    public String getPemilih() {
        return pemilih;
    }

    public String getKandidat() {
        return kandidat;
    }
}
