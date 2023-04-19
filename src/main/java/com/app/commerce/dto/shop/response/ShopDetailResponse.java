package com.app.commerce.dto.shop.response;

import com.app.commerce.dto.dashboard.response.DashboardResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDetailResponse extends ShopResponse {
    //    List<OrderResponse> orders;
//    List<ListingResponse> listings;
//    List<ConversationResponse> conversations;
    DashboardResponse dashboard;
}
