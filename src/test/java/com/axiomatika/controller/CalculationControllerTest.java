package com.axiomatika.controller;

import com.axiomatika.BaseTest;
import com.axiomatika.model.dto.QuadraticEquationRequest;
import com.axiomatika.model.dto.QuadraticEquationResponse;
import com.axiomatika.model.entity.QuadraticEquationResult;
import com.axiomatika.repository.QuadraticEquationResultRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class CalculationControllerTest extends BaseTest {

    @Autowired
    private QuadraticEquationResultRepository repository;

    @Test
    void quadraticEquationMustBeCalculatedAndSaved() throws Exception {
        //Given:
        List<Pair<QuadraticEquationRequest, Pair<Double, Double>>> requests = List.of(
                Pair.of(buildRequest(1, 2, -3), Pair.of(1.0, -3.0)),
                Pair.of(buildRequest(3, -10, 3), Pair.of(3.0, 0.33)),
                Pair.of(buildRequest(6, 2, -26), Pair.of(1.92, -2.25)),
                Pair.of(buildRequest(80, 1, -117), Pair.of(1.20, -1.21)),
                Pair.of(buildRequest(22.3, -10.5, 0.17), Pair.of(0.45, 0.01))
        );

        for (Pair<QuadraticEquationRequest, Pair<Double, Double>> pair : requests) {
            //When:
            QuadraticEquationRequest request = pair.getKey();
            Pair<Double, Double> expected = pair.getValue();

            ResultActions result = mockMvc.perform(post("/api/v1/calculate/quadratic-equation")
                    .content(objectMapper.writeValueAsBytes(request))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            //Then:
            result.andExpect(status().isOk());

            QuadraticEquationResponse response = buildResponse(result);

            assertEquals(expected.getLeft(), response.getX1().doubleValue());
            assertEquals(expected.getRight(), response.getX2().doubleValue());

            List<QuadraticEquationResult> allByX1AndX2 =
                    repository.findAllByAAndBAndC(request.getA(), request.getB(), request.getC());

            assertEquals(1, allByX1AndX2.size(), String.format("Results haven't been saved: %s", response));
        }
    }

    @Test
    void quadraticEquationMustNotBeCalculatedAndSaved() throws Exception {
        //Given:
        QuadraticEquationRequest request = buildRequest(-15, 8, -6);

        //When:
        ResultActions result = mockMvc.perform(post("/api/v1/calculate/quadratic-equation")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        //Then:
        result.andExpect(status().isBadRequest());

        String response = result.andReturn().getResponse().getContentAsString();

        assertEquals(
                String.format(
                        "It is impossible to calculate the quadratic equation. Discriminant less than zero: %s",
                        -296.0),
                response
        );

        List<QuadraticEquationResult> allByX1AndX2 =
                repository.findAllByAAndBAndC(request.getA(), request.getB(), request.getC());

        assertTrue(CollectionUtils.isEmpty(allByX1AndX2));
    }

    QuadraticEquationRequest buildRequest(double a, double b, double c) {
        QuadraticEquationRequest request = new QuadraticEquationRequest();
        request.setA(new BigDecimal(a).setScale(2, RoundingMode.DOWN));
        request.setB(new BigDecimal(b).setScale(2, RoundingMode.DOWN));
        request.setC(new BigDecimal(c).setScale(2, RoundingMode.DOWN));

        return request;
    }

    QuadraticEquationResponse buildResponse(ResultActions result) throws IOException {
        return objectMapper.readValue(
                result.andReturn().getResponse().getContentAsByteArray(),
                QuadraticEquationResponse.class
        );
    }
}
