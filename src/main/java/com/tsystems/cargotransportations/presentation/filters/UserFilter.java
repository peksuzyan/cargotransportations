package com.tsystems.cargotransportations.presentation.filters;

import com.tsystems.cargotransportations.entity.UserRole;

public class UserFilter extends RoleFilter {

    public UserFilter() {
        super(UserRole.USER);
    }

}
