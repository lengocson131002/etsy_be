package com.app.commerce.service.impl;

import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.Role;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.mappings.UserMapper;
import com.app.commerce.repository.RoleRepository;
import com.app.commerce.repository.UserRepository;
import com.app.commerce.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ROLE_ADMIN_CODE = "ROLE_ADMIN";
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse createStaff(CreateStaffRequest request) {
        List<Role> roles = request.getRoleCodes()
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
            throw new ApiException(ResponseCode.USER_ERROR_EXISTED);
        }

        User user = new User()
                .setUsername(request.getUsername())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setPhoneNumber(request.getPhoneNumber())
                .setEmail(request.getEmail())
                .setRoles(roles);

       User saved = userRepository.save(user);
       return userMapper.toResponse(saved);
    }
}
