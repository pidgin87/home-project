package com.smirnoff.home.finance.history;

import com.smirnoff.home.finance.history.adapter.product.ProductAdapter;
import com.smirnoff.home.finance.history.client.OperationHistoryClient;
import com.smirnoff.home.finance.history.client.model.GetOperationHistoryDtoList;
import com.smirnoff.home.finance.history.model.OperationHistoryDto;
import com.smirnoff.home.finance.history.persistance.entity.OperationHistoryEntity;
import com.smirnoff.home.finance.history.persistance.repository.OperationHistoryRepository;
import com.smirnoff.home.finance.product.model.ProductModel;
import com.smirnoff.home.finance.product.model.ProductTypeModel;
import com.smirnoff.home.graphql.request.GraphQlRequest;
import com.smirnoff.home.graphql.request.GraphQlResponse;
import com.smirnoff.home.platform.session.client.service.SessionClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyList;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = HistoryApplicationRunner.class,
        properties = {
                "server.port=10006"
        })
public class OperationHistoryControllerTest {

    @Autowired
    private OperationHistoryClient operationHistoryClient;

    @Autowired
    private OperationHistoryRepository operationHistoryRepository;

    @MockBean
    private SessionClientService sessionClientService;

    @MockBean
    private ProductAdapter productAdapter;

    //language=graphql
    private static final String GET_OPERATION_LIST = """
            query GetShortOperationList {
                getOperationList {
                    id
                    sourceAmount
                    destinationAmount
                    createdDate
                    description
                }
            }
            query GetSourceProductOperationList {
                getOperationList {
                    sourceProduct {
                        id
                        name
                        type
                    }
                }
            }
            query GetDestinationProductOperationList {
                getOperationList {
                    destinationProduct {
                        id
                        name
                        type
                    }
                }
            }            
            """;

    private String companyId;

    @BeforeEach
    public void beforeEach() {
        companyId = UUID.randomUUID().toString();

        Mockito.when(sessionClientService.getSessionId()).thenReturn("session:1234567890");
        Mockito.when(sessionClientService.getCompanyId()).thenReturn(companyId);

        OperationHistoryEntity entity = new OperationHistoryEntity();
        entity.setCompanyId(companyId);

        entity.setSourceProduct("id:SourceProduct");
        entity.setSourceFund("id:SourceFund");
        entity.setSourceAmount(BigDecimal.TWO);
        entity.setSourceCurrency("id:SourceCurrency");

        entity.setDestinationProduct("id:DestinationProduct");
        entity.setDestinationFund("id:DestinationFund");
        entity.setDestinationAmount(BigDecimal.TEN);
        entity.setDestinationCurrency("id:DestinationCurrency");
        entity.setDescription("Description");
        entity.setCreatedDate(LocalDateTime.now());

        operationHistoryRepository.save(entity);
    }

    @Test
    public void shouldGetShortOperationList() {
        GraphQlResponse<GetOperationHistoryDtoList> response = operationHistoryClient.getOperationList(GraphQlRequest.builder()
                .query(GET_OPERATION_LIST)
                .operationName("GetShortOperationList")
                .build()
        );

        List<OperationHistoryDto> operationList = response.getData().getOperationList();
        Assertions.assertNotNull(operationList);

        OperationHistoryDto operation = operationList.get(0);

        Assertions.assertNotNull(operation.getId());
        Assertions.assertNotNull(operation.getCreatedDate());

        Assertions.assertEquals(0, operation.getDestinationAmount().compareTo(BigDecimal.TEN));
        Assertions.assertEquals(0, operation.getSourceAmount().compareTo(BigDecimal.TWO));
        Assertions.assertEquals("Description", operation.getDescription());
    }

    @Test
    public void shouldGetSourceProductOperationList() {
        Mockito.when(productAdapter.getByIds(anyList())).thenReturn(
                List.of(new ProductModel("id:SourceProduct", "name:SourceProduct", ProductTypeModel.ACCOUNT))
        );

        GraphQlResponse<GetOperationHistoryDtoList> response = operationHistoryClient.getOperationList(GraphQlRequest.builder()
                .query(GET_OPERATION_LIST)
                .operationName("GetSourceProductOperationList")
                .build()
        );

        List<OperationHistoryDto> operationList = response.getData().getOperationList();
        Assertions.assertNotNull(operationList);

        OperationHistoryDto operation = operationList.get(0);

        ProductModel sourceProduct = operation.getSourceProduct();
        Assertions.assertNotNull(sourceProduct);

        Assertions.assertEquals("id:SourceProduct", sourceProduct.id());
        Assertions.assertEquals("name:SourceProduct", sourceProduct.name());
        Assertions.assertEquals(ProductTypeModel.ACCOUNT, sourceProduct.type());
    }

    @Test
    public void shouldGetDestinationProductOperationList() {
        Mockito.when(productAdapter.getByIds(anyList())).thenReturn(
                List.of(new ProductModel("id:DestinationProduct", "name:DestinationProduct", ProductTypeModel.BROKER_ACCOUNT))
        );

        GraphQlResponse<GetOperationHistoryDtoList> response = operationHistoryClient.getOperationList(GraphQlRequest.builder()
                .query(GET_OPERATION_LIST)
                .operationName("GetDestinationProductOperationList")
                .build()
        );

        List<OperationHistoryDto> operationList = response.getData().getOperationList();
        Assertions.assertNotNull(operationList);

        OperationHistoryDto operation = operationList.get(0);

        ProductModel destinationProduct = operation.getDestinationProduct();
        Assertions.assertNotNull(destinationProduct);

        Assertions.assertEquals("id:DestinationProduct", destinationProduct.id());
        Assertions.assertEquals("name:DestinationProduct", destinationProduct.name());
        Assertions.assertEquals(ProductTypeModel.BROKER_ACCOUNT, destinationProduct.type());
    }

}
