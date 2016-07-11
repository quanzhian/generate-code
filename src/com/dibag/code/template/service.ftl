package ${generateConfig.servicePackage};

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import ${generateConfig.mapperPackage}.${table.mapperName};
import ${generateConfig.entityPackage}.${table.entityName};


/**
*${table.entityComment},服务
*/
@Service
@Transactional
public class ${table.serviceName} {

    @Autowired
	private ${table.mapperName} ${table.entityNameFirst}Mapper;

	/**
	 * 获取所有数据
	 * @return
	 */
    public List<${table.entityName}> getList(){
       return ${table.entityNameFirst}Mapper.getList();
    }

    /**
     * 分页获取数据
     * @param pageBounds
     * @return
     */
	public PageList<${table.entityName}> getList( PageBounds pageBounds ){
	  return ${table.entityNameFirst}Mapper.getList( pageBounds );
    }
	
	/**
	 * 根据条件获取所有数据
	 * @param ${table.entityNameFirst}
	 * @return
	 */
	public List<${table.entityName}> getPageList( ${table.entityName} ${table.entityNameFirst} ){
	  return ${table.entityNameFirst}Mapper.getPageList( ${table.entityNameFirst} );
    }
    
	/**
	 * 根据条件分页获取数据
	 * @param ${table.entityNameFirst}
	 * @param pageBounds
	 * @return
	 */
	public PageList<${table.entityName}> getPageList(${table.entityName} ${table.entityNameFirst}, PageBounds pageBounds ){
	  return ${table.entityNameFirst}Mapper.getPageList(${table.entityNameFirst}, pageBounds);
    }
    
	/**
	 * 根据条件获取所有数据
	 * @param map
	 * @return
	 */
	public List<${table.entityName}> getPageListByMap( Map<String, Object> map ){
	  return ${table.entityNameFirst}Mapper.getPageListByMap( map );
    }
    
	/**
	 * 根据条件分页获取数据
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<${table.entityName}> getPageListByMap(Map<String, Object> map, PageBounds pageBounds ){
	   return ${table.entityNameFirst}Mapper.getPageListByMap(map, pageBounds);
    }
    
	/**
	 * 根据ID获取数据
	 * @param ${table.primaryKey}
	 * @return
	 */
	public ${table.entityName} selectByPrimaryKey( ${table.primaryKeyType} ${table.primaryKey} ){
	   return ${table.entityNameFirst}Mapper.selectByPrimaryKey(${table.primaryKey});
    }
    
	/**
	 * 根据条件获取数据
	 * @param ${table.entityNameFirst}
	 */
	public ${table.entityName} selectBy( ${table.entityName} ${table.entityNameFirst} ){
	   return ${table.entityNameFirst}Mapper.selectBy(${table.entityNameFirst});
    }
    
	/**
	 * 创建
	 * @param ${table.entityNameFirst}
	 */
	public void create( ${table.entityName} ${table.entityNameFirst} ){
	   ${table.entityNameFirst}Mapper.create(${table.entityNameFirst});
    }
    
	/**
	 * 有选择的创建新数据
	 * @param ${table.entityNameFirst}
	 */
	public void createSelective(${table.entityName} ${table.entityNameFirst}){
	   ${table.entityNameFirst}Mapper.createSelective(${table.entityNameFirst});
    }
    
	/**
	 * 根据ID更新
	 * @param ${table.entityNameFirst}
	 */
	public void update(${table.entityName} ${table.entityNameFirst}){
	   ${table.entityNameFirst}Mapper.update(${table.entityNameFirst});
    }
    
	/**
	 * 根据ID有条件的更新
	 * @param ${table.entityNameFirst}
	 */
	public void updateSelective(${table.entityName} ${table.entityNameFirst}){
	   ${table.entityNameFirst}Mapper.updateSelective(${table.entityNameFirst});
    }
    
	/**
	 * 根据ID有条件的批量更新
	 * @param list
	 */
	public void updateSelectiveBatch(List<${table.entityName}> list){
	   ${table.entityNameFirst}Mapper.updateSelectiveBatch(list);
    }
    
	/**
	 * 根据ID删除
	 * @param ${table.primaryKey}
	 */
	public void deleteByPrimaryKey(${table.primaryKeyType} ${table.primaryKey}){
	   ${table.entityNameFirst}Mapper.deleteByPrimaryKey(${table.primaryKey});
    }
    
	/**
	 * 根据条件进行删除
	 * @param ${table.entityNameFirst}
	 */
	public void deleteBy(${table.entityName} ${table.entityNameFirst}){
	   ${table.entityNameFirst}Mapper.deleteBy(${table.entityNameFirst});
    }
    
	/**
	 * 根据条件进行批量删除
	 * @param list
	 */
	public void deleteBatch(List<${table.entityName}> list){
	   ${table.entityNameFirst}Mapper.deleteBatch(list);
    }
}