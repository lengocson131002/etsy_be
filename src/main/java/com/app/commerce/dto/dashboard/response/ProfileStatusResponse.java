package com.app.commerce.dto.dashboard.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProfileStatusResponse {
    private Integer logout;
    private Integer failedProxy;
    private Integer deleted;
    private Integer tooManyRequest;
    private Integer empty;
    private Integer sync;

}
