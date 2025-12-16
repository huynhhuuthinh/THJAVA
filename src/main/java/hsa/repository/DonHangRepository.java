package hsa.repository;

import hsa.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
    List<DonHang> findByTrangThai(DonHang.TrangThaiDonHang trangThai);
    List<DonHang> findByBanAnId(Integer banAnId);
}