package hsa.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "ban_an")
public class BanAn {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "so_ban", nullable = false, unique = true, length = 10)
    private String soBan;
    
    @Column(name = "so_cho_ngoi", nullable = false)
    private Integer soChoNgoi;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    private TrangThaiBan trangThai;
    
    public enum TrangThaiBan {
        TRONG, DANG_SU_DUNG, DA_DAT
    }
    
    // Constructors
    public BanAn() {
        this.trangThai = TrangThaiBan.TRONG;
    }
    
    public BanAn(String soBan, Integer soChoNgoi) {
        this.soBan = soBan;
        this.soChoNgoi = soChoNgoi;
        this.trangThai = TrangThaiBan.TRONG;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getSoBan() {
        return soBan;
    }
    
    public void setSoBan(String soBan) {
        this.soBan = soBan;
    }
    
    public Integer getSoChoNgoi() {
        return soChoNgoi;
    }
    
    public void setSoChoNgoi(Integer soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }
    
    public TrangThaiBan getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(TrangThaiBan trangThai) {
        this.trangThai = trangThai;
    }
}