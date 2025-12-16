package hsa.repository;

import hsa.entity.BanAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BanAnRepository extends JpaRepository<BanAn, Integer> {
    List<BanAn> findByTrangThai(BanAn.TrangThaiBan trangThai);
}