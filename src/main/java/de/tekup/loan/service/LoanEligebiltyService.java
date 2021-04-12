package de.tekup.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.tekup.loan.soap.ws.loaneligebilty.CustomerRequest;
import de.tekup.loan.soap.ws.loaneligebilty.WsResponse;

@Service
public class LoanEligebiltyService {

	public WsResponse checkCustomerEligebilty(CustomerRequest customerRequest) {
		
		WsResponse wsResponse = new WsResponse();
		
		List<String> criteriaMismatchs = wsResponse.getCriteriaMismatch();
		
		if(!(customerRequest.getAge() >=30 && customerRequest.getAge() <= 55)) {
			criteriaMismatchs.add("Customer age must be between 30 and 55");
		}
		
		if(!(customerRequest.getYearlyIncome() >= 25000)) {
			criteriaMismatchs.add("Customer yearly Income must be over 25000");
		}
		
		if(!(customerRequest.getCibilScore() >= 500)) {
			criteriaMismatchs.add("Customer cibil score must be over 500");
		}
		
		if(criteriaMismatchs.isEmpty()) {
			wsResponse.setIsEligeble(true);
			long amount = (long)(((60-customerRequest.getAge())*customerRequest.getYearlyIncome())*0.4);
			wsResponse.setApprovedAmount(amount);
		} else {
			wsResponse.setIsEligeble(false);
			wsResponse.setApprovedAmount(0);
		}
		
		
		return wsResponse;
		
	}
}
