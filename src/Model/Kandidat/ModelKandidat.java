/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandidat;

/**
 *
 * @author salmanfaris
 */
public class ModelKandidat {
    int id;
    String nama,photo_url, description,no_urut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
            return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNo_urut() {
        return no_urut;
    }

    public void setNo_urut(String no_urut) {
        this.no_urut = no_urut;
    }
}
