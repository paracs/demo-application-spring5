package com.github.kazuki43zoo.app.common.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.Mapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.github.kazuki43zoo.domain.model.AccountAuthenticationHistory;
import com.github.kazuki43zoo.domain.service.account.AccountSharedService;
import com.github.kazuki43zoo.domain.service.security.CustomUserDetails;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Inject
    AccountSharedService accountSharedService;

    @Inject
    Mapper beanMapper;

    public CustomLogoutSuccessHandler() {
        setDefaultTargetUrl("/logoutSuccess");
    }

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        AccountAuthenticationHistory authenticationHistory = beanMapper.map(
                authentication.getDetails(), AccountAuthenticationHistory.class);

        accountSharedService.createLogoutHistory(userDetails.getAccount(), authenticationHistory);

        super.onLogoutSuccess(request, response, authentication);
    }

}
