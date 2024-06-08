package com.example.WatchShop1.Service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Entity.ReceivedDetailDTO;
import com.example.WatchShop1.Entity.ReceivedDetailEntity;
import com.example.WatchShop1.Entity.ReceivedEntity;
import com.example.WatchShop1.Repository.ProductRepository;
import com.example.WatchShop1.Repository.ReceivedDetailRepository;
import com.example.WatchShop1.Repository.ReceivedRepository;
import com.example.WatchShop1.Service.IReceivedDetailService;

@Service
public class ReceidvedDetailService implements IReceivedDetailService{
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired 
	private ReceivedDetailRepository receivedDetailRepository;
	@Autowired
	private ReceivedRepository receivedRepository;
	@Override
	public Boolean createReceivedDetail(ReceivedDetailDTO receivedDetailDTO) {
		// TODO Auto-generated method stub
		ProductEntity product =  productRepository.findByUsername(receivedDetailDTO.getProduct_name());
		
		ReceivedDetailEntity receiveddetail = new ReceivedDetailEntity();
		receiveddetail.setProduct_quantity(receivedDetailDTO.getProduct_quantity());
		
		ReceivedEntity received = new ReceivedEntity();
		received.setReceived_id(receivedDetailDTO.getReceived_id());
		receiveddetail.setReceivedEntity(received);
		
		
		ReceivedEntity receivedofdetail = receivedRepository.findById(receivedDetailDTO.getReceived_id()).orElse(null);
		
		
		receiveddetail.setProduct_inprice(receivedDetailDTO.getProduct_inprice());
		
		ProductEntity product_received = new ProductEntity();
		product_received.setProduct_id(product.getProduct_id());
		receiveddetail.setProduct_received(product_received);
		
		try {
			//luu chi tiet don hang
			ReceivedDetailEntity savedReceivedDetail = receivedDetailRepository.save(receiveddetail);
			//update tien cua don nhap
			int money_savedReceivedDetail = savedReceivedDetail.getProduct_inprice();
			receivedofdetail.setReceived_money(receivedofdetail.getReceived_money() + money_savedReceivedDetail);
			receivedRepository.save(receivedofdetail);
			//update soluong cua san pham
			product.setProduct_inventory(product.getProduct_inventory() + savedReceivedDetail.getProduct_quantity());
			productRepository.save(product);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<ReceivedDetailEntity> findAllById(int id) {
		// TODO Auto-generated method stub
		return receivedDetailRepository.findAllById(id);
	}

}
