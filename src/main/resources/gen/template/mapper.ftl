package ${packageName}.${moduleName}.mapper;

import org.springframework.stereotype.Repository;
import com.ucpaas.sms.common.dto.Page;
import ${packageName}.${moduleName}.entity.${table.className};

import java.util.List;
import java.util.Map;

/**
 * @description ${table.description}
 * @author ${author}
 * @date ${now}
 * @version ${version}
 */
@Repository
public interface ${table.className}Mapper{

	int insert(${table.className} model);
	
	int insertBatch(List<${table.className}> modelList);

	int update(${table.className} model);
	
	int updateSelective(${table.className} model);

    ${table.className} getBy${table.capitalObjectKey}(${table.objectKeyType} ${table.objectKey});

	List<${table.className}> queryList(Page<${table.className}> page);

	List<${table.className}> findList(${table.className} model);

	int count(Map<String,Object> params);

}