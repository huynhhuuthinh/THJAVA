package hsa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mon_an")
public class MonAn {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ten_mon", nullable = false, length = 200)
    private String tenMon;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;
    
    @Column(name = "gia", nullable = false, precision = 10, scale = 2)
    private BigDecimal gia;
    
    @Column(name = "hinh_anh")
    private String hinhAnh;
    
    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc danhMuc;
    
    @Column(name = "trang_thai")
    private Boolean trangThai;
    
    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;
    
    // Constructors
    public MonAn() {
        this.trangThai = true;
        this.ngayTao = LocalDateTime.now();
    }
    
    public MonAn(String tenMon, String moTa, BigDecimal gia, DanhMuc danhMuc) {
        this.tenMon = tenMon;
        this.moTa = moTa;
        this.gia = gia;
        this.danhMuc = danhMuc;
        this.trangThai = true;
        this.ngayTao = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTenMon() {
        return tenMon;
    }
    
    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public BigDecimal getGia() {
        return gia;
    }
    
    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }
    
    public String getHinhAnh() {
        return hinhAnh;
    }
    
    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    public DanhMuc getDanhMuc() {
        return danhMuc;
    }
    
    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
    
    public Boolean getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    public LocalDateTime getNgayTao() {
        return ngayTao;
    }
    
    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}