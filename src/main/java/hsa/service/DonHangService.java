package hsa.service;

import hsa.entity.*;
import hsa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DonHangService {
    
    @Autowired
    private DonHangRepository donHangRepository;
    
    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;
    
    @Autowired
    private BanAnService banAnService;
    
    @Autowired
    private MonAnService monAnService;
    
    public List<DonHang> getAllDonHang() {
        return donHangRepository.findAll();
    }
    
    public List<DonHang> getDonHangByTrangThai(DonHang.TrangThaiDonHang trangThai) {
        return donHangRepository.findByTrangThai(trangThai);
    }
    
    public Optional<DonHang> getDonHangById(Integer id) {
        return donHangRepository.findById(id);
    }
    
    @Transactional
    public DonHang taoDonHang(Integer banAnId) {
        Optional<BanAn> banAnOpt = banAnService.getBanAnById(banAnId);
        if (banAnOpt.isPresent()) {
            BanAn banAn = banAnOpt.get();
            
            DonHang donHang = new DonHang();
            donHang.setBanAn(banAn);
            donHang.setTrangThai(DonHang.TrangThaiDonHang.DANG_CHO);
            donHang = donHangRepository.save(donHang);
            
            // Cập nhật trạng thái bàn
            banAnService.updateTrangThai(banAnId, BanAn.TrangThaiBan.DANG_SU_DUNG);
            
            return donHang;
        }
        return null;
    }
    
    @Transactional
    public void themMonVaoDonHang(Integer donHangId, Integer monAnId, Integer soLuong, String ghiChu) {
        Optional<DonHang> donHangOpt = donHangRepository.findById(donHangId);
        Optional<MonAn> monAnOpt = monAnService.getMonAnById(monAnId);
        
        if (donHangOpt.isPresent() && monAnOpt.isPresent()) {
            DonHang donHang = donHangOpt.get();
            MonAn monAn = monAnOpt.get();
            
            ChiTietDonHang chiTiet = new ChiTietDonHang();
            chiTiet.setDonHang(donHang);
            chiTiet.setMonAn(monAn);
            chiTiet.setSoLuong(soLuong);
            chiTiet.setGia(monAn.getGia());
            chiTiet.setGhiChu(ghiChu);
            chiTiet.tinhThanhTien();
            
            chiTietDonHangRepository.save(chiTiet);
            
            // Cập nhật tổng tiền
            capNhatTongTien(donHangId);
        }
    }
    
    @Transactional
    public void capNhatTongTien(Integer donHangId) {
        Optional<DonHang> donHangOpt = donHangRepository.findById(donHangId);
        if (donHangOpt.isPresent()) {
            DonHang donHang = donHangOpt.get();
            List<ChiTietDonHang> chiTietList = chiTietDonHangRepository.findByDonHangId(donHangId);
            
            BigDecimal tongTien = chiTietList.stream()
                .map(ChiTietDonHang::getThanhTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            donHang.setTongTien(tongTien);
            donHang.setNgayCapNhat(LocalDateTime.now());
            donHangRepository.save(donHang);
        }
    }
    
    @Transactional
    public void capNhatTrangThai(Integer donHangId, DonHang.TrangThaiDonHang trangThai) {
        Optional<DonHang> donHangOpt = donHangRepository.findById(donHangId);
        if (donHangOpt.isPresent()) {
            DonHang donHang = donHangOpt.get();
            donHang.setTrangThai(trangThai);
            donHang.setNgayCapNhat(LocalDateTime.now());
            donHangRepository.save(donHang);
            
            // Nếu hoàn thành hoặc hủy, cập nhật trạng thái bàn
            if (trangThai == DonHang.TrangThaiDonHang.HOAN_THANH || 
                trangThai == DonHang.TrangThaiDonHang.DA_HUY) {
                if (donHang.getBanAn() != null) {
                    banAnService.updateTrangThai(donHang.getBanAn().getId(), BanAn.TrangThaiBan.TRONG);
                }
            }
        }
    }
    
    public void xoaChiTiet(Integer chiTietId, Integer donHangId) {
        chiTietDonHangRepository.deleteById(chiTietId);
        capNhatTongTien(donHangId);
    }
}