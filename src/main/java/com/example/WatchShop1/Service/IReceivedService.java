package com.example.WatchShop1.Service;

import com.example.WatchShop1.Entity.ReceivedEntity;

public interface IReceivedService {
	ReceivedEntity addReceived(ReceivedEntity receivedEntity );
	ReceivedEntity findReceivedById(int id);
}
