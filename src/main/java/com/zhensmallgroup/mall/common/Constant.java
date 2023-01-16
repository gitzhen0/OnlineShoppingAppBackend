package com.zhensmallgroup.mall.common;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Constant {
    public static final String SALT = "2jg8a*jjija9s∆j∆ˆˆˆˆ¥¨¨KJ=w=e;[fwer";

    public static final String ZHENS_MALL_USER = "zhens_mall_user";


    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc", "price asc");
    }
}
