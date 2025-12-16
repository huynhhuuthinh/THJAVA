package hsa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "don_hang")
public class DonHang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_ban")
    private BanAn banAn;
    
    @Column(name = "ten_khach_hang", length = 100)
    private String tenKhachHang;
    
    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;
    
    @Column(name = "tong_tien", precision = 10, scale = 2)
    private BigDecimal tongTien;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    private TrangThaiDonHang trangThai;
    
    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    private String ghiChu;
    
    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;
    
    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;
    
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiTietDonHang> chiTietDonHangList = new ArrayList<>();
    
    public enum TrangThaiDonHang {
        DANG_CHO, DANG_NAU, HOAN_THANH, DA_HUY
    }
    
    // Constructors
    public DonHang() {
        this.tongTien = BigDecimal.ZERO;
        this.trangThai = TrangThaiDonHang.DANG_CHO;
        this.ngayTao = LocalDateTime.now();
        this.ngayCapNhat = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public BanAn getBanAn() {
        return banAn;
    }
    
    public void setBanAn(BanAn banAn) {
        this.banAn = banAn;
    }
    
    public String getTenKhachHang() {
        return tenKhachHang;
    }
    
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    
    public BigDecimal getTongTien() {
        return tongTien;
    }
    
    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }
    
    public TrangThaiDonHang getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(TrangThaiDonHang trangThai) {
        this.trangThai = trangThai;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }
    
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    public LocalDateTime getNgayTao() {
        return ngayTao;
    }
    
    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    public LocalDateTime getNgayCapNhat() {
        return ngayCapNhat;
    }
    
    public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    
    public List<ChiTietDonHang> getChiTietDonHangList() {
        return chiTietDonHangList;
    }
    
    public void setChiTietDonHangList(List<ChiTietDonHang> chiTietDonHangList) {
        this.chiTietDonHangList = chiTietDonHangList;
    }
    
    // Helper method
    public void tinhTongTien() {
        this.tongTien = chiTietDonHangList.stream()
            .map(ChiTietDonHang::getThanhTien)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}