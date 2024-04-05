package com.smirnoff.home.finance.history.client.model;

import com.smirnoff.home.finance.history.model.OperationHistoryDto;

import java.util.List;

public record GetOperationHistoryDtoList(
        List<OperationHistoryDto> getOperationList
) {
}
