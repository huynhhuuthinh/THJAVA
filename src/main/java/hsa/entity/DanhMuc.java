package hsa.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "danh_muc")
public class DanhMuc {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ten_danh_muc", nullable = false, length = 100)
    private String tenDanhMuc;
    
    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;
    
    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;
    
    @OneToMany(mappedBy = "danhMuc", cascade = CascadeType.ALL)
    private List<MonAn> danhSachMonAn;
    
    // Constructors
    public DanhMuc() {
        this.ngayTao = LocalDateTime.now();
    }
    
    public DanhMuc(String tenDanhMuc, String moTa) {
        this.tenDanhMuc = tenDanhMuc;
        this.moTa = moTa;
        this.ngayTao = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTenDanhMuc() {
        return tenDanhMuc;
    }
    
    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public LocalDateTime getNgayTao() {
        return ngayTao;
    }
    
    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    public List<MonAn> getDanhSachMonAn() {
        return danhSachMonAn;
    }
    
    public void setDanhSachMonAn(List<MonAn> danhSachMonAn) {
        this.danhSachMonAn = danhSachMonAn;
    }
}