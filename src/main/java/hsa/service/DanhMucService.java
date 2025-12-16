package hsa.service;



import hsa.entity.DanhMuc;
import hsa.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DanhMucService {
    
    @Autowired
    private DanhMucRepository danhMucRepository;
    
    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }
    
    public Optional<DanhMuc> getDanhMucById(Integer id) {
        return danhMucRepository.findById(id);
    }
    
    public DanhMuc saveDanhMuc(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }
    
    public void deleteDanhMuc(Integer id) {
        danhMucRepository.deleteById(id);
    }
}