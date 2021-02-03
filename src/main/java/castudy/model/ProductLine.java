package castudy.model;

public class ProductLine {
    private int maTheLoai;
    private String tenTheLoai;
    private String moTa;


    public ProductLine(int maTheLoai, String tenTheLoai,String moTa) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa=moTa;
      ;
    }

    public ProductLine() {
    }

    public ProductLine(String tenTheLoai,String moTa) {
        this.tenTheLoai = tenTheLoai;
        this.moTa=moTa;

    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
