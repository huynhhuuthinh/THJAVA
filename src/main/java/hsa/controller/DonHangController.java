package hsa.controller;

import hsa.entity.DonHang;
import hsa.service.DonHangService;
import hsa.service.BanAnService;
import hsa.service.MonAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/donhang")
public class DonHangController {
    
    @Autowired
    private DonHangService donHangService;
    
    @Autowired
    private BanAnService banAnService;
    
    @Autowired
    private MonAnService monAnService;
    
    @GetMapping
    public String listDonHang(Model model) {
        model.addAttribute("donHangList", donHangService.getAllDonHang());
        return "admin/donhang/list";
    }
    
    @GetMapping("/tao")
    public String showTaoDonHang(Model model) {
        model.addAttribute("banAnList", banAnService.getBanTrong());
        return "admin/donhang/chon-ban";
    }
    
    @PostMapping("/tao")
    public String taoDonHang(@RequestParam Integer banAnId) {
        DonHang donHang = donHangService.taoDonHang(banAnId);
        if (donHang != null) {
            return "redirect:/admin/donhang/chi-tiet/" + donHang.getId();
        }
        return "redirect:/admin/donhang/tao";
    }
    
    @GetMapping("/chi-tiet/{id}")
    public String chiTietDonHang(@PathVariable Integer id, Model model) {
        DonHang donHang = donHangService.getDonHangById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        model.addAttribute("donHang", donHang);
        model.addAttribute("monAnList", monAnService.getMonAnDangBan());
        return "admin/donhang/chi-tiet";
    }
    
    @PostMapping("/them-mon")
    public String themMon(@RequestParam Integer donHangId,
                         @RequestParam Integer monAnId,
                         @RequestParam Integer soLuong,
                         @RequestParam(required = false) String ghiChu) {
        donHangService.themMonVaoDonHang(donHangId, monAnId, soLuong, ghiChu);
        return "redirect:/admin/donhang/chi-tiet/" + donHangId;
    }
    
    @GetMapping("/xoa-mon/{chiTietId}/{donHangId}")
    public String xoaMon(@PathVariable Integer chiTietId, @PathVariable Integer donHangId) {
        donHangService.xoaChiTiet(chiTietId, donHangId);
        return "redirect:/admin/donhang/chi-tiet/" + donHangId;
    }
    
    @PostMapping("/cap-nhat-trang-thai")
    public String capNhatTrangThai(@RequestParam Integer donHangId,
                                   @RequestParam String trangThai) {
        donHangService.capNhatTrangThai(donHangId, DonHang.TrangThaiDonHang.valueOf(trangThai));
        return "redirect:/admin/donhang/chi-tiet/" + donHangId;
    }
}