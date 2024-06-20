package com.smirnoff.home.finance.history.adapter.fund;

import com.smirnoff.home.finance.fund.client.FundClient;
import com.smirnoff.home.finance.fund.model.Fund;
import com.smirnoff.home.finance.fund.model.GetFundByIdsModelList;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.graphql.request.GraphQlVariables;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class FundAdapterImpl implements FundAdapter {

    private final FundClient fundClient;

    //language=graphql
    private static final String GRAPHQL_REQUESTS = """
            query GetFundListByIds($fundIds: [String]) {
                getFundListByIds(fundIds: $fundIds) {
                    id
                    name
                }
            }
            """;

    @Override
    public List<Fund> getByIds(List<String> fundIds) {
        GraphQlResponse<GetFundByIdsModelList> funds = fundClient.getFundsByIds(GraphQlRequest.builder()
                .query(GRAPHQL_REQUESTS)
                .operationName("GetFundListByIds")
                .variables(new GraphQlVariables()
                        .addObject("fundIds", fundIds))
                .build());

        GetFundByIdsModelList data = funds.getData();
        if (isNull(data)) {
            return Collections.emptyList();
        }

        return data.getFundListByIds();
    }
}
