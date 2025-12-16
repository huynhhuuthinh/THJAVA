package hsa.service;

import hsa.entity.MonAn;
import hsa.repository.MonAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MonAnService {
    
    @Autowired
    private MonAnRepository monAnRepository;
    
    public List<MonAn> getAllMonAn() {
        return monAnRepository.findAll();
    }
    
    public List<MonAn> getMonAnDangBan() {
        return monAnRepository.findByTrangThai(true);
    }
    
    public List<MonAn> getMonAnByDanhMuc(Integer danhMucId) {
        return monAnRepository.findByDanhMucIdAndTrangThai(danhMucId, true);
    }
    
    public Optional<MonAn> getMonAnById(Integer id) {
        return monAnRepository.findById(id);
    }
    
    public MonAn saveMonAn(MonAn monAn) {
        return monAnRepository.save(monAn);
    }
    
    public void deleteMonAn(Integer id) {
        monAnRepository.deleteById(id);
    }
    
    public void updateTrangThai(Integer id, Boolean trangThai) {
        Optional<MonAn> monAnOpt = monAnRepository.findById(id);
        if (monAnOpt.isPresent()) {
            MonAn monAn = monAnOpt.get();
            monAn.setTrangThai(trangThai);
            monAnRepository.save(monAn);
        }
    }
}