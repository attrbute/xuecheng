package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author shkstart
 * @create 2019-12-17 22:07
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {
}
