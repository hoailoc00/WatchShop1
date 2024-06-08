package com.example.WatchShop1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WatchShop1.Entity.BestSeller;
import com.example.WatchShop1.Entity.OrderDetailEntity;
import com.example.WatchShop1.Entity.OrderEntity;
import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Repository.OrderDetailRepository;
import com.example.WatchShop1.Service.iml.ReportService;
@Controller
@RequestMapping("/admin")
public class ReportController {
	private ReportService reportService;
	
	@Autowired
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@GetMapping("/report/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
	    if ("QLBC".equals(page)) {
	    	int totalSevenue = reportService.getMonthSevenue();
	    	model.addAttribute("totalSevenue", totalSevenue);
	    	int staffNumber = reportService.getStaffNumber();
	    	model.addAttribute("staffNumber",staffNumber);
	    	int productNumber = reportService.getAllProduct();
	    	model.addAttribute("productNumber", productNumber);
	    	int orderAmount = reportService.getOrderAmount();
	    	model.addAttribute("orderAmount", orderAmount);
	    	int cancelOrderAmount = reportService.getCancelOrderAmount();
	    	model.addAttribute("cancelOrderAmount", cancelOrderAmount);
	    	int banAmount = reportService.getBanAmount();
	    	model.addAttribute("banAmount", banAmount);
	    	int soldOut = reportService.getSoldOut();
	    	model.addAttribute("soldOut", soldOut);
	    	List<BestSeller> fiveBestSeller = reportService.get5BestSeller();
	    	model.addAttribute("fiveBestSeller", fiveBestSeller);
	    	List<ProductEntity> soldOutList = reportService.getSoldOutList();
	    	model.addAttribute("soldOutList", soldOutList);
	    	List<OrderEntity> ordersOfMonth = reportService.getCurrentMonthOrders();
	    	model.addAttribute("ordersOfMonth", ordersOfMonth);
	        model.addAttribute("page","QLBC");
	        return "/admin/doc/index_admin";  // Return the name of the view to be displayed
	    } else {
	        // Xử lý các trang khác tại đây (nếu có)
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}
}