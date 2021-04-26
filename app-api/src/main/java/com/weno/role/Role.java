package com.weno.role;

import com.weno.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private RoleName roleName;

    @Builder
    public Role(Long id, User user, RoleName roleName) {
        this.id = id;
        this.user = user;
        this.roleName = roleName;
    }

    @Builder
    public Role(User user, RoleName roleName) {
        this.user = user;
        this.roleName = roleName;
    }
}
