package com.bahar.crm.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    OP_VIEW_USER_LIST,
    OP_VIEW_USER,
    OP_CREATE_USER,
    OP_UPDATE_USER,
    OP_DELETE_USER,

    OP_VIEW_ROLE_LIST,
    OP_VIEW_ROLE,
    OP_CREATE_ROLE,
    OP_UPDATE_ROLE,
    OP_DELETE_ROLE,
    ;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
