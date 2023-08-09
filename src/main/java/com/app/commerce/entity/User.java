package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = User.COLLECTION_NAME)
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class User extends BaseEntity implements UserDetails {

    public static final String COLLECTION_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String staffId;
    private String address;
    private String phoneNumber;
    private String fullName;
    private String email;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "staff_team",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "trackers")
    private Set<Shop> trackings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
    }

    @PreRemove
    private void preRemove() {
        for (Team t : teams) {
            t.getStaffs().removeIf(staff -> Objects.equals(staff.getId(), this.getId()));
        }

        for (Shop shop : trackings) {
            shop.getTrackers().removeIf(tracker -> Objects.equals(tracker.getId(), this.getId()));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User setTeams(Set<Team> updatedTeams) {
        if (this.trackings == null) {
            this.trackings = new HashSet<>();
        }

        this.teams = updatedTeams;
        for (Shop shop: this.trackings) {
            if (shop.getTeams().stream().noneMatch(team -> updatedTeams.stream().anyMatch(te -> Objects.equals(te.getId(), team.getId())))) {
                shop.getTrackers().remove(this);
            }
        }

        return this;
    }
}
