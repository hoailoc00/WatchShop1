package com.example.WatchShop1.Service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.CartEntity;
import com.example.WatchShop1.Repository.CartRepository;
import com.example.WatchShop1.Service.ICartService;

@Service
public class CartService implements ICartService{
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Boolean addProductToCart(CartEntity cartEntity) {
		try {
			this.cartRepository.save(cartEntity);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delProductCart(int account_id) {
		try {
			return this.cartRepository.delProductCart(account_id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CartEntity> listCart(int account_id) {
		return this.cartRepository.listCart(account_id);
	}

	@Override
	public int totalCart(int account_id) {
		if(!this.cartRepository.listCart(account_id).isEmpty()) {
			return this.cartRepository.Total(account_id);
		}
		return 0;
	}

	@Override
	public Boolean updateProductCart(int quantity, int product_id) {
		try {
			this.cartRepository.updateProductCart(quantity, product_id);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CartEntity findProductFromCart(int product_id, int account_id) {
		return this.cartRepository.findProductFromCart(product_id, account_id);
	}

	@Override
	public Boolean delOneProductCart(int account_id, int product_id) {
		try {
			this.cartRepository.delOneProductCart(account_id, product_id);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
