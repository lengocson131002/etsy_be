package com.app.commerce.mappings.impl;

import com.app.commerce.dto.profile.request.CreateGoLoginProfileIdRequest;
import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.dto.shop.request.GoLoginProfileDto;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.mappings.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
        GoLoginProfileResponse response = new GoLoginProfileResponse()
                .setId(profile.getId())
                .setGoLoginProfileId(profile.getGoLoginProfileId())
                .setName(profile.getName())
                .setCreatedDate(profile.getCreatedDate())
                .setProxy(profile.getProxy())
                .setNotes(profile.getNotes())
                .setFolderName(profile.getFolderName());

        if (profile.getShop() != null) {
            response.setShopId(profile.getShop().getId());
            response.setShopName(profile.getShop().getName());
        }

        return response;
    }
}
