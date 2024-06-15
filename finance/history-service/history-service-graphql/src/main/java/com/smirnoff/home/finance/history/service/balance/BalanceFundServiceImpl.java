package com.smirnoff.home.finance.history.service.balance;

import com.smirnoff.home.finance.history.persistance.entity.FundBalanceEntity;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.repository.FundBalanceRepository;
import com.smirnoff.home.finance.history.service.balance.lc.FundBalanceLifeCycle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class BalanceFundServiceImpl implements BalanceFundService {
    private final FundBalanceLifeCycle fundBalanceLifeCycle;
    private final FundBalanceRepository fundBalanceRepository;
    @Override
    public void createByOperation(OperationHistoryEntity operation) {
        if (nonNull(operation.getSourceProduct())) {
            fundBalanceRepository.save(createFundBalance(
                    operation,
                    operation.getSourceFund(),
                    operation.getSourceAmount().multiply(BigDecimal.valueOf(-1L)),
                    operation.getSourceCurrency()
            ));
        }

        if (nonNull(operation.getDestinationProduct())) {
            fundBalanceRepository.save(createFundBalance(
                    operation,
                    operation.getDestinationFund(),
                    operation.getDestinationAmount(),
                    operation.getDestinationCurrency()
            ));
        }
    }

    private FundBalanceEntity createFundBalance(OperationHistoryEntity operation,
                                                   String fund,
                                                   BigDecimal amount,
                                                   String currency) {
        FundBalanceEntity negativeOperation = fundBalanceLifeCycle.createNew();
        negativeOperation.setOperation(operation);
        negativeOperation.setFund(fund);
        negativeOperation.setAmount(amount);
        negativeOperation.setCurrency(currency);
        return negativeOperation;
    }
}
