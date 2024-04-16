package com.smirnoff.home.graphql.request;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Builder
public record GraphQlRequest(
        String query,
        String operationName,
        GraphQlVariables variables
) {

}
