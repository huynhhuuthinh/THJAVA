package hsa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "chi_tiet_don_hang")
public class ChiTietDonHang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_don_hang", nullable = false)
    private DonHang donHang;
    
    @ManyToOne
    @JoinColumn(name = "id_mon_an", nullable = false)
    private MonAn monAn;
    
    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;
    
    @Column(name = "gia", nullable = false, precision = 10, scale = 2)
    private BigDecimal gia;
    
    @Column(name = "thanh_tien", nullable = false, precision = 10, scale = 2)
    private BigDecimal thanhTien;
    
    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String ghiChu;
    
    // Constructors
    public ChiTietDonHang() {
        this.soLuong = 1;
    }
    
    public ChiTietDonHang(DonHang donHang, MonAn monAn, Integer soLuong) {
        this.donHang = donHang;
        this.monAn = monAn;
        this.soLuong = soLuong;
        this.gia = monAn.getGia();
        this.tinhThanhTien();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public DonHang getDonHang() {
        return donHang;
    }
    
    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }
    
    public MonAn getMonAn() {
        return monAn;
    }
    
    public void setMonAn(MonAn monAn) {
        this.monAn = monAn;
    }
    
    public Integer getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
        this.tinhThanhTien();
    }
    
    public BigDecimal getGia() {
        return gia;
    }
    
    public void setGia(BigDecimal gia) {
        this.gia = gia;
        this.tinhThanhTien();
    }
    
    public BigDecimal getThanhTien() {
        return thanhTien;
    }
    
    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }
    
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    // Helper method
    public void tinhThanhTien() {
        if (this.gia != null && this.soLuong != null) {
            this.thanhTien = this.gia.multiply(new BigDecimal(this.soLuong));
        }
    }
}