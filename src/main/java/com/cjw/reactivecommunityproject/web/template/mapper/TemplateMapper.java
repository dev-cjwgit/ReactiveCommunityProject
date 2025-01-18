package com.cjw.reactivecommunityproject.web.template.mapper;

import com.cjw.reactivecommunityproject.web.template.model.entity.TemplateEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemplateMapper {
    TemplateEntity select();
}
