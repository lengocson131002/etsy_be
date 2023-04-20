package com.app.commerce.mappings.impl;

import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import com.app.commerce.dto.shop.request.GoLoginProfileDto;
import com.app.commerce.entity.GoLoginProfile;
import com.app.commerce.mappings.ProfileMapper;
import org.springframework.stereotype.Service;


@Service
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public GoLoginProfile toEntity(GoLoginProfileDto dto) {
        if (dto == null) {
            return null;
        }
        return new GoLoginProfile()
                .setId(dto.getId())
                .setName(dto.getName())
                .setCreatedDate(dto.getCreatedDate())
                .setProxy(dto.getProxy())
                .setNotes(dto.getNotes())
                .setFolderName(dto.getFolderName());
    }

    @Override
    public GoLoginProfileResponse toResponse(GoLoginProfile profile) {
        if (profile == null) {
            return null;
        }
        return new GoLoginProfileResponse()
                .setId(profile.getId())
                .setName(profile.getName())
                .setCreatedDate(profile.getCreatedDate())
                .setProxy(profile.getProxy())
                .setNotes(profile.getNotes())
                .setFolderName(profile.getFolderName());
    }
}
