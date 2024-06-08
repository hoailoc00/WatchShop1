package com.example.WatchShop1.Service.iml;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.AccountEntity;
import com.example.WatchShop1.Entity.BestSeller;
import com.example.WatchShop1.Entity.OrderDetailEntity;
import com.example.WatchShop1.Entity.OrderEntity;
import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Repository.AccountRepository;
import com.example.WatchShop1.Repository.OrderDetailRepository;
import com.example.WatchShop1.Repository.OrderRepository;
import com.example.WatchShop1.Repository.ProductRepository;


@Service
public class ReportService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	
	// Hàm lấy list đơn hàng trong tháng này
	public List<OrderEntity> getCurrentMonthOrders(){
		LocalDate currentDate = LocalDate.now();
		Date startOfMonth = Date.from(currentDate.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endOfMonth = Date.from(currentDate.withDayOfMonth(
				currentDate.lengthOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<OrderEntity> orders = orderRepository.findByOrderDateBetween(startOfMonth, endOfMonth);
		return orders;
	}
	
	// Hàm lấy tổng doanh thu từ các đơn hàng trong tháng này
	public int getMonthSevenue() {
		List<OrderEntity> orders = this.getCurrentMonthOrders();
		int totalSevenue = 0;
		for(OrderEntity order : orders) {
			totalSevenue += order.getOrder_money();
		}
		return totalSevenue;
	}
	
	// Hàm lấy list đơn hàng theo tháng
	public List<OrderEntity> getMonthOrders(int month){
		int currentYear = java.time.Year.now().getValue();
		Date startOfMonth = java.sql.Date.valueOf(YearMonth.of(currentYear, month).atDay(1)) ;
		Date endOfMonth = java.sql.Date.valueOf(YearMonth.of(currentYear, month).atEndOfMonth());;
		List<OrderEntity> orders = orderRepository.findByOrderDateBetween(startOfMonth, endOfMonth);
		return orders;
	}
		
	// Hàm lấy doanh thu của 1 tháng
	public int getChosenMonthSevenue(int month) {
		List<OrderEntity> orders = this.getMonthOrders(month);
		int totalSevenue = 0;
		for(OrderEntity order : orders) {
			totalSevenue += order.getOrder_money();
		}
		return totalSevenue;
	}
	
	public int getStaffNumber() {
		List<AccountEntity> accounts = accountRepository.findEmployees();
		int staffNumber = accounts.size();
		return staffNumber;
	}
	
	public int getAllProduct() {
		List<ProductEntity> products = productRepository.findAllProducts();
		int productNumber = products.size();
		return productNumber;
	}
	
	// Hàm lấy số lượng đơn hàng
	public int getOrderAmount() {
		List<OrderEntity> orders = orderRepository.findAll();
		int orderAmount = orders.size();
		return orderAmount;
	}
	
	// Hàm lấy số lượng đơn hàng đã hủy
	public int getCancelOrderAmount() {
		List<OrderEntity> orders = orderRepository.findByOrder_status("Đã hủy");
		int cancelOrderAmount = orders.size();
		return cancelOrderAmount;
	}
	
	// Hàm lấy số lượng tài khoản bị ban (isDeleled=1)
	public int getBanAmount() {
		List<AccountEntity> accounts = accountRepository.findBanAccounts();
		int banAmount = accounts.size();
		return banAmount;
	}
	
	// Hàm lấy list sản phẩm hết hàng
	public List<ProductEntity> getSoldOutList(){
		return productRepository.findSoldOut();
	}
	
	// Hàm lấy số lượng sản phẩm hết hàng (số lượng =0)
	public int getSoldOut() {
		List<ProductEntity> products = productRepository.findSoldOut();
		int soldOut = products.size();
		return soldOut;
	}
	
	// Hàm lấy số lượng sản phẩm sắp hết hàng (số lượng <=5)
	public int getRunningOutOfStock() {
		List<ProductEntity> products = productRepository.findRunningOutOfStock();
		int runningOutOfStock = products.size();
		return runningOutOfStock;
	}
	
	// Hàm lấy doanh thu của tất cả sản phẩm trong tháng này
	public List<BestSeller> getAllSeller(){
	    List<BestSeller> allSellers = new ArrayList<>();
	    List<OrderEntity> currentMonthOrders = this.getCurrentMonthOrders();
	    List<OrderDetailEntity> currentMontOrderDetails = new ArrayList<>();
	    for (OrderEntity orderEntity : currentMonthOrders) {
	        List<OrderDetailEntity> orderDetailList = orderDetailRepository.findAllByOrderId(orderEntity.getOrder_id());
	        currentMontOrderDetails.addAll(orderDetailList); // Thêm các chi tiết đơn hàng từ mỗi đơn hàng vào danh sách tổng
	    }
	    List<ProductEntity> productList = productRepository.findAllProducts();
	    for (ProductEntity product : productList) {
	        BestSeller bestSeller = new BestSeller();
	        bestSeller.setProductID(product.getProduct_id());
	        bestSeller.setProductName(product.getProduct_name());
	        int productAmount = 0;
	        for (OrderDetailEntity orderDetail : currentMontOrderDetails) {
	            if (orderDetail.getProduct_order().getProduct_id() == product.getProduct_id()) {
	                productAmount += orderDetail.getProduct_quantity();
	            }
	        }
	        bestSeller.setProductAmount(productAmount);
	        int productTurnover = product.getProduct_saleprice() * productAmount;
	        bestSeller.setProductTurnover(productTurnover);
	        allSellers.add(bestSeller);
	    }
	    return allSellers;
	}
	
	// Hàm lấy top 5 sản phẩm có doanh thu cao nhất tháng này
	public List<BestSeller> get5BestSeller(){
		List<BestSeller> allSeller = this.getAllSeller();
		Collections.sort(allSeller, (p1, p2) -> Double.compare(p2.getProductTurnover(), p1.getProductTurnover()));
		List<BestSeller> fiveBestSeller = allSeller.subList(0, Math.min(allSeller.size(), 5));
		return fiveBestSeller;
	}
	
	public List<OrderDetailEntity> getAllDetail(){
		return orderDetailRepository.findAll();
	}
	
	public List<OrderDetailEntity> getDetail(int orderID){
		return orderDetailRepository.findAllByOrderId(orderID);
		
	}
	
	// Hàm lấy dữ liệu doanh thu cho chart
	public int[] sevenueChartData() {
		int[] sevenue = new int[13];
		for(int i=1; i<=12; i++) {
			sevenue[i] = this.getChosenMonthSevenue(i);
		}
		return sevenue;
	}
	
	
}