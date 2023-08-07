package com.app.commerce.controller;

import com.app.commerce.config.BaseConstants;
import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.ListResponse;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.common.response.StatusCountResponse;
import com.app.commerce.dto.excel.OrderExcel;
import com.app.commerce.dto.order.request.GetAllOrdersRequest;
import com.app.commerce.dto.order.response.OrderDetailResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.entity.Order;
import com.app.commerce.entity.Team;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.service.AuthenticationService;
import com.app.commerce.service.OrderService;
import com.app.commerce.util.DateTime;
import com.app.commerce.util.ExcelExporter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class OrderController {

    private final OrderService orderService;
    private final ExcelExporter excelExporter;

    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<PageResponse<Order, OrderResponse>> getAllOrders(@Valid @ParameterObject GetAllOrdersRequest request) {
        if (!authenticationService.isAdmin()) {
            User loggedUser = authenticationService.getCurrentAuthenticatedAccount()
                    .orElseThrow(() -> new ApiException(ResponseCode.UNAUTHORIZED));

            if (loggedUser.getTeams() == null || loggedUser.getTeams().isEmpty()) {
                return ResponseEntity.ok(new PageResponse<>());
            }

            request.setTeamIds(loggedUser.getTeams()
                    .stream()
                    .map(Team::getId)
                    .collect(Collectors.toList()));
        }

        if (StringUtils.isBlank(request.getSortBy())) {
            request.setSortBy("shopLastSyncAt");
            request.setSortDir(Sort.Direction.DESC);
        }

        PageResponse<Order, OrderResponse> response = orderService.getAllOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("export")
    @Transactional
    public ResponseEntity<ByteArrayResource> exportOrders(@Valid @ParameterObject GetAllOrdersRequest request, HttpServletResponse response) {
        if (request.getFrom() == null || request.getTo() == null) {
            throw new ApiException(ResponseCode.ORDER_ERROR_EXPORT_DATE_RANGE_REQUIRED);
        }

        if (StringUtils.isBlank(request.getSortBy())) {
            request.setSortBy("shopLastSyncAt");
            request.setSortDir(Sort.Direction.DESC);
        }

        List<Order> orders = orderService.getOrders(request);
        List<OrderExcel> orderExcels = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++)  {
            orderExcels.add(new OrderExcel(i, orders.get(i)));
        }

        Map<String, String> columnHeaders = new LinkedHashMap<>();
        columnHeaders.put("no", "N.o");
        columnHeaders.put("etsyOrderId", "Etsy order ID");
        columnHeaders.put("orderTime", "Order time");
        columnHeaders.put("progressStep", "Progress step");
        columnHeaders.put("buyerName", "Buyer Name");
        columnHeaders.put("buyerEmail", "Buyer Email");
        columnHeaders.put("itemCount", "Item count");
        columnHeaders.put("total", "Total");
        columnHeaders.put("tax", "Tax");
        columnHeaders.put("currency", "Currency");

        String fileName = String.format("list-orders-%s-%s.xlsx", DateTime.toString(request.getFrom(),  TimeZone.getTimeZone("Asia/Bangkok"), BaseConstants.DATE_FORMAT),
                        DateTime.toString(request.getTo(), TimeZone.getTimeZone("Asia/Bangkok"), BaseConstants.DATE_FORMAT));
        ByteArrayResource byteArrayResource = new ByteArrayResource(excelExporter.exportToExcel(orderExcels,columnHeaders).readAllBytes());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName));

        return new ResponseEntity<>(byteArrayResource, header, HttpStatus.CREATED);
    }

    @GetMapping("/statuses")
    public ResponseEntity<ListResponse<StatusCountResponse>> getAllOrdersStatuses(@RequestParam Optional<String> shopId) {
        List<StatusCountResponse> statuses = orderService.getAllStatuses(shopId.orElse(null));
        return ResponseEntity.ok(new ListResponse<>(statuses));
    }

    @GetMapping("/count/shop-status")
    public ResponseEntity<ListResponse<StatusCountResponse>> countOrderByShopStatus() {
        List<StatusCountResponse> statuses = orderService.countByShopStatus();
        return ResponseEntity.ok(new ListResponse<>(statuses));
    }


    @GetMapping("{id}")
    public ResponseEntity<OrderDetailResponse> getOrder(@PathVariable Long id) {
        OrderDetailResponse response = orderService.getOrder(id);
        return ResponseEntity.ok(response);
    }

}
