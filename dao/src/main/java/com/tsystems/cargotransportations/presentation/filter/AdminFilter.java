package com.tsystems.cargotransportations.presentation.filter;

import com.tsystems.cargotransportations.entity.UserRole;

public class AdminFilter extends RoleFilter {

    public AdminFilter() {
        super(UserRole.ADMIN);
    }

}
