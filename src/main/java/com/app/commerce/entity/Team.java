package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.*;

@Entity
@Table(name = Team.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
public class Team extends BaseEntity {
    public static final String COLLECTION_NAME = "teams";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;

    public Team(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }
    @ManyToMany(mappedBy = "teams")
    private Set<Shop> shops;

    @ManyToMany(mappedBy = "teams")
    private Set<User> staffs;

    @PreRemove
    private void preRemove() {
        for (Shop s : shops) {
            s.getTeams().removeIf(team -> Objects.equals(team.getId(), this.getId()));
        }

        for (User staff: staffs) {
            staff.getTeams().removeIf(team -> Objects.equals(team.getId(), this.getId()));
        }
    }

    public void addShop(Shop shop) {
        if (shops == null) {
            shops = new HashSet<>();
        }
        if (shops.stream().noneMatch(s -> Objects.equals(s.getId(), shop.getId()))) {
            shops.add(shop);
//            shop.setTeam(this);
        }
    }

    public void removeShop(Shop shop) {
        if (shops == null) {
            return;
        }
        shops.removeIf(s -> Objects.equals(s.getId(), shop.getId()));
//        shop.setTeam(null);
    }

    public void addStaff(User staff) {
        if (staffs == null) {
            staffs = new HashSet<>();
        }
        if (staffs.stream().noneMatch(s -> Objects.equals(s.getId(), staff.getId()))) {
            staffs.add(staff);
//            staff.setTeam(this);
        }
    }

    public void removeStaff(User staff) {
        if (staff == null) {
            return;
        }
        staffs.removeIf(s -> Objects.equals(s.getId(), staff.getId()));
//        staff.setTeam(null);
    }
}
