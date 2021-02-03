package castudy.model;

public class Product {
    private int maSanPham;
    private String tenSanPham;
    private float giaSanPham;
    private String image;
    private String mauSac;
    private ProductLine loaiSanPham;
    private ProductVendor nhaSanXuat;
    private int soLuong;
    private String ngaySanXuat;
    private String hanSudung;
    private String moTa;
    private int soLuongMua=0;
    private int maTheLoai;
    private int maNhasanXuat;


    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public float getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(float giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getImage() {
        return image;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductLine getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(ProductLine loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public ProductVendor getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(ProductVendor nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(String ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public String getHanSudung() {
        return hanSudung;
    }

    public void setHanSudung(String hanSudung) {
        this.hanSudung = hanSudung;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public int getMaNhasanXuat() {
        return maNhasanXuat;
    }

    public void setMaNhasanXuat(int maNhasanXuat) {
        this.maNhasanXuat = maNhasanXuat;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public Product(int maSanPham, String tenSanPham, float giaSanPham, String image,String mauSac, ProductLine loaiSanPham, ProductVendor nhaSanXuat, int soLuong, String ngaySanXuat, String hanSudung, String moTa, int soLuongMua, int maTheLoai, int maNhasanXuat) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.image = image;
        this.mauSac=mauSac;
        this.loaiSanPham = loaiSanPham;
        this.nhaSanXuat = nhaSanXuat;
        this.soLuong = soLuong;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSudung = hanSudung;
        this.moTa = moTa;
        this.soLuongMua = soLuongMua;
        this.maNhasanXuat = maNhasanXuat;
        this.maTheLoai = maTheLoai;
    }

    public Product(int maSanPham, String tenSanPham, float giaSanPham, String image,String mauSac, ProductLine loaiSanPham, ProductVendor nhaSanXuat, int soLuong, String ngaySanXuat, String hanSudung, String moTa, int maTheLoai, int maNhasanXuat) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.image = image;
        this.mauSac=mauSac;
        this.loaiSanPham = loaiSanPham;
        this.nhaSanXuat = nhaSanXuat;
        this.soLuong = soLuong;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSudung = hanSudung;
        this.moTa = moTa;
        this.maTheLoai = maTheLoai;
        this.maNhasanXuat = maNhasanXuat;

    }

    public Product(String tenSanPham, float giaSanPham, String image,String mauSac, ProductLine loaiSanPham, ProductVendor nhaSanXuat, int soLuong, String ngaySanXuat, String hanSudung, String moTa, int maTheLoai,int maNhasanXuat) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.image = image;
        this.mauSac=mauSac;
        this.loaiSanPham = loaiSanPham;
        this.nhaSanXuat = nhaSanXuat;
        this.soLuong = soLuong;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSudung = hanSudung;
        this.moTa = moTa;
        this.maTheLoai = maTheLoai;
        this.maNhasanXuat = maNhasanXuat;
    }

}
