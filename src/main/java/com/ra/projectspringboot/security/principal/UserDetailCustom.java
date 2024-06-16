package com.ra.projectspringboot.security.principal;

import com.ra.projectspringboot.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDetailCustom implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Boolean status;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private Date created_at;
    private Date updated_at;
    private Boolean is_deleted;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailCustom buildUserDetails(User user) {
        return UserDetailCustom.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .status(user.getStatus())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .address(user.getAddress())
                .created_at(user.getCreatedAt())
                .updated_at(user.getUpdatedAt())
                .is_deleted(user.getIsDeleted())
                .authorities(user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).toList())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
