package hsa.repository;

import hsa.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Integer> {
    List<MonAn> findByTrangThai(Boolean trangThai);
    List<MonAn> findByDanhMucId(Integer danhMucId);
    List<MonAn> findByDanhMucIdAndTrangThai(Integer danhMucId, Boolean trangThai);
}