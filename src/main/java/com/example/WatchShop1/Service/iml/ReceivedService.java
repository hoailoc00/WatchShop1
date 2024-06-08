package com.example.WatchShop1.Service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.ReceivedEntity;
import com.example.WatchShop1.Repository.ReceivedRepository;
import com.example.WatchShop1.Service.IReceivedService;

@Service
public class ReceivedService implements IReceivedService{
	@Autowired
	private ReceivedRepository receivedRepository;
	
	@Override
	public ReceivedEntity addReceived(ReceivedEntity receivedEntity) {
		// TODO Auto-generated method stub
		ReceivedEntity savedEntity = receivedRepository.save(receivedEntity);

        
        return savedEntity;
	}

	@Override
	public ReceivedEntity findReceivedById(int id) {
		// TODO Auto-generated method stub
		return receivedRepository.findById(id).orElse(null);
	}
	

}
