package com.app.commerce.service.impl;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.request.GetAllStaffRequest;
import com.app.commerce.dto.staff.request.UpdateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.Role;
import com.app.commerce.entity.Shop;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.UserMapper;
import com.app.commerce.repository.RoleRepository;
import com.app.commerce.repository.ShopRepository;
import com.app.commerce.repository.UserRepository;
import com.app.commerce.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ROLE_ADMIN_CODE = "ROLE_ADMIN";
    private final UserMapper userMapper;

    private final ShopRepository shopRepository;

    @Override
    @Transactional
    public UserResponse createStaff(CreateStaffRequest request) {
        List<Role> roles = request.getRoles()
                .stream()
                .map(roleCode -> {
                    if (ROLE_ADMIN_CODE.equalsIgnoreCase(roleCode)) {
                        throw new ApiException(ResponseCode.ROLE_ERROR_NOT_FOUND);
                    }

                    return roleRepository
                            .findByCode(roleCode)
                            .orElseThrow(() -> new ApiException(ResponseCode.ROLE_ERROR_NOT_FOUND));

                })
                .toList();

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_USERNAME_EXISTED);
        }

        if (request.getStaffId() != null
                && !request.getStaffId().isEmpty()
                && userRepository.existsByStaffId(request.getStaffId())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_STAFF_ID_EXISTED);
        }

        User user = userMapper.toEntity(request);
        user.setRoles(roles);

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public PageResponse<User, UserResponse> getAllStaffs(GetAllStaffRequest request) {
        Page<User> pageResult = userRepository.findAll(request.getSpecification(), request.getPageable());
        return new PageResponse<>(pageResult, userMapper::toResponse);
    }

    @Override
    public UserResponse getStaff(Long id) {
        User staff = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));
        return userMapper.toResponse(staff);
    }

    @Override
    @Transactional
    public void updateStaff(Long id, UpdateStaffRequest request) {
        User staff = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));

        if (request.getUsername() != null && !request.getUsername().equals(staff.getUsername()) && userRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_USERNAME_EXISTED);
        }

        if (request.getStaffId() != null && !request.getStaffId().equals(staff.getStaffId()) && userRepository.existsByStaffId(request.getStaffId())) {
            throw new ApiException(ResponseCode.STAFF_ERROR_STAFF_ID_EXISTED);
        }

        staff.setStaffId(request.getStaffId())
                .setUsername(request.getUsername())
                .setPassword(StringUtils.isNotBlank(request.getPassword())
                        ? passwordEncoder.encode(request.getPassword())
                        : staff.getPassword())
                .setFullName(request.getFullName())
                .setAddress(request.getAddress())
                .setDescription(request.getDescription())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail());

        List<Role> roles = request.getRoles()
                .stream()
                .map(roleCode -> {
                    if (ROLE_ADMIN_CODE.equalsIgnoreCase(roleCode)) {
                        throw new ApiException(ResponseCode.ROLE_ERROR_NOT_FOUND);
                    }
                    return roleRepository
                            .findByCode(roleCode)
                            .orElseThrow(() -> new ApiException(ResponseCode.ROLE_ERROR_NOT_FOUND));

                })
                .toList();

        staff.getRoles().clear();
        staff.getRoles().addAll(roles);

        userRepository.save(staff);
    }

    @Override
    @Transactional
    public void removeStaff(Long id) {
        User staff = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND));

        if (staff.getRoles().stream().anyMatch(role -> role.getCode().equals(ROLE_ADMIN_CODE))) {
            throw new ApiException(ResponseCode.STAFF_ERROR_NOT_FOUND);
        }
        userRepository.delete(staff);
    }

    @Override
    @Transactional
    public void addTracking(User staff, String shopId) {
        if (staff == null) {
            return;
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        shop.addTracker(staff);
        shopRepository.save(shop);
    }

    @Override
    @Transactional
    public void unTracking(User staff, String shopId) {
        if (staff == null) {
            return;
        }

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ApiException(ResponseCode.SHOP_ERROR_NOT_FOUND));

        shop.removeTracker(staff);
        shopRepository.save(shop);
    }

}
