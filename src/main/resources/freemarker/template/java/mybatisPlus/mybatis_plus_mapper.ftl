package ${javaClassDefinition.MAPPER.fullPackage};


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${javaClassDefinition.MODEL.fullPackage}.${javaClassDefinition.MODEL.className};
import org.apache.ibatis.annotations.Mapper;

/**
 * ${tableInfo.tableComment!'${tableInfo.tableName}'} Mapper
 *
 * @author  ${classCommentInfo.author} <br/>
 * @date    ${classCommentInfo.createTime} <br/>
 * @since   ${classCommentInfo.sinceVersion} <br/>
 */
@Mapper
public interface ${javaClassDefinition.MAPPER.className} extends BaseMapper<${javaClassDefinition.MODEL.className}> {

}