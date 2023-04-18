package com.app.commerce.dto.shop.response;

import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.dto.dashboard.response.DashboardResponse;
import com.app.commerce.dto.order.response.OrderResponse;
import com.app.commerce.dto.listing.response.ListingResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDetailResponse extends ShopResponse {
    List<OrderResponse> orders;
    List<ListingResponse> listings;
    List<ConversationResponse> conversations;
    DashboardResponse dashboard;
}
