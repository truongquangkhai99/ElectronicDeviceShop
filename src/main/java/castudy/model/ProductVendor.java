package castudy.model;

public class ProductVendor {
    private int maNhaSanXuat;
    private String tenNhaSanXuat;
    private String diaChi;
    private String email;
    private String sđt;
    private String image;

    public ProductVendor(int maNhaSanXuat, String tenNhaSanXuat,String diaChi,String email,String sđt,String image) {
        this.maNhaSanXuat = maNhaSanXuat;
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.diaChi=diaChi;
        this.email=email;
        this.sđt=sđt;
        this.image=image;
    }

    public ProductVendor() {
    }


    public ProductVendor(String tenNhaSanXuat,String diaChi,String email,String sđt,String image) {
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.diaChi=diaChi;
        this.email=email;
        this.sđt=sđt;
        this.image=image;
    }

    public int getMaNhaSanXuat() {
        return maNhaSanXuat;
    }

    public void setMaNhaSanXuat(int maNhaSanXuat) {
        this.maNhaSanXuat = maNhaSanXuat;
    }

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSđt() {
        return sđt;
    }

    public void setSđt(String sđt) {
        this.sđt = sđt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
