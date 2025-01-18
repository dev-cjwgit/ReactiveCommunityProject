package com.cjw.reactivecommunityproject.web.template.mapper;

import com.cjw.reactivecommunityproject.web.template.model.entity.TemplateRestEntityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemplateRestMapper {
    TemplateRestEntityVO select();
}
