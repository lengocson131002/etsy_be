package com.app.commerce.mappings.impl;

import com.app.commerce.dto.profile.request.CreateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileDetailResponse;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.dto.shop.request.GoLoginProfileDto;
import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.entity.Shop;
import com.app.commerce.mappings.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public GoLoginProfile toEntity(GoLoginProfileDto dto) {
        if (dto == null) {
            return null;
        }
        return new GoLoginProfile()
                .setGoLoginProfileId(dto.getId())
                .setFolderName(dto.getId())
                .setName(dto.getName())
                .setCreatedDate(dto.getCreatedDate())
                .setProxy(dto.getProxy())
                .setNotes(dto.getNotes())
                .setFolderName(dto.getFolderName());
    }

    @Override
    public GoLoginProfile toEntity(CreateGoLoginProfileIdRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Go login profile Request is required");
        }
        return new GoLoginProfile()
                .setGoLoginProfileId(request.getGoLoginProfileId())
                .setName(request.getName())
                .setNotes(request.getNotes())
                .setCreatedDate(request.getCreatedDate())
                .setProxy(request.getProxy())
                .setFolderName(request.getFolderName());
    }

    @Override
    public GoLoginProfileResponse toResponse(GoLoginProfile profile) {
        if (profile == null) {
            return null;
        }


        return new GoLoginProfileResponse()
                .setId(profile.getId())
                .setGoLoginProfileId(profile.getGoLoginProfileId())
                .setName(profile.getName())
                .setCreatedDate(profile.getCreatedDate())
                .setProxy(profile.getProxy())
                .setNotes(profile.getNotes())
                .setFolderName(profile.getFolderName())
                .setStatus(getStatus(profile));
    }

    @Override
    public GoLoginProfileDetailResponse toDetailResponse(GoLoginProfile profile) {
        if (profile == null) {
            return null;
        }
        GoLoginProfileDetailResponse response = new GoLoginProfileDetailResponse()
                .setId(profile.getId())
                .setGoLoginProfileId(profile.getGoLoginProfileId())
                .setName(profile.getName())
                .setCreatedDate(profile.getCreatedDate())
                .setProxy(profile.getProxy())
                .setNotes(profile.getNotes())
                .setFolderName(profile.getFolderName())
                .setStatus(getStatus(profile));

        if (profile.getShops() != null) {
            response.setShops(profile.getShops().stream()
                    .map(shop -> {
                        ShopResponse shopResponse = new ShopResponse();
                        shopResponse.setId(shop.getId());
                        shopResponse.setName(shop.getName());
                        shopResponse.setAvatar(shop.getAvatar());
                        shopResponse.setStatus(shop.getStatus());
                        return shopResponse;
                    }).collect(Collectors.toList()));
        }

        return response;
    }

    private List<String> getStatus(GoLoginProfile profile) {
        List<String> statuses = new ArrayList<>();
        if (profile.getIsLogOut()) {
            statuses.add("Logout");
        }
        if (profile.getIsDeleted()) {
            statuses.add("Deleted");
        }

        if (profile.getIsEmpty()) {
            statuses.add("Empty");
        }

        if (profile.getIsFailedProxy()) {
            statuses.add("Failed proxy");
        }

        if (profile.getIsTooManyRequest()) {
            statuses.add("Too many request");
        }

        return statuses;
    }
}
