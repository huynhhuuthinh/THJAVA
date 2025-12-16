package hsa.service;

import hsa.entity.BanAn;
import hsa.repository.BanAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BanAnService {
    
    @Autowired
    private BanAnRepository banAnRepository;
    
    public List<BanAn> getAllBanAn() {
        return banAnRepository.findAll();
    }
    
    public List<BanAn> getBanTrong() {
        return banAnRepository.findByTrangThai(BanAn.TrangThaiBan.TRONG);
    }
    
    public Optional<BanAn> getBanAnById(Integer id) {
        return banAnRepository.findById(id);
    }
    
    public BanAn saveBanAn(BanAn banAn) {
        return banAnRepository.save(banAn);
    }
    
    public void deleteBanAn(Integer id) {
        banAnRepository.deleteById(id);
    }
    
    public void updateTrangThai(Integer id, BanAn.TrangThaiBan trangThai) {
        Optional<BanAn> banAnOpt = banAnRepository.findById(id);
        if (banAnOpt.isPresent()) {
            BanAn banAn = banAnOpt.get();
            banAn.setTrangThai(trangThai);
            banAnRepository.save(banAn);
        }
    }
}