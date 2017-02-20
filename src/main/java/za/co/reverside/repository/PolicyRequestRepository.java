
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.PolicyRequest;
import za.co.reverside.domain.Quotation;
import za.co.reverside.domain.QuotationOption;

public interface PolicyRequestRepository  extends JpaRepository<PolicyRequest, Long> {
	public PolicyRequest findByQuotation(Quotation quotation);	
	
}
