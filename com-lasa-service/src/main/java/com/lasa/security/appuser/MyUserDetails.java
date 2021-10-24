package com.lasa.security.appuser;

import com.lasa.data.model.entity.Admin;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import static com.lasa.security.utils.permission.ApplicationUserRole.*;

@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private Integer id;
    private String userName;
    private String name;
    private String password;
    private int status;
    private Set<? extends GrantedAuthority> grantedAuthorities;

    public MyUserDetails(Student student) {
        this.id = student.getId();
        this.userName = student.getEmail();
        this.name = student.getName();
        this.status = student.getStatus();
        this.grantedAuthorities =  STUDENT.getGrantedAuthorities();
    }

    public MyUserDetails(Lecturer lecturer) {
        this.id = lecturer.getId();
        this.userName = lecturer.getEmail();
        this.name = lecturer.getName();
        this.status = lecturer.getStatus();
        this.grantedAuthorities = LECTURER.getGrantedAuthorities();
    }

    public MyUserDetails(Admin admin) {
        this.id = admin.getId();
        this.userName = admin.getUsername();
        this.password = admin.getPassword();
        this.name = admin.getName();
        this.status = 1;
        this.grantedAuthorities = ADMIN.getGrantedAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != 2;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status != 0;
    }
}
