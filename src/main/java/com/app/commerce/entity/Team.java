package com.app.commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = Team.COLLECTION_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@FieldNameConstants
@NamedEntityGraph(
        name = "team.fetch", attributeNodes = {
        @NamedAttributeNode("shops"),
        @NamedAttributeNode("staffs")
    }
)
public class Team extends BaseEntity {
    public static final String COLLECTION_NAME = "teams";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    @ManyToMany(mappedBy = "teams")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 50)
    private Set<Shop> shops;

    @ManyToMany(mappedBy = "teams")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 50)
    private Set<User> staffs;

    public Team(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    @PreRemove
    private void preRemove() {
        for (Shop s : shops) {
            s.getTeams().removeIf(team -> Objects.equals(team.getId(), this.getId()));
        }

        for (User staff : staffs) {
            staff.getTeams().removeIf(team -> Objects.equals(team.getId(), this.getId()));
        }
    }

    public void addShop(Shop shop) {
        if (shops == null) {
            shops = new HashSet<>();
        }
        if (shops.stream().noneMatch(s -> Objects.equals(s.getId(), shop.getId()))) {
            shops.add(shop);
            shop.getTeams().add(this);
        }
    }

    public void removeShop(Shop shop) {
        if (shops == null) {
            return;
        }
        shops.removeIf(s -> Objects.equals(s.getId(), shop.getId()));
        shop.getTeams().removeIf(t -> Objects.equals(t.getId(), this.id));
    }

    public void addStaff(User staff) {
        if (staffs == null) {
            staffs = new HashSet<>();
        }
        if (staffs.stream().noneMatch(s -> Objects.equals(s.getId(), staff.getId()))) {
            staffs.add(staff);
            staff.getTeams().add(this);
        }
    }

    public void removeStaff(User staff) {
        if (staff == null) {
            return;
        }
        staffs.removeIf(s -> Objects.equals(s.getId(), staff.getId()));
        staff.getTeams().removeIf(t -> Objects.equals(t.getId(), this.id));
    }
}
