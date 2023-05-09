package com.bitir.menu.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "menus")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ElementCollection
    private List<Integer> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Menu menu = (Menu) o;
        return id != null && Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
