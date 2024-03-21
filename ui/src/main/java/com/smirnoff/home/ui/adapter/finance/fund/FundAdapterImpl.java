package com.smirnoff.home.ui.adapter.finance.fund;

import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.ui.adapter.finance.fund.client.FundClient;
import com.smirnoff.home.ui.adapter.finance.fund.client.GetFundModelList;
import com.smirnoff.home.ui.model.finance.fund.FundModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FundAdapterImpl implements FundAdapter {

    private final FundClient fundClient;

    //language=graphql
    private static final String GET_FUND_REQUEST = """
            query GetFundList {
                getFundList {
                    id
                    name
                }
            }
            """;

    //language=graphql
    private static final String CREATE_FUND_REQUEST = """
            mutation CreateFund($name: String) {
                createFund(name: $name) {
                    id
                }
            }
            """;

    //language=graphql
    private static final String UPDATE_FUND_REQUEST = """
            mutation UpdateFund($id: String, $name: String) {
                updateFund(id: $id, name: $name) {
                    id
                }
            }
            """;

    //language=graphql
    private static final String DELETE_FUND_REQUEST = """
            mutation DeleteFund($id: String) {
                deleteFund(id: $id) {
                    result
                }
            }
            """;

    @Override
    public void create(String fundName) {
        fundClient.createFund(GraphQlRequest.builder()
                .query(CREATE_FUND_REQUEST)
                .operationName("CreateFund")
                .variables(Map.of("name", fundName))
                .build()
        );
    }

    @Override
    public void update(String id, String fundName) {
        fundClient.updateFund(GraphQlRequest.builder()
                .query(UPDATE_FUND_REQUEST)
                .operationName("UpdateFund")
                .variables(Map.of("id", id, "name", fundName))
                .build()
        );
    }

    @Override
    public List<FundModel> getList() {
        GraphQlResponse<GetFundModelList> response = fundClient.getFunds(GraphQlRequest.builder()
                .query(GET_FUND_REQUEST)
                .operationName("GetFundList")
                .build()
        );
        return response.getData().getFundList();
    }

    @Override
    public void delete(String fundId) {
        fundClient.deleteFund(GraphQlRequest.builder()
                .query(DELETE_FUND_REQUEST)
                .operationName("DeleteFund")
                .variables(Map.of("id", fundId))
                .build()
        );
    }
}
