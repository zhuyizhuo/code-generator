package ${javaClassDefinition.SERVICE_IMPL.fullPackage};

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import ${javaClassDefinition.MAPPER.fullPackage}.${javaClassDefinition.MAPPER.className};
import ${javaClassDefinition.MODEL.fullPackage}.${javaClassDefinition.MODEL.className};
import ${javaClassDefinition.SERVICE.fullPackage}.${javaClassDefinition.SERVICE.className};

/**
 * ${tableInfo.tableComment} Service 实现类
 * time:    ${classCommentInfo.createTime} <br/>
 * @author  ${classCommentInfo.author} <br/>
 * @since   ${classCommentInfo.sinceVersion} <br/>
 */
@Service("${javaClassDefinition.MODEL.className?uncap_first}")
public class ${javaClassDefinition.SERVICE_IMPL.className} extends ServiceImpl<${javaClassDefinition.MAPPER.className}, ${javaClassDefinition.MODEL.className}> implements ${javaClassDefinition.SERVICE.className} {

}