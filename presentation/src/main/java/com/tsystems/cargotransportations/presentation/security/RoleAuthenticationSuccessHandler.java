package com.tsystems.cargotransportations.presentation.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RoleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Map<String, String> roleUrlMap;

    /**
     * When user with given role passed authentication successfully,
     * redirects it to corresponding start page.
     * @param httpServletRequest request
     * @param httpServletResponse response
     * @param authentication authentication
     * @throws IOException IOException instance
     * @throws ServletException ServletException instance
     */
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().isEmpty()
                    ? null
                    : userDetails.getAuthorities().toArray()[0].toString();
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + roleUrlMap.get(role));
        }
    }

    /**
     * Gets roleUrlMap.
     *
     * @return roleUrlMap roleUrlMap
     */
    public Map<String, String> getRoleUrlMap() {
        return roleUrlMap;
    }

    /**
     * Sets roleUrlMap.
     *
     * @param roleUrlMap roleUrlMap
     */
    public void setRoleUrlMap(Map<String, String> roleUrlMap) {
        this.roleUrlMap = roleUrlMap;
    }
}
