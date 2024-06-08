package com.example.WatchShop1.Service;

import java.util.List;

import com.example.WatchShop1.Entity.ReceivedDetailDTO;
import com.example.WatchShop1.Entity.ReceivedDetailEntity;

public interface IReceivedDetailService {
	Boolean createReceivedDetail(ReceivedDetailDTO receivedDetailDTO);
	List<ReceivedDetailEntity> findAllById(int received_id);
}
