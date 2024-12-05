package com.cjw.reactivecommunityproject.web.template.dao;

import com.cjw.reactivecommunityproject.web.template.model.entity.TemplateRestEntityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemplateRestDAO {
    TemplateRestEntityVO select();
}
