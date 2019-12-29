package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author shkstart
 * @create 2019-12-16 14:52
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String>{
}
