package ${generateConfig.mapperPackage};

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import ${generateConfig.entityPackage}.${table.entityName};

/**
*${table.entityComment},Mapper接口
*/
public interface ${table.mapperName} {

	/**
	 * 获取所有数据
	 * @return
	 */
    public List<${table.entityName}> getList();

    /**
     * 分页获取数据
     * @param pageBounds
     * @return
     */
	public PageList<${table.entityName}> getList( PageBounds pageBounds );
	
	/**
	 * 根据条件获取所有数据
	 * @param ${table.entityNameFirst}
	 * @return
	 */
	public List<${table.entityName}> getPageList( ${table.entityName} ${table.entityNameFirst} );
	
	/**
	 * 根据条件分页获取数据
	 * @param ${table.entityNameFirst}
	 * @param pageBounds
	 * @return
	 */
	public PageList<${table.entityName}> getPageList(${table.entityName} ${table.entityNameFirst}, PageBounds pageBounds );
	
	/**
	 * 根据条件获取所有数据
	 * @param map
	 * @return
	 */
	public List<${table.entityName}> getPageListByMap( Map<String, Object> map );
	
	/**
	 * 根据条件分页获取数据
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<${table.entityName}> getPageListByMap(Map<String, Object> map, PageBounds pageBounds );
	
	/**
	 * 根据ID获取数据
	 * @param ${table.primaryKey}
	 * @return
	 */
	public ${table.entityName} selectByPrimaryKey( ${table.primaryKeyType} ${table.primaryKey} );
	
	/**
	 * 根据条件获取数据
	 * @param ${table.entityNameFirst}
	 */
	public ${table.entityName} selectBy( ${table.entityName} ${table.entityNameFirst} );
	
	/**
	 * 创建
	 * @param ${table.entityNameFirst}
	 */
	public void create( ${table.entityName} ${table.entityNameFirst} );
	
	/**
	 * 有选择的创建新数据
	 * @param ${table.entityNameFirst}
	 */
	public void createSelective(${table.entityName} ${table.entityNameFirst});
	
	/**
	 * 根据ID更新
	 * @param ${table.entityNameFirst}
	 */
	public void update(${table.entityName} ${table.entityNameFirst});
	
	/**
	 * 根据ID有条件的更新
	 * @param ${table.entityNameFirst}
	 */
	public void updateSelective(${table.entityName} ${table.entityNameFirst});
		
	/**
	 * 根据ID有条件的批量更新
	 * @param list
	 */
	public void updateSelectiveBatch(List<${table.entityName}> list);
	
	/**
	 * 根据ID删除
	 * @param ${table.primaryKey}
	 */
	public void deleteByPrimaryKey(${table.primaryKeyType} ${table.primaryKey});
	
	/**
	 * 根据条件进行删除
	 * @param ${table.entityNameFirst}
	 */
	public void deleteBy(${table.entityName} ${table.entityNameFirst});
	
	/**
	 * 根据条件进行批量删除
	 * @param list
	 */
	public void deleteBatch(List<${table.entityName}> list);
}