package com.example.WatchShop1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.WatchShop1.Entity.OrderDetailEntity;
import com.example.WatchShop1.Entity.OrderEntity;
import com.example.WatchShop1.Service.iml.OrderDetailService;
import com.example.WatchShop1.Service.iml.OrderService;
@Controller
@RequestMapping("/admin")
public class OrderController {
	private OrderService orderServiceImpl;
	private OrderDetailService orderDetailServiceImpl;
	
	@Autowired
	public OrderController(OrderService orderServiceImpl, OrderDetailService orderDetailServiceImpl) {
		this.orderServiceImpl = orderServiceImpl;
		this.orderDetailServiceImpl = orderDetailServiceImpl;
	}
	
	@GetMapping("/order/pagecontrol")
	public String getPage(@RequestParam("page") String page, Model model) {
	    if ("QLĐH".equals(page)) {
	        List<OrderEntity> orderList = orderServiceImpl.getAllOrders();
	        model.addAttribute("orders", orderList);
	        List<OrderDetailEntity> orderdetailist = orderDetailServiceImpl.getAllOrderDetails();
	        model.addAttribute("orderdetails", orderdetailist);
	        model.addAttribute("page","QLĐH");
	        return "/admin/doc/index_admin";  
	    } else {
	        
	        return "error"; 
	    }
	}
	@GetMapping("/order_detail/pagecontrol")
	public String getOrderDetailPage(@RequestParam("page") String page,
			@RequestParam("order_id") int orderId,
			Model model) {
		if ("Order_Detail".equals(page)) {
			
			List<OrderDetailEntity> orderdetails = orderDetailServiceImpl.findAllByOrderId(orderId);
			// Tính tổng tiền
			double totalAmount = 0.0;
			for (OrderDetailEntity orderDetail : orderdetails) {
			    double productSalePrice = orderDetail.getProduct_order().getProduct_saleprice();
			    int productQuantity = orderDetail.getProduct_quantity();
			    totalAmount += productSalePrice * productQuantity;
			}
            model.addAttribute("orderdetails", orderdetails);
            model.addAttribute("totalAmount", totalAmount);
			model.addAttribute("page", "Order_Detail");
			return "/admin/doc/index_admin";
		} else {

			return "error";
		}
	}
	 @GetMapping("/qldonhang/approve")
	public String approveOrder(@RequestParam("order_id") int orderId, RedirectAttributes redirectAttributes) {
		
		System.out.println(orderId);
		
		Boolean result =  orderServiceImpl.approveOrder(orderId);
		if (result == true ) {
			redirectAttributes.addAttribute("approveSuccess", true);
		}else {
			redirectAttributes.addAttribute("approveSuccess", false);
		}
		
		return "redirect:/admin/order_detail/pagecontrol?page=Order_Detail&order_id=" + orderId;
	}
}
