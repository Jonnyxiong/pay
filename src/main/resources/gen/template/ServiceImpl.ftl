package ${packageName}.${moduleName}.service;

import com.ucpaas.sms.common.dto.Page;
import com.ucpaas.sms.config.aop.DBConnection;
import com.ucpaas.sms.config.db.DbType;
import ${packageName}.${moduleName}.entity.${table.className};
import ${packageName}.${moduleName}.mapper.${table.className}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @description ${table.description}
 * @author ${author}
 * @date ${now}
 * @version ${version}
 */
@Service
public class ${table.className}ServiceImpl implements ${table.className}Service{

    @Autowired
    private ${table.className}Mapper ${table.classInstanceName}Mapper;
    
    @Override
    @DBConnection(DbType.MESSAGE)
	@Transactional
    public int insert(${table.className} model) {
        return this.${table.classInstanceName}Mapper.insert(model);
    }

    @Override
    @DBConnection(DbType.MESSAGE)
	@Transactional
    public int insertBatch(List<${table.className}> modelList) {
        return this.${table.classInstanceName}Mapper.insertBatch(modelList);
    }

	@Override
	@DBConnection(DbType.MESSAGE)
	@Transactional
    public int update(${table.className} model) {
		${table.className} old = this.${table.classInstanceName}Mapper.getBy${table.capitalObjectKey}(model.get${table.capitalObjectKey}());
		if(old == null){
			return 0;
		}
		return this.${table.classInstanceName}Mapper.update(model); 
    }

    @Override
    @DBConnection(DbType.MESSAGE)
	@Transactional
    public int updateSelective(${table.className} model) {
		${table.className} old = this.${table.classInstanceName}Mapper.getBy${table.capitalObjectKey}(model.get${table.capitalObjectKey}());
		if(old != null)
        	return this.${table.classInstanceName}Mapper.updateSelective(model);
		return 0;
    }

    @Override
    @DBConnection(DbType.MESSAGE)
	@Transactional
    public ${table.className} getBy${table.capitalObjectKey}(${table.objectKeyType} ${table.objectKey}) {
        ${table.className} model = this.${table.classInstanceName}Mapper.getBy${table.capitalObjectKey}(${table.objectKey});
		return model;
    }

    @Override
    @DBConnection(DbType.MESSAGE)
	@Transactional
    public Page queryList(Page page) {
        List<${table.className}> list = this.${table.classInstanceName}Mapper.queryList(page);
        page.setData(list);
        return page;
    }

    @Override
    @DBConnection(DbType.MESSAGE)
	@Transactional
    public List<${table.className}> findList(${table.className} model) {
        return this.${table.classInstanceName}Mapper.findList(model);
    }

    @Override
    @DBConnection(DbType.MESSAGE)
	@Transactional
    public int count(Map<String,Object> params) {
		return this.${table.classInstanceName}Mapper.count(params);
    }
    
}
