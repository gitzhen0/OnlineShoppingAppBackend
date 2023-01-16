package com.zhensmallgroup.mall.filter;

import com.zhensmallgroup.mall.common.ApiRestResponse;
import com.zhensmallgroup.mall.common.Constant;
import com.zhensmallgroup.mall.exception.ZhensMallExceptionEnum;
import com.zhensmallgroup.mall.model.pojo.Category;
import com.zhensmallgroup.mall.model.pojo.User;
import com.zhensmallgroup.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminFilter implements Filter {

    @Autowired
    UserService userService;
    /**
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        User currentUser = (User)session.getAttribute((Constant.ZHENS_MALL_USER));

        if(currentUser == null){
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write(
                    "{\n" +
                            "    \"status\": 10007,\n" +
                            "    \"msg\": \"NEED_LOGIN\",\n" +
                            "    \"data\": null\n" +
                            "}"
            );
            out.flush();
            out.close();
            return;
        }
        boolean adminRole = userService.checkAdminRole(currentUser);

        if(adminRole){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write(
                    "{\n" +
                            "    \"status\": 10009,\n" +
                            "    \"msg\": \"NEED_Admin\",\n" +
                            "    \"data\": null\n" +
                            "}"
            );
            out.flush();
            out.close();
            return;
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
