package com.app.commerce.mappings.impl;

import com.app.commerce.dto.order.response.OrderDetailResponse;
import com.app.commerce.dto.order.response.OrderItemResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.dto.shop.request.OrderDto;
import com.app.commerce.entity.Order;
import com.app.commerce.entity.OrderItem;
import com.app.commerce.mappings.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderDto dto) {
        return new Order()
                .setEtsyOrderId(dto.getId())
                .setProgressStep(dto.getProgressStep())
                .setItemCount(dto.getItemCount())
                .setItemTotal(dto.getItemTotal())
                .setCouponRate(dto.getCouponRate())
                .setCouponValue(dto.getCouponValue())
                .setCouponCode(dto.getCouponCode())
                .setSubTotal(dto.getSubTotal())
                .setOrderTotal(dto.getOrderTotal())
                .setAdjustedTotal(dto.getAdjustedTotal())
                .setTax(dto.getTax())
                .setOrderName(dto.getOrderName())
                .setOrderTime(dto.getOrderTime())
                .setShippingCustomerName(dto.getShippingCustomerName())
                .setShippingPrice(dto.getShippingPrice())
                .setShippingAddress(dto.getShippingAddress())
                .setShippingBy(dto.getShippingBy())
                .setShippingCareer(dto.getShippingCareer())
                .setEstimateDelivery(dto.getEstimateDelivery())
                .setTrackingNumber(dto.getTrackingNumber())
                .setMarkAsGift(dto.getMarkAsGift())
                .setItems(dto.getItems().stream()
                        .map(this::toOrderItemEntity)
                        .collect(Collectors.toList()));
    }

    @Override
    public OrderItem toOrderItemEntity(OrderDto.OrderItemDto dto) {
        return new OrderItem()
                .setEtsyListingId(dto.getListingId())
                .setImage(dto.getImage())
                .setPrice(dto.getPrice())
                .setName(dto.getName())
                .setQuantity(dto.getQuantity())
                .setDescription(dto.getDescription());
    }

    @Override
    public OrderItemResponse toOrderItemResponse(OrderItem item) {
        return new OrderItemResponse()
                .setId(item.getId())
                .setEtsyListingId(item.getEtsyListingId())
                .setName(item.getName())
                .setImage(item.getImage())
                .setPrice(item.getPrice())
                .setQuantity(item.getQuantity())
                .setDescription(item.getDescription());
    }

    @Override
    public OrderResponse toResponse(Order order) {
        if (order == null) {
            return null;
        }
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setEtsyOrderId(order.getEtsyOrderId());
        response.setProgressStep(order.getProgressStep());
        response.setItemCount(order.getItemCount());
        response.setItemTotal(order.getItemTotal());
        response.setCouponRate(order.getCouponRate());
        response.setCouponValue(order.getCouponValue());
        response.setCouponCode(order.getCouponCode());
        response.setSubTotal(order.getSubTotal());
        response.setOrderTotal(order.getOrderTotal());
        response.setAdjustedTotal(order.getAdjustedTotal());
        response.setTax(order.getTax());
        response.setOrderName(order.getOrderName());
        response.setOrderTime(order.getOrderTime());
        response.setShippingCustomerName(order.getShippingCustomerName());
        response.setShippingPrice(order.getShippingPrice());
        response.setShippingAddress(order.getShippingAddress());
        response.setShippingBy(order.getShippingBy());
        response.setShippingCareer(order.getShippingCareer());
        response.setEstimateDelivery(order.getEstimateDelivery());
        response.setTrackingNumber(order.getTrackingNumber());
        response.setMarkAsGift(order.getMarkAsGift());
        return response;
    }

    @Override
    public OrderDetailResponse toDetailResponse(Order order) {
        if (order == null) {
            return null;
        }
        OrderDetailResponse response = new OrderDetailResponse();
        response.setId(order.getId());
        response.setEtsyOrderId(order.getEtsyOrderId());
        response.setProgressStep(order.getProgressStep());
        response.setItemCount(order.getItemCount());
        response.setItemTotal(order.getItemTotal());
        response.setCouponRate(order.getCouponRate());
        response.setCouponValue(order.getCouponValue());
        response.setCouponCode(order.getCouponCode());
        response.setSubTotal(order.getSubTotal());
        response.setOrderTotal(order.getOrderTotal());
        response.setAdjustedTotal(order.getAdjustedTotal());
        response.setTax(order.getTax());
        response.setOrderName(order.getOrderName());
        response.setOrderTime(order.getOrderTime());
        response.setShippingCustomerName(order.getShippingCustomerName());
        response.setShippingPrice(order.getShippingPrice());
        response.setShippingAddress(order.getShippingAddress());
        response.setShippingBy(order.getShippingBy());
        response.setShippingCareer(order.getShippingCareer());
        response.setEstimateDelivery(order.getEstimateDelivery());
        response.setTrackingNumber(order.getTrackingNumber());
        response.setMarkAsGift(order.getMarkAsGift());
        response.setItems(order.getItems()
                .stream()
                .map(this::toOrderItemResponse)
                .collect(Collectors.toList()));

        return response;
    }

}
