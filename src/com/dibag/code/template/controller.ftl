package ${generateConfig.controllerPackage};

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import ${generateConfig.basePackage}.constant.Const;
import ${generateConfig.basePackage}.constant.JsonResult;
import ${generateConfig.entityPackage}.${table.entityName};
import ${generateConfig.servicePackage}.${table.serviceName};
import ${generateConfig.basePackage}.constant.ExceptionCode;

/**
 * ${table.entityComment}控制器
 * @author admin
 *
 */
@RequestMapping(value="/admin/${table.entityNameFirst}")
@Controller
public class ${table.controllerName} {
	
	private static Logger logger = LoggerFactory.getLogger( ${table.entityName}AdminController.class );
	
	@Autowired
	private ${table.entityName}Service ${table.entityNameFirst}Service;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(HttpServletRequest request, Model model) {
		if(request.getMethod().equals( "POST" )){
			return;
		}
		model.addAttribute("${table.entityNameFirst}_navbar", "am-active");
		model.addAttribute("${table.entityNameFirst}_sub_navbar", "am-active");
	}
	
   /**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param ${table.entityNameFirst}
	 * @param model
	 * @return
	 */		
	@RequestMapping
    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, ${table.entityName} ${table.entityNameFirst}, Model model ){
		try {
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "${table.primaryKey}.desc" ) );
			PageList<${table.entityName}> list = ${table.entityNameFirst}Service.getPageList( ${table.entityNameFirst}, pageBounds );
			model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
			model.addAttribute("list", list);
			model.addAttribute("${table.entityNameFirst}", ${table.entityNameFirst});
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/${table.entityNameFirst}/list";
    }
    
    /**
	 * 异步列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param ${table.entityNameFirst}
	 * @param model
	 * @return
	 */		
    @RequestMapping(value="/asyncList", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, ${table.entityName} ${table.entityNameFirst} ){
	    PageList<${table.entityName}> pageList = null;
	    JsonResult ajaxResult = null;
		try {
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "${table.primaryKey}.desc" ) );
			pageList = ${table.entityNameFirst}Service.getPageList( ${table.entityNameFirst}, pageBounds );
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, pageList );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }    
	
   /**
	 * 详情
	 * 
	 * @param id
	 * @return
	 */	
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET )
    public String detail(@PathVariable ${table.primaryKeyType} id, Model model ){
		try {
			${table.entityName} ${table.entityNameFirst} = ${table.entityNameFirst}Service.selectByPrimaryKey(id);
            model.addAttribute("${table.entityNameFirst}", ${table.entityNameFirst});
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/${table.entityNameFirst}/edit";
    }
 
   /**
	 * 更新
	 * 
	 * @param ${table.entityNameFirst}
	 * @return
	 */
    @RequestMapping(value="/update", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult update(${table.entityName} ${table.entityNameFirst}){
		JsonResult ajaxResult = null;
		try {
		<#if (table.primaryKeyType == "String")>
		    if(${table.entityNameFirst}.get${table.primaryKeyEntityNameFirst}() == null || ${table.entityNameFirst}.get${table.primaryKeyEntityNameFirst}().isEmpty()){
		<#else>
		    if(${table.entityNameFirst}.get${table.primaryKeyEntityNameFirst}() == null || ${table.entityNameFirst}.get${table.primaryKeyEntityNameFirst}() <= 0){
		</#if>
		       return new JsonResult( ExceptionCode.FAIL,"" );
		    }
			${table.entityNameFirst}Service.update(${table.entityNameFirst});
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, ${table.entityNameFirst} );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }
   
   /**
	 * 创建
	 * 
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/create", method = RequestMethod.GET )
    public String create( Model model ){
        return "admin/${table.entityNameFirst}/create";
    }
    
   /**
	 * 创建
	 * 
	 * @param ${table.entityNameFirst}
	 * @return
	 */
    @RequestMapping(value="/create", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public JsonResult create(${table.entityName} ${table.entityNameFirst}){
		JsonResult ajaxResult = null;
		try {
			${table.entityNameFirst}Service.create(${table.entityNameFirst});
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, ${table.entityNameFirst} );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }
    
   /**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
    @RequestMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult delete(@PathVariable ${table.primaryKeyType} id){
		JsonResult ajaxResult = null;
		try {
			${table.entityNameFirst}Service.deleteByPrimaryKey(id);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }
    
   /**
	 * 批量删除
	 * 
	 * @param ids id集合,例如:1,2,3,4
	 * @return
	 */
    @RequestMapping(value="/deletes/{ids}", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult deletes(@PathVariable String ids){
		JsonResult ajaxResult = null;
		try {
			List<${table.entityName}> ${table.entityNameFirst}s = new ArrayList<${table.entityName}>();
			String[] strIds = ids.split( "," );
			for( String strId : strIds ) {
				${table.entityName} ${table.entityNameFirst} = new ${table.entityName}();
			<#if (table.primaryKeyType == "String")>
			    ${table.entityNameFirst}.set${table.primaryKeyEntityNameFirst}( strId );
		    <#else>
		        ${table.entityNameFirst}.set${table.primaryKeyEntityNameFirst}( ${table.primaryKeyType}.parse${table.primaryKeyType}( strId ) );
		    </#if>
	            ${table.entityNameFirst}s.add( ${table.entityNameFirst} );
            }
			${table.entityNameFirst}Service.deleteBatch( ${table.entityNameFirst}s );
			${table.entityNameFirst}Service.deleteBatch(${table.entityNameFirst}s);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }  
}