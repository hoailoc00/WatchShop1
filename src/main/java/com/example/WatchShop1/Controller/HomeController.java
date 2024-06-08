package com.example.WatchShop1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WatchShop1.Entity.AccountEntity;
import com.example.WatchShop1.Entity.BestSeller;
import com.example.WatchShop1.Entity.OrderEntity;
import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Service.iml.AccountService;
import com.example.WatchShop1.Service.iml.ProductService;
import com.example.WatchShop1.Service.iml.ReportService;
@Controller
@RequestMapping("/admin")
public class HomeController {
	private AccountService accountServiceImpl;
	private ProductService productServiceImpl;
	private ReportService reportService;
	
	@Autowired
	public HomeController(AccountService accountServiceImpl,
			ProductService productServiceImpl,
			ReportService reportService) {
		this.accountServiceImpl = accountServiceImpl;
		this.productServiceImpl = productServiceImpl;
		this.reportService = reportService;
	}
	
	@GetMapping("/home/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
	    if ("home".equals(page)) {
	    	List<AccountEntity> customers = accountServiceImpl.findCustomers();
	    	int customerNumber = customers.size();
	    	model.addAttribute("customerNumber", customerNumber);
	    	List<ProductEntity> products = productServiceImpl.findAllProducts();
	    	int productNumber = products.size();
	    	model.addAttribute("productNumber", productNumber);
	    	List<OrderEntity> currentMonthOrderList = reportService.getCurrentMonthOrders();
	    	int currentMonthOrderNumber = currentMonthOrderList.size();
	    	model.addAttribute("currentMonthOrderNumber", currentMonthOrderNumber);
	    	int runningOut = reportService.getRunningOutOfStock();
	    	model.addAttribute("runningOut", runningOut);    
	    	List<BestSeller> fiveBestSeller = reportService.get5BestSeller();
	    	model.addAttribute("fiveBestSeller", fiveBestSeller);
	    	int[] listData = reportService.sevenueChartData();
	    	model.addAttribute("listData", listData);
	        model.addAttribute("page","home");
	        return "/admin/doc/index_admin";  // Return the name of the view to be displayed
	    } else {
	        // Xử lý các trang khác tại đây (nếu có)
	        return "error"; // Ví dụ: Trả về một trang lỗi nếu page không hợp lệ
	    }
	}
}
